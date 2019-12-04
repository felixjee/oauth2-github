package online.keepon.project.oauth;

import org.springframework.stereotype.Component;

/**
 * @author fujie
 * @ 日期 2019-12-03 15:31
 */
@Component
public class GithubOauth implements BaseOauth {

    private static final String GITHUB_CLIENT_ID = "3d00a49f88fde305975c";
    private static final String GITHUB_CLIENT_SECRET = "4e0d1c0410057c34cdec3b58c1e21e0521b64b89";

    /**
     * 登陆授权类型
     */
    @Override
    public String authorize() {
        return "https://github.com/login/oauth/authorize?client_id=" + GITHUB_CLIENT_ID + "&scope=user,public_repo";
    }

    @Override
    public String accessToken(String code) {
        return "https://github.com/login/oauth/access_token?client_id=" + GITHUB_CLIENT_ID + "&client_secret=" + GITHUB_CLIENT_SECRET + "&code=" + code;
    }

    @Override
    public String userInfo(String accessToken) {
        return "https://api.github.com/user?access_token=" + accessToken + "&scope=public_repo%2Cuser&token_type=bearer";
    }
}
