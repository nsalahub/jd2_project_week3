package com.gmail.salahub.nikolay.jd2_project_week3.repository.exception;

import java.sql.SQLException;

public class GettingConnectionException extends RuntimeException {

    public GettingConnectionException(String massage, Throwable throwable) {
        super(massage, throwable);
    }
}
