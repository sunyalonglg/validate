package com.sunyalong.validate;

import com.sunyalong.validate.utils.VlidateUtils;

public class TestValidate {

    public static void main(String[] args) throws IllegalAccessException {
        User user = new User();
        VlidateUtils.ValidateResult validateResult = VlidateUtils.toValidate(user);

        System.out.println(validateResult.getCode()+"------------"+validateResult.getMsg());

    }
}
