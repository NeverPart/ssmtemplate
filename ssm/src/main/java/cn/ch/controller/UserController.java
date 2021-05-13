package cn.ch.controller;

import cn.ch.entity.User;
import cn.ch.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户的控制层
 *
 * @author ch
 * @date 2021/5/13 14:26
 */
@Controller
@RequestMapping("/user")
public class UserController {

    //注入service
    @Autowired
    private IUserService IUserService;

    /**
     * @return java.lang.String
     * @Author ch
     * @Description 登录
     * @Date 2021/5/13 14:29
     * @Param username:
     * @Param password:
     * @Param model:
     **/
    @RequestMapping(value = "/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        User user = IUserService.login(username);

        if (user != null) {
            if (user.getPassword().equals(password)) {
                //登录成功
                return "page/page";
            } else {
                model.addAttribute("message", "登录失败");
                return "page/loginInfo";
            }
        } else {
            model.addAttribute("message", "你输入的用户名或密码有误");
            return "page/loginInfo";
        }
    }

    /**
     * @return java.lang.String
     * @Author ch
     * @Description 回到登录页
     * @Date 2021/5/13 14:30
     * @Param
     **/
    @RequestMapping(value = "/index")
    public String index() {

        return "index";
    }
}
