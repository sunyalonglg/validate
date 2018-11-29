package com.sunyalong.validate;

import com.sunyalong.validate.annotations.BetweenLength;
import com.sunyalong.validate.annotations.Length;
import com.sunyalong.validate.annotations.NotNull;
import com.sunyalong.validate.annotations.Type;

public class User {

    @NotNull(msg = "用户名不能为空")
    @Type(type = String.class)
    private String username = "zhangsan";

    @Length(length = 1)
    private String sex = "男";

    @BetweenLength(min = 1,max = 150)
    private int age = 150;
}
