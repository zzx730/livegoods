package com.livegoods.search.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@Document(indexName = "livegoods-item")
public class Item4ES {
    @Id
    private String id;
    // 出租类型，不分词
    @Field(type = FieldType.Keyword)
    private String rentType;
    // 出租价格，不分词
    @Field(type = FieldType.Keyword)
    private String price;
    // 房屋类型，最细粒度分词
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String houseType;
    // 房屋图片，不分词
    @Field(type = FieldType.Keyword)
    private String img;
    // 商品标题，最细粒度分词
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String title;
    // 商品城市，不分词
    @Field(type = FieldType.Keyword)
    private String city;


}
