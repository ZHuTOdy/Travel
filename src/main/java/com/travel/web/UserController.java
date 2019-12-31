package com.travel.web;

import com.travel.pojo.ResultInfo;
import com.travel.pojo.User;
import com.travel.pojo.UserModel;
import com.travel.service.impl.UserServiceImpl;
import com.travel.utils.MailUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author TOdyZHu
 * @Date 2019-07-29 11:09
 **/
@Controller
@RequestMapping("/user")
public class UserController {
    private HttpSession session;

    @RequestMapping("/login")
    @ResponseBody
    private String logIn(HttpServletRequest req) {
        try {
            UserServiceImpl service = new UserServiceImpl();
            Object obj = service.Login(req.getParameter("username"), req.getParameter("password"));
            session = req.getSession();
            String flag = String.valueOf(obj);
            if (!flag.equals("0")) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) obj;
                if (map.get("status").equals("N")) {
                    return "2";
                }

                session.setAttribute("username", map.get("username"));
                session.setAttribute("uid", map.get("uid"));
                session.setAttribute("code", map.get("code"));
                return "1";
            }
            return "登录失败，请检查你的账户和密码";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping("/register")
    @ResponseBody
    private String register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserServiceImpl service = new UserServiceImpl();
        UserModel model = new UserModel();
        model.setUserName(request.getParameter("username"));
        model.setPassword(request.getParameter("password"));
        model.setBirthday(request.getParameter("birthday").isEmpty() ? "1970-01-01" : request.getParameter("birthday"));
        model.setEmail(request.getParameter("email"));
        model.setSex(request.getParameter("sex"));
        model.setTelephone(request.getParameter("telephone"));
        model.setName(request.getParameter("name"));
        String code = UUID.randomUUID().toString().replace("-", "");
        model.setCode(code);
        String flag = service.register(model);
        if (flag == "1") {
            MailUtil.sendMail(model.getEmail(), ":)欢迎您注册，请点击链接:http://localhost:8080/travel_war_exploded/user/activateAccount?code=" + model.getCode() + " 激活用户", "邮箱验证");
        }
        return flag;
    }

    @RequestMapping("/logout")
    @ResponseBody
    private String logout(HttpServletRequest request) throws IOException {
        creatSession(request);
        session.setAttribute("username", "");
        session.setAttribute("code", "");
        return "1";
    }

    private void creatSession(HttpServletRequest request) {
        session = request.getSession();
        session.setAttribute("username", "");
        session.setAttribute("code", "");
    }

    @RequestMapping("/activateAccount")
    @ResponseBody
    private String activateAccount(String code, HttpServletResponse response) throws IOException {
        UserServiceImpl service = new UserServiceImpl();
        return service.activateAccount(code);
    }

    @RequestMapping("/showName")
    @ResponseBody
    public User showName(HttpServletRequest request) {
        User u = new User();
        int uid = (Integer) request.getSession().getAttribute("uid");
        u.setUid(uid);
        System.out.println("传入UID：" + u.getUid());
        UserServiceImpl userService = new UserServiceImpl();
        u = userService.showName(u);
        System.out.println("userName + " + u.getName());
        return u;
    }

    @RequestMapping("/exit")
    @ResponseBody
    public ResultInfo exit(HttpServletRequest request) {
        request.getSession().setAttribute("uid", null);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setErrorMsg("200");
        resultInfo.setFlag(true);
        return resultInfo;
    }
}
