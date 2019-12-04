package online.keepon.project.service;

import com.alibaba.fastjson.JSONObject;
import online.keepon.project.oauth.GiteeOauth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author fujie
 * @ 日期 2019-12-03 17:45
 */
@Service
public class GiteeServiceImpl implements BaseOauthService {

    @Autowired
    GiteeOauth giteeOauth;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String authorizeUri() {
        return giteeOauth.authorize();
    }

    @Override
    public String getAccessToken(String code) {
        String token = giteeOauth.accessToken(code);
        ResponseEntity<Object> entity = restTemplate.postForEntity(token, httpEntity(), Object.class);
        Object body = entity.getBody();
        assert body != null;
        String string = body.toString();
        String[] split = string.split("=");
        String accessToken = split[1].toString().split(",")[0];
        return accessToken;
    }

    @Override
    public JSONObject getUserInfo(String accessToken) {
        String userInfo = giteeOauth.userInfo(accessToken);
        ResponseEntity<JSONObject> forEntity = restTemplate.exchange(userInfo, HttpMethod.GET, httpEntity(), JSONObject.class);
        JSONObject body = forEntity.getBody();
        return body;
    }

    public static HttpEntity httpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
        HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);
        return request;
    }
}
