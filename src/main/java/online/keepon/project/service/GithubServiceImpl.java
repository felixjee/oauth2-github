package online.keepon.project.service;

import com.alibaba.fastjson.JSONObject;
import online.keepon.project.oauth.GithubOauth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static online.keepon.project.service.GiteeServiceImpl.httpEntity;

/**
 * @author fujie
 * @ 日期 2019-12-03 17:34
 */
@Service
public class GithubServiceImpl implements BaseOauthService {
    @Autowired
    GithubOauth githubOauth;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String authorizeUri() {
        return githubOauth.authorize();
    }

    @Override
    public String getAccessToken(String code) {
        String token = githubOauth.accessToken(code);
        ResponseEntity<Object> forEntity = restTemplate.exchange(token, HttpMethod.GET, httpEntity(), Object.class);
        String[] split = Objects.requireNonNull(forEntity.getBody()).toString().split("=");
        String accessToken = split[1].split(",")[0];
        return accessToken;
    }

    @Override
    public JSONObject getUserInfo(String accessToken) {
        String userInfo = githubOauth.userInfo(accessToken);
        ResponseEntity<JSONObject> entity = restTemplate.exchange(userInfo, HttpMethod.GET, httpEntity(), JSONObject.class);
        JSONObject body = entity.getBody();
        return body;
    }
}
