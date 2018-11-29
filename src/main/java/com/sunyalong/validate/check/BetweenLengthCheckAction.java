package com.sunyalong.validate.check;

import com.sunyalong.validate.annotations.BetweenLength;
import com.sunyalong.validate.utils.VlidateUtils;
import com.sunyalong.validate.validateinterface.CheckAction;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 *  区间判断实现
 *
 * @author sunyalong
 * @version 1.0, 2018-11-29 15:42:06
 */
public class BetweenLengthCheckAction implements CheckAction{

    @Override
    public VlidateUtils.ValidateResult check(Annotation annotation, Field field, Object obj) throws IllegalAccessException {
        VlidateUtils.ValidateResult result = null;
        BetweenLength type = (BetweenLength) annotation;
        field.setAccessible(true);
        Object value = field.get(obj);

        // 判断是否为字符串
        if(value.getClass() == String.class){
            String stringValue = (String) value;
            if(stringValue.length() > type.min() && stringValue.length() < type.max()){
                // 验证成功
                result = VlidateUtils.ValidateResult.getSuccess();
            }else{
//                验证失败
                result = VlidateUtils.ValidateResult.getError(VlidateUtils.getPackageAndClassName(obj,field),type.msg());
            }
            return result;
        }

        // 判断是否为数字
        if(value instanceof  Number){
            Number numberValue = (Number) value;

            if (numberValue.doubleValue() >= type.min() && numberValue.doubleValue() <= type.max()) {
                // 验证成功
                result = VlidateUtils.ValidateResult.getSuccess();
            }else{
//                验证失败
                result = VlidateUtils.ValidateResult.getError(VlidateUtils.getPackageAndClassName(obj,field),type.msg());
            }
        }
        return result;
    }
}
