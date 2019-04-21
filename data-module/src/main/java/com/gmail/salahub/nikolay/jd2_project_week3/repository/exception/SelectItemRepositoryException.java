package com.gmail.salahub.nikolay.jd2_project_week3.repository.exception;

public class SelectItemRepositoryException extends RuntimeException {
    public SelectItemRepositoryException(String massage, Throwable e) {
        super(massage, e);
    }
}
