package ru.itis.reflex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.reflex.models.User;
import ru.itis.reflex.security.Role.Role;
import ru.itis.reflex.services.interfaces.AuthService;
import ru.itis.reflex.services.interfaces.CompanyService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private CompanyService companyService;

    @GetMapping("/signIn")
    public String root(HttpServletRequest request, Authentication authentication){
        if (authentication != null){
            User user = authService.getUserByAuthentication(authentication);
            request.getSession().setAttribute("user", user);
            if (user.getRole().equals(Role.ADMIN)){
                String companyName = companyService.getCompanyByHead(user).getName();

                return "redirect:/"+companyName+"/admin";
            }
        }
        return "redirect:/index";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Authentication authentication, HttpServletRequest request){
        if (authentication != null){
            request.getSession().invalidate();
        }
        return "redirect:/index";
    }
}
