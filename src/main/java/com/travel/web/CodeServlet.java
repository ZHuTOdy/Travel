package com.travel.web;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

/**
 * @ClassName CodeServlet
 * @Description TODO
 * @Author TOdyZHu
 * @Date 2019-08-01 14:58
 **/
@WebServlet("/code")
public class CodeServlet extends HttpServlet {
    private static String code;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag = request.getParameter("method");
        if (flag.equals("checkCode")) {
            response.getWriter().write(checkCode(request, response));
            return;
        }
        // 服务器通知浏览器不要缓存
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");

        // 在内存中创建一个长80，宽30的图片，默认黑色背景
        // 参数一：长
        // 参数二：宽
        // 参数三：颜色
        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 获取画笔
        Graphics g = image.getGraphics();
        // 设置画笔颜色为灰色
        g.setColor(Color.GRAY);
        // 填充图片
        g.fillRect(0, 0, width, height);

        // 产生4个随机验证码，12Ey
        code = getCheckCode();
        request.getSession().removeAttribute("CHECKCODE_SERVER");
        // 将验证码放入HttpSession中
        request.getSession().setAttribute("CHECKCODE_SERVER", code);

        // 设置画笔颜色为黄色
        g.setColor(Color.YELLOW);
        // 设置字体的小大
        g.setFont(new Font("黑体", Font.BOLD, 24));
        // 向图片上写入验证码
        g.drawString(code, 15, 25);

        // 将内存中的图片输出到浏览器
        // 参数一：图片对象
        // 参数二：图片的格式，如PNG,JPG,GIF
        // 参数三：图片输出到哪里去
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", bo);
        response.getWriter().write(Base64.getEncoder().encodeToString(bo.toByteArray()));
    }

    private String checkCode(HttpServletRequest request, HttpServletResponse response) {
        String ccode = request.getParameter("code");
        if (code.toLowerCase().equals(ccode)) {
            return "1";
        } else {
            return "0";
        }
    }

    private String getCheckCode() {
        String base = "0123456789ABCDEFGabcdefg";
        int size = base.length();
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= 4; i++) {
            // 产生0到size-1的随机值
            int index = r.nextInt(size);
            // 在base字符串中获取下标为index的字符
            char c = base.charAt(index);
            // 将c放入到StringBuffer中去
            sb.append(c);
        }
        return sb.toString();
    }
}
