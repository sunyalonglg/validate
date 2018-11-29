package com.sunyalong.validate.annotations;

import java.lang.annotation.*;

/**
 * 长度判断注解, 如果在属性中使用了当前注解, 那么给出的值length将于属性值 == 的方式判断, 如果当前的属性值大于或者小于当前注解给出的length都会返回验证不通过
 * 这个注解是判断String类型的, 如果使用数字类型会抛出  类型转换异常. 如果需要判断数字的区间需要使用 BetweenLength
 *
 * @author sunyalong
 * @version 1.0, 2018-11-29 15:57:20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Length {

    /** 异常后的消息 */
    String msg() default "长度不正确!";

    /** 长度值 */
    long length();
}
