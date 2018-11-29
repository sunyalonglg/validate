package com.sunyalong.validate.validateinterface;

import com.sunyalong.validate.utils.VlidateUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 检查的Action, 也就是每一个注册都需要一个检查这个注解是否通过验证, 命名方式, 注解名称加 CheckAction的名称, 全部实现check方法.
 *
 * @author sunyalong
 * @version 1.0, 2018-11-29 17:46:04
 * @email sun1920185681@163.com
 */
public interface CheckAction {

    /**
     * 检查方法
     *
     * @param annotation
     * @param field
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    VlidateUtils.ValidateResult check(Annotation annotation, Field field, Object obj) throws IllegalAccessException;
}
