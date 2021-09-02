package com.huiminpay.service;

import com.huiminpay.api.ISmaService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsServiceImpl implements ISmaService {

    @Value("${sms.url}")
    String url;

    @Value("${sms.effectiveTime}")
    String effectiveTime;

    @Value("sms.name")
    String name;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String sendSms(String phone) {
        //创建RestTemplate对象进行远程调用
        RestTemplate restTemplate = new RestTemplate();
        //定义请求路径
        String smsurl = url+"generate?effectiveTime="+effectiveTime+"&name"+name;

        HashMap<String, Object> body = new HashMap<>();
        body.put("moblie",phone);

        //指定请求的格式类型为json（设置请求头有信息）
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        //将要发送的信息设置请求头中进行发送
        HttpEntity<HashMap<String, Object>> hashMapHttpEntity = new HttpEntity<>(body, httpHeaders);

        //通过RestTemplate进行远程调用.ResponseEntity<Map>短信接口返回的数据结果就封装在Map对象中
        ResponseEntity<Map> exchange = restTemplate.exchange(smsurl, HttpMethod.POST, hashMapHttpEntity, Map.class);
        //获取验证码对应的key值返回给前端进行保存
        if (exchange != null){
            Map resultMap = exchange.getBody();
            Object object = resultMap.get("result");
            if (object == null){
                throw new RuntimeException("验证码获取失败");
            }
            Map<String, String> result = (Map<String, String>) object;
            String key = result.get("key");
            return key;
        }
        return null;
    }

    @Override
    public String verify(String verificationKey, String verifiyCode) {
        String verifiyUrl = url + "/verify?name=sms&verificationCode=" + verifiyCode + "&verificationKey=" + verificationKey;
        Map responseMap = null;
        try {
            //请求校验验证码
            ResponseEntity<Map> exchange = restTemplate.exchange(verifiyUrl, HttpMethod.POST,
                    HttpEntity.EMPTY, Map.class);
            responseMap = exchange.getBody();
        } catch (RestClientException e) {
            e.printStackTrace();
            throw new RuntimeException("验证码错误");
        }
        if (responseMap == null || responseMap.get("result") == null || !(Boolean)
                responseMap.get("result")) {
            throw new RuntimeException("验证码错误");
        }
        return null;
    }
}
