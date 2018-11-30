# validate web应用参数校验框架
   该框架主要实现的功能有, HTTP 请求的参数自动校验参数, 已经与Spring框架进行了无缝对接, 只需要配置一个拦截器就可以使用. 
框架还提供了手动验证方法, 使用方法参考 TestValidate类于User,即可轻松掌握, validate是一个轻量级的框架,减少了分装, 但又不缺乏其强大的功能, 实现原理也比较简单感兴趣的童鞋, 可以进行研究探讨. 可以了解其中的原理, 如有任何不太了解的可与本人邮箱联系. 

# validate 安装与打包

使用git下载本工程, 用IDEA打开, 点击右边的maven, 跳过测试, 打包完成后就已经在maven本地仓库了.

# validate 与Spring 框架集成
打开需要集成的工程, 在pom文件添加当前打包的这个工程的maven依赖。

validate 该框架内置一个拦截器, 直接在应用程序中配置这个拦截器就可以实现,具体参考以下代码

    @Component
    public class InterceptorConfig implements WebMvcConfigurer {
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new ValidateCheckInterceptor()).addPathPatterns("/**").excludePathPatterns("/a.do");
        }
    }

# 注意事项

1. 如果需要修改返回的数据结构可修改 ValidateCheckInterceptor 拦截器的               responseError(httpServletRequest,httpServletResponse,JSON.toJSONString(result));
    行这行代码
    
2. 当前端使用application/json 的请求方式提交的时候校验框架不会起任何作用, 因为如果读取了参数流就会销毁, 到接口的后也是拿不到任何数据的, 如遇到此         类问题 需要在接口的第一行,使用手动验证的方式进行实现具体参考 TestValidate 类中的方法. 

3. 如果需要对本框架继续添加更多的注解也是可以的， 具体查看VlidateUtils 类进行添加。



    Email: sun1920185681@163.com
    QQ   : 1920185681
