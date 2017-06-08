package com.main.core.config.tool.quartz;

import java.lang.annotation.*;

/**
 * <p>
 * 定时器执行方法标记注解
 * </p>
 * @author superMan791
 * @version V1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TriggerMethod {

    String value() default "";
}
