package com.sunyalong.validate.check;

import com.sunyalong.validate.annotations.NotNull;
import com.sunyalong.validate.utils.VlidateUtils;
import com.sunyalong.validate.validateinterface.CheckAction;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 *  为空检查动作实现
 *
 * @author sunyalong
 * @version 1.0, 2018-11-29 15:42:06
 */
public class NotNullCheckAction implements CheckAction{

    @Override
    public VlidateUtils.ValidateResult check(Annotation annotation, Field field, Object obj) throws IllegalAccessException {
        NotNull notNull = (NotNull) annotation;
        VlidateUtils.ValidateResult result = null;
        field.setAccessible(true);
        Object value = field.get(obj);
        if (value == null) {
            result = VlidateUtils.ValidateResult.getError(VlidateUtils.getPackageAndClassName(obj,field),notNull.msg());
        } else {
            result = VlidateUtils.ValidateResult.getSuccess();
        }
        return result;
    }
}
