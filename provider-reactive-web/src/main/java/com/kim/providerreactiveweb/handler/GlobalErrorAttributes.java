package com.kim.providerreactiveweb.handler;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.HashMap;
import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest webRequest, ErrorAttributeOptions options) {
        HashMap<String, Object> map = new HashMap<>();
        Throwable error = getError(webRequest);
        map.put("message", error.getMessage());
        map.put("endpoint_url", webRequest.path());
        return map;
    }
}
