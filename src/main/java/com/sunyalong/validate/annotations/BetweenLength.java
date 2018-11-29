package com.sunyalong.validate.annotations;

import java.lang.annotation.*;

/**
 * 区间判断, 适用于数字, 或者字符串的长度, 如果min 给出为1  max给出为10 , 判断的时候回使用小于等于 , 也就是 1, 和10 都会返回验证成功
 * 不适用于其他对象, 负责会判处类型转换异常
 *
 * @author sunyalong
 * @version 1.0, 2018-11-29 15:53:25
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BetweenLength {

    /** 异常后的消息 */
    String msg() default "长度有误!";

    /** 最小值 */
    long min();

    /** 最大值 */
    long max();

}
