package com.education.service.edu.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author Mr_W
 * @date 2021/2/16 13:19
 * @description: controller 测试
 */
@RestController
@RequestMapping("test")
@Api(tags = "测试")
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @GetMapping
    @ApiOperation("测试接口")
    public String test() {
        LOGGER.info("asdf[{}]{}{}{}", "reqw", 1, 2, 2);
        return "test";
    }

    @GetMapping("/t")
    @ApiOperation("测试接口")
    public String test(@RequestParam("test") @ApiParam("测试") @NotBlank(message = "测试不能为空") @Validated String test) {
        return "test";
    }

}
