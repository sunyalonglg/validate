package com.sunyalong.validate.check;

import com.sunyalong.validate.annotations.NotNull;
import com.sunyalong.validate.annotations.Type;
import com.sunyalong.validate.utils.VlidateUtils;
import com.sunyalong.validate.validateinterface.CheckAction;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 *  类型检查动作实现
 *
 * @author sunyalong
 * @version 1.0, 2018-11-29 15:42:06
 * @email sun1920185681@163.com
 */
public class TypeCheckAction implements CheckAction{

    @Override
    public VlidateUtils.ValidateResult check(Annotation annotation, Field field, Object obj) throws IllegalAccessException {
        VlidateUtils.ValidateResult result = null;
        NotNull notnullAnnotation = field.getAnnotation(NotNull.class);
        Type type = (Type) annotation;
        field.setAccessible(true);
        Object value = field.get(obj);

        // 判断是否为空值
        if(notnullAnnotation == null){
            if(value == null){
                return VlidateUtils.ValidateResult.getError(VlidateUtils.getPackageAndClassName(obj,field),"属性为空");
            }
        }

        if (type.type() != value.getClass()) {
            result = VlidateUtils.ValidateResult.getError(VlidateUtils.getPackageAndClassName(obj,field),type.msg());
        } else {
            result = VlidateUtils.ValidateResult.getSuccess();
        }
        return result;
    }
}
