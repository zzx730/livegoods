package com.livegoods.search.dao.impl;

import com.livegoods.search.dao.ItemDao4ES;
import com.livegoods.search.pojo.Item4ES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemDao4ESImpl implements ItemDao4ES {
    @Autowired
    private ElasticsearchOperations operations;
    @Value("${livegoods.search.init.enabled}")
    private boolean initEnabled = false;
    /**
     * 批量添加数据到ES
     * @param items
     */
    @Override
    public void batchIndex(List<Item4ES> items) {
        // 判断是否需要初始化ES索引
        if(initEnabled){
            initIndex();
        }
        ArrayList<IndexQuery> list = new ArrayList<>();
        items.forEach(item4ES -> {
            list.add(new IndexQueryBuilder().withObject(item4ES).build());
        });
        // 批量插入数据
        operations.bulkIndex(list, Item4ES.class);

    }
    //初始化索引
    private void initIndex() {
        IndexOperations indexOps = operations.indexOps(Item4ES.class);
        if (indexOps.exists()){
            indexOps.delete();
        }
        indexOps.create();
        indexOps.refresh();
        indexOps.putMapping(indexOps.createMapping());

    }
    /**
     * 分页搜索
     * @param city 城市
     * @param content 搜索关键字， 在title商品标题字段中匹配
     * @param page 页码， 从0开始的
     * @param rows 查询行数
     * @return
     */
    @Override
    public List<Item4ES> queryForPage(String city, String content, int page, int rows) {
        // 创建搜索条件集合 should相当于并集（即多个条件之间不需要同时存在），  Must相当于交集（即多个条件都要同时存在）
//        BoolQueryBuilder shouldBuilder = QueryBuilders.boolQuery()
//
//                .should(QueryBuilders.matchQuery("title",content))
//                // 房屋类型
//                .should(QueryBuilders.matchQuery("houseType",content))
//                // 租赁类型
//                .should(QueryBuilders.matchQuery("rentType",content));
//        // 设置查询条件之间的关系
//        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
//        queryBuilder.must(QueryBuilders.matchQuery("city",city)).must(shouldBuilder);
//        // 创建搜索条件对象
//        NativeSearchQuery query = new NativeSearchQueryBuilder()
//                .withQuery(queryBuilder)
//                .withPageable(PageRequest.of(page * rows, rows))
//                .build();

        // 创建搜索条件集合
        Criteria criteria = new Criteria().and(new Criteria("city").is(city)).subCriteria(
                            // 标题搜索
                            new Criteria().or("title").is(content)
                            // 房屋类型
                            .or("houseType").is(content)
                            // 租赁类型
                            .or("rentType").is(content));
        // 创建搜索条件对象
        Query query = new CriteriaQuery(criteria).setPageable(PageRequest.of(page * rows, rows));
        // 搜索
        SearchHits<Item4ES> result = operations.search(query, Item4ES.class);
        List<SearchHit<Item4ES>> searchHits = result.getSearchHits();
        ArrayList<Item4ES> list = new ArrayList<>();
        for (SearchHit<Item4ES> searchHit : searchHits) {
            // 创建Item4ES对象并构建数据
            Item4ES item4ES = new Item4ES();
            // 商品ID
            item4ES.setId(searchHit.getContent().getId());
            // 商品标题
            item4ES.setTitle(searchHit.getContent().getTitle());
            // 租赁方式
            item4ES.setRentType(searchHit.getContent().getRentType());
            // 商品价格
            item4ES.setPrice(searchHit.getContent().getPrice());
            // 商品城市
            item4ES.setCity(searchHit.getContent().getCity());
            // 房屋类型
            item4ES.setHouseType(searchHit.getContent().getHouseType());
            // 商品图片
            item4ES.setImg(searchHit.getContent().getImg());
            list.add(item4ES);
        }
        return list;
    }


}
