package com.winterchen.model;

import java.lang.annotation.*;

/**
 * @Author: liuzipan
 * @Description
 * @Date :9:28 2019/4/15
 * @Modefied By:
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {

    String value() default "";

    int col() default 0;
}
