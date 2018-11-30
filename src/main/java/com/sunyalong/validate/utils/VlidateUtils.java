package com.sunyalong.validate.utils;

import com.sunyalong.validate.annotations.BetweenLength;
import com.sunyalong.validate.annotations.Length;
import com.sunyalong.validate.annotations.NotNull;
import com.sunyalong.validate.annotations.Type;
import com.sunyalong.validate.check.BetweenLengthCheckAction;
import com.sunyalong.validate.check.LengthCheckAction;
import com.sunyalong.validate.check.NotNullCheckAction;
import com.sunyalong.validate.check.TypeCheckAction;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 验证工具类
 *
 * @author sunyalong
 * @version 1.0, 2018-11-29 15:42:06
 * @email sun1920185681@163.com
 */
public class VlidateUtils {

    /**
     * 本框架注解判断的顺序
     */
    private static List<Class> annotationsSort = new ArrayList<>();


    /**
     * 验证是否正确的结果方法
     *
     * @param obj 需要验证的对象
     * @return 返回验证结果.
     */
    public static ValidateResult toValidate(Object obj) throws IllegalAccessException {
        // 设置返回结果
        ValidateResult result = new ValidateResult();
        result.setCode(200);
        result.setMsg("当前类没有打注解");
        if (obj == null) {
            return result;
        }

        // 获得所有属性
        Field[] fields = obj.getClass().getDeclaredFields();


        // 遍历注解顺序
        for (Class annotationClass : annotationsSort) {
            for (Field field : fields) {
                Annotation annotation = field.getAnnotation(annotationClass);
                if (annotation == null) {
                    continue;
                }
                result = getValidateResult(annotation, field, obj);
                if (result.getCode() != 200) {
                    return result;
                }
            }
        }
        return result;
    }


    /**
     * 计算获得验证成功或失败的方法
     *
     * @param annotation 当前注解
     * @param field      当前属性
     * @param obj        当前对象
     * @return 校验结果
     */
    private static ValidateResult getValidateResult(Annotation annotation, Field field, Object obj) throws IllegalAccessException {
        ValidateResult result = null;
        if (annotation.annotationType() == NotNull.class) {
            NotNullCheckAction notNullCheckAction = new NotNullCheckAction();
            result = notNullCheckAction.check(annotation, field, obj);
        } else if (annotation.annotationType() == Type.class) {
            TypeCheckAction typeCheckAction = new TypeCheckAction();
            result = typeCheckAction.check(annotation, field, obj);
        } else if (annotation.annotationType() == Length.class) {
            LengthCheckAction lengthCheckAction = new LengthCheckAction();
            result = lengthCheckAction.check(annotation, field, obj);
        } else if (annotation.annotationType() == BetweenLength.class) {
            BetweenLengthCheckAction betweenLengthCheckAction = new BetweenLengthCheckAction();
            result = betweenLengthCheckAction.check(annotation, field, obj);
        }
        return result;
    }



    /**
     * 获得一个类的属性和包名称
     *
     * @param obj
     * @param field
     * @return
     */
    public static String getPackageAndClassName(Object obj,Field field){
        Class<?> aClass = obj.getClass();
        return  aClass.getPackage()+"."+aClass.getName()+"#"+field.getName();
    }


    /**
     * 验证返回的结果
     */
    public static class ValidateResult {

        /**
         * 验证后返回的状态码 200 为正常
         */
        private int code;

        /**
         * 验证后返回的结果
         */
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        /**
         * 获得验证成功信息
         *
         * @return
         */
        public static ValidateResult getSuccess() {
            ValidateResult result = new ValidateResult();
            result.setCode(200);
            result.setMsg("验证成功");
            return result;
        }

        /**
         * 获得验证失败信息
         *
         * @return
         */
        public static ValidateResult getError(String ... msgs) {
            StringBuilder sb = new StringBuilder();
            for (String msg : msgs) {
                sb.append(msg);
            }
            ValidateResult result = new ValidateResult();
            result.setCode(500);
            result.setMsg(sb.toString());
            return result;
        }
    }

    static {
        annotationsSort.add(NotNull.class);
        annotationsSort.add(Type.class);
        annotationsSort.add(Length.class);
        annotationsSort.add(BetweenLength.class);
    }
}
