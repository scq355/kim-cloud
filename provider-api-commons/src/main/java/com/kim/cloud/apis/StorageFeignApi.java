package com.kim.cloud.apis;

import com.kim.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "provider-storage")
public interface StorageFeignApi {
    /**
     * 扣减库存
     */
    @PostMapping(value = "/storage/decrease")
    ResultData<?> decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
