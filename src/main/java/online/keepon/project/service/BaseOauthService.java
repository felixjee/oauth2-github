package online.keepon.project.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author fujie
 * @ 日期 2019-12-03 17:46
 */
public interface BaseOauthService {
    String authorizeUri();

    String getAccessToken(String code);

    JSONObject getUserInfo(String accessToken);

}
