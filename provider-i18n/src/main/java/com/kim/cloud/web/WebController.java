package com.kim.cloud.web;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("web")
public class WebController {

    @Resource
    private MessageSource messageSource;

    @GetMapping("i18n")
    public String i18n(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        System.out.println(locale.getCountry());
        return messageSource.getMessage("A00002", null, LocaleContextHolder.getLocale());
    }
}
