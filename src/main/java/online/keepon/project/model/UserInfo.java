package online.keepon.project.model;

import lombok.Data;

/**
 * @author fujie
 * @ 日期 2019-12-03 20:15
 */
@Data
public class UserInfo {
    private Integer id;
    private String avatarUrl;
    private String login;
    private String bio;
    private String createdAt;
    private String htmlUrl;
}
