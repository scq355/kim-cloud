package com.kim.providerreactiveweb.controller;

import com.kim.providerreactiveweb.service.StreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("stream")
public class StreamController {

    @Autowired
    private StreamingService streamingService;

    @GetMapping(value = "video/{title}", produces = "video/mp4")
    public Mono<Resource> getVideos(@PathVariable String title,
                                    @RequestHeader("Range") String range) {
        System.out.println("Range: " + range);
        return streamingService.getVideo(title);
    }
}
