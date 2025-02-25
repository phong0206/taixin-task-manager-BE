package io.github.phongnv.restful_spring_boot.infrastruture.configurations;

import io.github.phongnv.restful_spring_boot.infrastruture.dtos.response.ResponseData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Date;

@ControllerAdvice
public class ResponseWrapperAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, org.springframework.http.MediaType selectedContentType,
                                  Class selectedConverterType, org.springframework.http.server.ServerHttpRequest request,
                                  org.springframework.http.server.ServerHttpResponse response) {

        if (body instanceof ResponseData<?>) {
            return body;
        }

        HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();

        return ResponseData.builder()
                .timestamp(new Date())
                .status(((ServletServerHttpRequest) request).getServletRequest().getAttribute("javax.servlet.error.status_code") != null
                        ? (int) ((ServletServerHttpRequest) request).getServletRequest().getAttribute("javax.servlet.error.status_code")
                        : HttpStatus.OK.value())
                .data(body)
                .method(servletRequest.getMethod())
                .path(servletRequest.getRequestURI())
                .build();
    }
}
