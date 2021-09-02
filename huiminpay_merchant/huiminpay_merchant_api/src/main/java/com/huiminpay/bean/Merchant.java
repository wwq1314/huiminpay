package com.huiminpay.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "商铺实体类对象")
@TableName("merchant")
public class Merchant implements Serializable {

    @ApiModelProperty("主键id")
    private Long id;
    @ApiModelProperty("商铺名称")
    private String merchantName;
    @ApiModelProperty("商铺编号")
    private String merchantNo;
    @ApiModelProperty("商铺地址")
    private String merchantAddress;
    @ApiModelProperty("商铺类型")
    private String merchantType;
    @ApiModelProperty("商铺营业执照")
    private String businessLicensesImg;
    @ApiModelProperty("商铺法人身份证正面")
    private String idCardFrontImg;
    @ApiModelProperty("商铺法人身份证反面")
    private String idCardAfterImg;
    @ApiModelProperty("商铺负责人名称")
    private String username;
    @ApiModelProperty("商铺负责人联系方式")
    private String mobile;
    @ApiModelProperty("商铺联系人地址")
    private String contactsAddress;
    @ApiModelProperty("商铺审核状态")
    private String auditStatus;
    @ApiModelProperty("租户id")
    private Long tenantId;
}
