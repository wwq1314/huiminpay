package com.huiminpay.api;

import com.huiminpay.api.dto.MerchantDto;
import com.huiminpay.bean.Merchant;

public interface IMerchantService {

    public Merchant queryMerchantById(Long id);

    /**
     * 商户注册
     * @param merchantDto
     * @return
     */
    MerchantDto createMerchant(MerchantDto merchantDto);
}
