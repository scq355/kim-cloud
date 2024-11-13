package com.kim.cloud.apis;

import com.kim.cloud.fallback.PayFeignSentinelApiFallBack;
import com.kim.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "provider-payment-ali", fallback = PayFeignSentinelApiFallBack.class)
public interface PayFeignSentinelApi {
    @GetMapping("/pay/nacos/get/{orderNo}")
    ResultData<?> getPayByOrderNo(@PathVariable("orderNo") String orderNo);
}
