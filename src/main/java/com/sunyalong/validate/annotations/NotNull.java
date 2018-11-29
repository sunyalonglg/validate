package com.sunyalong.validate.annotations;

import java.lang.annotation.*;

/**
 * 非空校验注解
 *
 * @author sunyalong
 * @version 1.0, 2018-11-29 15:53:25
 * @email sun1920185681@163.com
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotNull {

    /** 异常后的消息 */
    String msg() default "不能为空!";
}
