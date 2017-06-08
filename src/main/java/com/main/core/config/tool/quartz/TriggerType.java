package com.main.core.config.tool.quartz;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * <p>
 * 定时器标记类注解
 * </p>
 * @author superMan791
 * @version V1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface TriggerType {

    /** 定义定时器触发时间 */
    String cronExpression() default "";
}
