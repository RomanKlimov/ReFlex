package ru.itis.reflex.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.reflex.models.User;
import ru.itis.reflex.services.interfaces.AuthService;
import ru.itis.reflex.services.interfaces.CompanyService;
import ru.itis.reflex.services.interfaces.UserService;

@Controller
public class StatisticsPageController {

    private final UserService userService;
    private final CompanyService companyService;
    private final AuthService authService;

    public StatisticsPageController(UserService userService,
                                    CompanyService companyService,
                                    AuthService authService) {
        this.userService = userService;
        this.companyService = companyService;
        this.authService = authService;
    }

    @GetMapping("/profile")
    public String getUserStatisticPage(Model model,  Authentication authentication) {
        User currentUser = authService.getUserByAuthentication(authentication);
        model.addAttribute("currentUser", currentUser);
        return "graph_test_page";
    }

    @GetMapping("/company_stats")
    public String getCompanyStatisticPage(Model model,  Authentication authentication) {
        //for Test
        model.addAttribute("company", companyService.getCompanyByName("SomeCoolCompany"));
        User currentUser = authService.getUserByAuthentication(authentication);
        model.addAttribute("currentUser", currentUser);
        return "graph_test_page";
    }
}
