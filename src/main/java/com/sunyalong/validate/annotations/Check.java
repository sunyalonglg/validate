package com.sunyalong.validate.annotations;

import java.lang.annotation.*;

/**
 * 次注解用于表示某个java类是需要被检测, 也就是被校验的
 *
 * @author sunyalong
 * @version 1.0, 2018-11-29 15:53:25
 * @email sun1920185681@163.com
 */
@Target({ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Check {

    String value() default "!";
}
