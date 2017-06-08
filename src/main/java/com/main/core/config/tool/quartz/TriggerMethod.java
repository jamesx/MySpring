package com.main.core.config.tool.quartz;

import java.lang.annotation.*;

/**
 * <p>
 * 定时器执行方法标记注解
 * </p>
 * @author zhengdong 2016年10月13日 下午2:18:39
 * @version V1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TriggerMethod {

    String value() default "";
}
