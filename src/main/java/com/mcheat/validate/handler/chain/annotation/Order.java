package com.mcheat.validate.handler.chain.annotation;

import java.lang.annotation.*;

/**
 * 处理器排序
 *
 * @Author McHeat
 * @Date 2019/1/31 13:37
 * @Version 1.0.0
 */
@Documented
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Order {

    int priority() default -1;

}
