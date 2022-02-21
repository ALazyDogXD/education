package com.education.service.sms.web;

import com.education.service.base.entity.ResponseMsg;
import com.education.service.sms.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author ALazyDogXD
 * @date 2022/2/22 1:43
 * @description 短信相关接口
 */

@Api(tags = "短信相关接口")
@RestController
@RequestMapping("sms")
@RefreshScope
public class SmsController {

    @Resource
    private SmsService smsService;

    @PostMapping("authCode/{phone}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号")
    })
    public ResponseMsg sendAuthCode(@PathVariable @ApiParam("验证码") @Validated @Pattern(regexp = "(1\\d{10})") String phone) {
        smsService.sendAuthCode(phone);
        return ResponseMsg.success("发送成功");
    }

}
