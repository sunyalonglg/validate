package com.sunyalong.validate.annotations;

import java.lang.annotation.*;

/**
 * 类型判断, 给出一个类型判断当前属性的值类型和 给出的类型是否一致, 当使用次注解的时候将先判断当前是否为空 , 如果当前对象为空那么验证不通过
 *
 * @author sunyalong
 * @version 1.0, 2018-11-29 15:53:25
 * @email sun1920185681@163.com
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Type {

    /** 异常后的消息 */
    String msg() default "类型错误!";

    /** 数据的类型 */
    Class type();
}
