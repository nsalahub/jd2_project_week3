package com.gmail.salahub.nikolay.jd2_project_week3.repository.exception;

import java.io.IOException;

public class InitialFileReadingException extends RuntimeException {
    public InitialFileReadingException(String errorReadingInitialFileMassage, Throwable e) {
        super(errorReadingInitialFileMassage, e);
    }
}
