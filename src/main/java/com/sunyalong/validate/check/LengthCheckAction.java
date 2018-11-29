package com.sunyalong.validate.check;

import com.sunyalong.validate.annotations.Length;
import com.sunyalong.validate.utils.VlidateUtils;
import com.sunyalong.validate.validateinterface.CheckAction;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 *  长度检查动作实现
 *
 * @author sunyalong
 * @version 1.0, 2018-11-29 15:42:06
 * @email sun1920185681@163.com
 */
public class LengthCheckAction implements CheckAction{

    @Override
    public VlidateUtils.ValidateResult check(Annotation annotation, Field field, Object obj) throws IllegalAccessException {
        VlidateUtils.ValidateResult result = null;
        Length type = (Length) annotation;
        field.setAccessible(true);
        String value = (String)field.get(obj);

        if (type.length() != value.length()) {
            result = VlidateUtils.ValidateResult.getError(VlidateUtils.getPackageAndClassName(obj,field),type.msg());
        } else {
            result = VlidateUtils.ValidateResult.getSuccess();
        }
        return result;
    }
}
