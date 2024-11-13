package com.kim.cloud.controller;

import com.alibaba.fastjson2.JSON;
import com.kim.cloud.entities.Pay;
import com.kim.cloud.entities.PayDTO;
import com.kim.cloud.resp.ResultData;
import com.kim.cloud.resp.ReturnCodeEnum;
import com.kim.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@Tag(name = "支付微服务模块",description = "支付CRUD")
public class PayController {
    @Resource
    private PayService paymentService;


    @PostMapping("/pay/add")
    @Operation(summary = "新增",description = "新增支付流水方法,json串做参数")
    public ResultData<String> add(@RequestBody Pay pay) {
        log.info(JSON.toJSONString(pay));
        int add = paymentService.add(pay);
        return ResultData.success("插入成功：" + add);
    }

    @DeleteMapping(value = "/pay/del/{id}")
    @Operation(summary = "删除",description = "删除支付流水方法")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
        return ResultData.success(paymentService.delete(id));
    }

    @PutMapping("/pay/update")
    @Operation(summary = "修改",description = "修改支付流水方法")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        int update = paymentService.update(pay);
        return ResultData.success("修改成功：" + update);
    }

    @GetMapping("/pay/get/{id}")
    @Operation(summary = "按照ID查流水",description = "查询支付流水方法")
    public ResultData<Pay> getPayById(@PathVariable("id") Integer id) {
//        try {
//            TimeUnit.SECONDS.sleep(62);
//        } catch (InterruptedException e) {
//            log.error("超时", e);
//        }
        if (id < 0) throw new RuntimeException("id 不能为负数");
        return ResultData.success(paymentService.getById(id));
    }

    @GetMapping("/pay/list")
    @Operation(summary = "获取流水列表",description = "查询支付流水列表方法")
    public ResultData<List<Pay>> getPayList() {
        return ResultData.success(paymentService.getAll());
    }

    @GetMapping(value = "/pay/err")
    public ResultData<Integer> getPayError() {
        int i = 200;

        try {
            log.info("com in pay error test");
            int age = i / 0;
        } catch (Exception e) {
            log.error("com in pay error", e);
            ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return ResultData.success(i);
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("/pay/get/info")
    public String getInfoByConsul(@Value("${kim.info}") String info) {
        return info + "\t" + port;
    }

}
