package com.huiminpay.controller;

import com.huiminpay.api.IMerchantService;
import com.huiminpay.api.ISmaService;
import com.huiminpay.api.dto.MerchantDto;
import com.huiminpay.bean.Merchant;
import com.huiminpay.vo.MerchantVo;
import io.swagger.annotations.*;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

@Api(value = "商铺应用API接口",description = "商铺应用API接口，包含增删改查功能")
@RestController
@RequestMapping("merchant")
public class MerchantController {

    @Reference
    IMerchantService merchantService;

    @Reference
    ISmaService iSmaService;

    @GetMapping("/queryMerchant/{merchantId}/{merchantName}")
    @ApiOperation(value = "根据商铺id获取信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "merchantId",value = "商铺id",required = true,dataType = "long"),
            @ApiImplicitParam(name = "merchantName",value = "商铺名称",required = false,dataType = "string")
         }
    )
    public Merchant queryMerchant(@PathVariable(value = "merchantId") Long id,
                                  @PathVariable(value = "merchantName") String name){
        return merchantService.queryMerchantById(id);
    }

    @PostMapping("/query")
    @ApiOperation("根据传递的merchant信息返回结果")
    public String query(Merchant merchant){
        return merchant.toString();
    }



    @ApiImplicitParam(name = "phone",value = "手机号",required = true,paramType = "path")
    @GetMapping("/sendPhone/{phone}")
    public String sendSms(@PathVariable("phone") String phone){
        //调用业务层代码返回key
        return iSmaService.sendSms(phone);
    }

    @GetMapping("/registerMerchant")
    public MerchantVo registerMerchant(@RequestBody MerchantVo merchantVo){
        //校验验证码
        iSmaService.verify(merchantVo.getVerifiykey(),merchantVo.getVerifiyCode());
        //商户的注册
        MerchantDto merchantDto = new MerchantDto();
        merchantDto.setMobile(merchantDto.getMobile());
        merchantService.createMerchant(merchantDto);
        return merchantVo;
    }
}
