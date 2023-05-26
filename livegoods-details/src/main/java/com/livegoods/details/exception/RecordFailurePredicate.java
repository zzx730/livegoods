package com.livegoods.details.exception;

import java.util.function.Predicate;

public class RecordFailurePredicate implements Predicate<Throwable> {

    @Override
    public boolean test(Throwable throwable) {
        if (throwable.getCause() instanceof BusinessException) return false;
        else return true;
    }
}