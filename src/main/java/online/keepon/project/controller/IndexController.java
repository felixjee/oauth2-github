package online.keepon.project.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import online.keepon.project.enums.LoginType;
import online.keepon.project.model.UserInfo;
import online.keepon.project.service.GiteeServiceImpl;
import online.keepon.project.service.GithubServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fujie
 * @ 日期 2019-12-02 18:12
 */
@Controller
public class IndexController {

    @Autowired
    private GiteeServiceImpl giteeService;

    @Autowired
    GithubServiceImpl githubService;


    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }


    @GetMapping(value = "/oauth/login")
    public String login(@RequestParam("type") String type) {
        if (type.toUpperCase().equals(LoginType.GITHUB.toString())) {
            return "redirect:" + githubService.authorizeUri();
        }
        return "redirect:" + giteeService.authorizeUri();
    }

    @GetMapping(value = "/github/callback")
    public String callback(HttpServletRequest request, Model model) {
        String code = request.getParameter("code");
        JSONObject userInfo = githubService.getUserInfo(githubService.getAccessToken(code));
        UserInfo info = JSON.parseObject(userInfo.toString(), UserInfo.class);
        model.addAttribute("github", info);
        return "index";
    }

    @GetMapping(value = "/gitee/callback")
    public String giteeCallBack(HttpServletRequest request, Model model) {
        String code = request.getParameter("code");
        JSONObject userInfo = giteeService.getUserInfo(giteeService.getAccessToken(code));
        UserInfo info = JSON.parseObject(userInfo.toString(), UserInfo.class);
        model.addAttribute("gitee", info);
        return "index";
    }
}
