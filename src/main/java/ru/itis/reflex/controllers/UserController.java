package ru.itis.reflex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.reflex.models.Company;
import ru.itis.reflex.models.Key;
import ru.itis.reflex.models.User;
import ru.itis.reflex.services.interfaces.AuthService;
import ru.itis.reflex.services.interfaces.CompanyService;
import ru.itis.reflex.services.interfaces.DepartmentService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/{company}/{department}/user")
    public String apply(@PathVariable("company") String company, @PathVariable("department") String department, Authentication authentication, ModelMap modelMap){
        User user = authService.getUserByAuthentication(authentication);
        Company companyByName = companyService.getCompanyByName(company);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("company", companyByName);
        modelMap.addAttribute("department", department);

        return "user";
    }


}
