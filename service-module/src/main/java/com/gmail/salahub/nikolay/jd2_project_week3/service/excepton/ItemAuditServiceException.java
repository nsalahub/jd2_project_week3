package com.gmail.salahub.nikolay.jd2_project_week3.service.excepton;

public class ItemAuditServiceException extends RuntimeException {
    public ItemAuditServiceException(String massage, Throwable e) {
        super(massage, e);
    }
}
