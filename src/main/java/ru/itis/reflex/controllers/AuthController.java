package ru.itis.reflex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.reflex.models.User;
import ru.itis.reflex.security.Role.Role;
import ru.itis.reflex.services.interfaces.AuthService;
import ru.itis.reflex.services.interfaces.CompanyService;
import ru.itis.reflex.services.interfaces.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @GetMapping("/signIn")
    public String root(HttpServletRequest request, Authentication authentication){
        //TODO switchcase
        if (authentication != null){
            User user = authService.getUserByAuthentication(authentication);
            request.getSession().setAttribute("user", user);
            if (user.getRole().equals(Role.ADMIN)){
                String companyName = companyService.getCompanyByHead(user).getName();
//                return "redirect:/"+companyName+"/admin";
                return "redirect:/"+"company_stats";
            }
            if (user.getRole().equals(Role.BOSS)) {
                String companyName = user.getCompany().getName();
                return "redirect:/"+companyName+"/boss";
            }
            if (user.getRole().equals(Role.USER)) {
                String companyName = user.getCompany().getName();
                String departmentName = user.getDepartment().getName();
                return "redirect:/"+"profile";
//                return "redirect:/"+companyName+"/" + departmentName + "/user";
            }
        }
        return "redirect:/index";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "signIn";
    }

    @GetMapping("/logout")
    public String logout(Authentication authentication, HttpServletRequest request){
        if (authentication != null){
            request.getSession().invalidate();
        }
        return "redirect:/index";
    }


}
