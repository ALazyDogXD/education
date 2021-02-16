package com.knife.serviceedu.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr_W
 * @date 2021/2/16 13:19
 * @description: controller 测试
 */
@RestController
@RequestMapping("test")
@Api("测试")
public class TestController {

    @GetMapping
    @ApiOperation("测试接口")
    public String test() {
        return "test";
    }

}
