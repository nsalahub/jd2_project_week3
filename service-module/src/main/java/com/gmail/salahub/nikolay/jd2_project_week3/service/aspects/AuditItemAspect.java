package com.gmail.salahub.nikolay.jd2_project_week3.service.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditItemAspect {

    private static final Logger logger = LoggerFactory.getLogger(AuditItemAspect.class);

    @Pointcut("execution(public *com.gmail.salahub.nikolay.jd2_project_week3.service.impl.ItemServiceImpl.*(..)")
    public void executeAuditAddItem() {

    }

}
