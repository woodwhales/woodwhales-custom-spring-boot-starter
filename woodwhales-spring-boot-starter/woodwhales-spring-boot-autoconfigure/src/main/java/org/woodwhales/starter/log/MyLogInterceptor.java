package org.woodwhales.starter.log;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Objects;

/**
 * @projectName: woodwhales-spring-boot-starter
 * @author: woodwhales
 * @date: 20.3.14 18:40
 * @description:
 */
@Slf4j
public class MyLogInterceptor extends HandlerInterceptorAdapter {

    private static final ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (Objects.nonNull(myLog)) {
            // 记录方法执行起始时间
            startTimeThreadLocal.set(System.currentTimeMillis());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(!(handler instanceof HandlerMethod)) {
            return;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (Objects.nonNull(myLog)) {
            //  获取方法执行起始时间
            long startTime = startTimeThreadLocal.get();
            long expendTime = System.currentTimeMillis() - startTime;

            //  打印参数
            String requestUri = request.getRequestURI();
            String methodName = method.getDeclaringClass().getName() + "#" + method.getName();
            String methodDesc = myLog.desc();
            String parameters = JSON.toJSONString(request.getParameterMap());

            log.info("描述：{}", methodDesc);
            log.info("路径：{}", requestUri);
            log.info("方法：{}", methodName);
            log.info("参数：{}", parameters);
            String requestMethod = request.getMethod();
            if(HttpMethod.POST.matches(requestMethod)||
                    HttpMethod.PATCH.matches(requestMethod)||
                    HttpMethod.PUT.matches(requestMethod)){

                log.info("请求体: {}", JSON.toJSONString(JSON.parse(HttpHelper.getBodyString(request))));
            }
            log.info("耗时：{}", expendTime);
        }
    }

    private String getBodyString(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString().trim();
    }
}
