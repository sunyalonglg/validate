package com.sunyalong.validate;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunyalong.validate.annotations.Check;
import com.sunyalong.validate.utils.VlidateUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.Map;

public class ValidateCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        try {
            if (o instanceof HandlerMethod) {
                HandlerMethod handler = (HandlerMethod) o;
                MethodParameter[] methodParameters = handler.getMethodParameters();
                for (MethodParameter methodParameter : methodParameters) {
                    Check parameterAnnotation = methodParameter.getParameterAnnotation(Check.class);
                    if(parameterAnnotation != null){
                        Object paramClazzz = getRequestObject(httpServletRequest,httpServletResponse,methodParameter);
                        VlidateUtils.ValidateResult result = VlidateUtils.toValidate(paramClazzz);
                        if (result.getCode() != 200) {
                            responseError(httpServletRequest,httpServletResponse,JSON.toJSONString(result));
                            return false;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 向客户端返回结果
     *
     * @param req
     * @param res
     * @param msg
     */
    private void responseError(HttpServletRequest req, HttpServletResponse res, String msg) {
        try {
            req.setCharacterEncoding("utf-8");
            res.setCharacterEncoding("utf-8");
            res.setHeader("Content-type", "text/html;charset=utf-8");
            res.getWriter().print(msg);
            res.setStatus(200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得当前方法请求的对象
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param methodParameter
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private Object getRequestObject(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, MethodParameter methodParameter) throws IllegalAccessException, InstantiationException {
        Class<?> parameterType = methodParameter.getParameterType();
        Object paramClazz = parameterType.newInstance();
        Object objct = getObjct(httpServletRequest, httpServletResponse, paramClazz);
        return objct;
    }


    /**
     * 将request的对象映射成一个对象
     *
     * @param request
     * @param response
     * @param obj
     * @return
     */
    public static Object getObjct(HttpServletRequest request, HttpServletResponse response,Object obj) {
        String contentType = request.getContentType();
        String json;
        if (contentType != null && contentType.equals("application/json")) {
            json = "{}";
        } else {
            Map<String, String[]> parameterMap = request.getParameterMap();
            json = JSONObject.toJSONString(parameterMap).replace("[", "").replace("]", "");
        }
        Object parse = JSON.parseObject(json,obj.getClass());
        return parse;
    }
}
