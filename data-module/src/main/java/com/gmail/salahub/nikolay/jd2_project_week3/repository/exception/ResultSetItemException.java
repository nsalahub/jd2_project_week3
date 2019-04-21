package com.gmail.salahub.nikolay.jd2_project_week3.repository.exception;

import java.sql.SQLException;

public class ResultSetItemException extends RuntimeException {
    public ResultSetItemException(String massage, Throwable e) {
        super(massage, e);
    }
}
