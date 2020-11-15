package com.nuon.goamall.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuon.goamall.exception.ParameterException;
import com.nuon.goamall.model.User;
import com.nuon.goamall.repository.UserRepository;
import com.nuon.goamall.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class WxAuthenticationService {

    @Autowired
    private ObjectMapper mapper;

    @Value("${wx.code2session}")
    private String code2SessionUrl;

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.appsecret}")
    private String appsecret;

    @Autowired
    private UserRepository userRepository;

    public String code2Session(String code) {
        String url = MessageFormat.format(this.code2SessionUrl, this.appid, this.appsecret, code);
        RestTemplate rest = new RestTemplate();
        Map<String, Object> session = new HashMap<>();
        String sessionText = rest.getForObject(url, String.class);
        try {
            session = mapper.readValue(sessionText, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return registerUser(session);
    }

    private String registerUser(Map<String, Object> session) {
        String openid = (String) session.get("openid");
        if (openid == null) {
            throw new ParameterException(20004);
        }

        Optional<User> userOptional = this.userRepository.findByOpenid(openid);
        if(userOptional.isPresent()){
            // 账号已经存在，直接获取token
            return JwtToken.makeToken(userOptional.get().getId());
        }

        // 账号不存在，先插入用户数据，并获取对应token
        User user = User.builder()
                .openid(openid)
                .build();
        userRepository.save(user);
        return JwtToken.makeToken(user.getId());
    }
}
