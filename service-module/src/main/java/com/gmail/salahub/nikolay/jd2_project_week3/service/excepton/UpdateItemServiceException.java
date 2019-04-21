package com.gmail.salahub.nikolay.jd2_project_week3.service.excepton;

public class UpdateItemServiceException extends RuntimeException {
    public UpdateItemServiceException(String massage, Throwable e) {
        super(massage, e);
    }
}
