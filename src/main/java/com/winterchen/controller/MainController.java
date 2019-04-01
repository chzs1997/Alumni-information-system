package com.winterchen.controller;

/**
 * @Author: liuzipan
 * @Description
 * @Date :18:00 2019/3/2
 * @Modefied By:
 */
import org.springframework.stereotype.Controller;


@Controller
public class MainController {
//
//    @GetMapping("/")
//    public String index(
//            @SessionAttribute(MyWebAppConfigurer.SESSION_KEY) String account,
//            @SessionAttribute("username") String username,
//            Model model) {
//        model.addAttribute("name", account);
//        System.out.println(username);
//        return "Homepage";
//    }
//
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//
//    @PostMapping("/loginPost")
//    public String loginPost(String account,
//                            String password, HttpSession session) {
//        Map<String, Object> map = new HashMap<>();
//        if (!"123456".equals(password)) {
//            map.put("success", false);
//            map.put("message", "密码错误");
//            return "err";
//        }
//        // 设置session
//        session.setAttribute(MyWebAppConfigurer.SESSION_KEY, account);
//        session.setAttribute("username","123456");
//        map.put("success", true);
//        map.put("message", "登录成功");
//        return "Homepage";
//    }
//
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        // 移除session
//        session.removeAttribute(MyWebAppConfigurer.SESSION_KEY);
//        return "redirect:/login";
//    }
}

