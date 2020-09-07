package com.example.wechaterweima.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: meihao
 * @CreateDate: 2020/9/1 13:23
 */

@RestController
@Slf4j
public class ErweimaController {
    private final String ID_CARD = "421127199508240431";

    @RequestMapping("/check")
    public String check(String idCard){
        log.info("身份证号检查，idCard： " + idCard);
        if (StringUtils.isEmpty(idCard)){
            return "0"; // 凭证码或证件号为空
        }
        if (!ID_CARD.equals(idCard)){
            return "1"; // 证件号码有误，请重输
        }
        return "2"; // 检查通过
    }
}
