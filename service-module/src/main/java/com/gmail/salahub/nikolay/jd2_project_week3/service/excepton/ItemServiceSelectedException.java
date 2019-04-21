package com.gmail.salahub.nikolay.jd2_project_week3.service.excepton;

public class ItemServiceSelectedException extends RuntimeException {
    public ItemServiceSelectedException(String massage, Throwable e) {
        super(massage, e);
    }
}
