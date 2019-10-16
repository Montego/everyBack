//package com.every.every.config.customHandlers;
//
//import com.every.every.entity.User;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class CustomSimpleUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws IOException {
//
//        clearAuthenticationAttributes(request);
//        response.setHeader("Content-Type", "application/json;charset=UTF-8");
//        ObjectMapper om = new ObjectMapper();
//        User user = (User) authentication.getPrincipal();
//        response.getWriter().print(om.writeValueAsString(user));
//
//    }
//}
