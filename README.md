# validate web应用参数校验框架
该框架主要实现的功能有, http 请求的参数自动校验, 现目前需要在每个接口的开始前调用
VlidateUtils.toValidate方法进行手动人工校验, 后期会集成在SpringBoot中, 于Spring框架进行无缝对接, 目前框架使用方法参考 TestValidate类于User,可以了解其中的原理, 如有任何不太了解的可与本人邮箱联系. 

# validate 验证框架与Spring 框架集成

validate 该框架内置一个拦截器, 直接在应用程序中配置这个拦截器就可以实现, 验证功能.

    @Component
    public class InterceptorConfig implements WebMvcConfigurer {
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new ValidateCheckInterceptor()).addPathPatterns("/**").excludePathPatterns("/a.do");
        }
    }

# 注意事项

1. 如果需要修改返回的数据结构可修改 ValidateCheckInterceptor 拦截器的下面这行代码将result修改即可.
    responseError(httpServletRequest,httpServletResponse,JSON.toJSONString(result));
2. 当前端使用application/json 的请求方式提交表单的时候校验框架不会起任何作用, 应为如果读取了参数流就会销毁, 到接口的时候也是拿不到任何数据的, 如遇到此    类问题 需要在接口的第一行使用手动验证的方式进行实现具体参考 TestValidate 类中的方法. 




    Email: sun1920185681@163.com
    QQ   : 1920185681
