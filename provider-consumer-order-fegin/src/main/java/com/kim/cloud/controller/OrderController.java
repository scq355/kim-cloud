package com.kim.cloud.controller;

import com.kim.cloud.apis.PayFeignApi;
import com.kim.cloud.entities.PayDTO;
import com.kim.cloud.resp.ResultData;
import com.kim.cloud.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class OrderController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/consumer/pay/add")
    public ResultData<?> addOrder(PayDTO payDTO) {
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping("/consumer/pay/get/{id}")
    public ResultData<?> getPayInfo(@PathVariable("id") Integer id) {
        try {
            return payFeignApi.getPayInfo(id);
        } catch (Exception e) {
            log.error("接口超时", e);
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
    }

    @GetMapping(value = "/consumer/pay/get/info")
    private String getInfoByConsul() {
        return payFeignApi.getPayInfoSLB();
    }

    @Resource
    private DiscoveryClient discoveryClient;


    @GetMapping("/consumer/discovery")
    public String discovery() {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info(element);
        }

        log.info("===================================");

        List<ServiceInstance> instances = discoveryClient.getInstances("provider-payment");
        for (ServiceInstance element : instances) {
            log.info("{}\t{}\t{}\t{}", element.getServiceId(), element.getHost(), element.getPort(), element.getUri());
        }

        return instances.get(0).getServiceId() + ":" + instances.get(0).getPort();
    }

}
