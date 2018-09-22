package ru.itis.reflex.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.reflex.services.interfaces.CompanyService;
import ru.itis.reflex.services.interfaces.UserService;

@Controller
public class StatisticsPageController {

    private final UserService userService;
    private final CompanyService companyService;

    public StatisticsPageController(UserService userService,
                                    CompanyService companyService) {
        this.userService = userService;
        this.companyService = companyService;
    }

    @GetMapping("/user_stats")
    public String getUserStatisticPage() {
        return "graph_test_page";
    }

    @GetMapping("/company_stats")
    public String getCompanyStatisticPage(Model model) {
        //for Test
        model.addAttribute("company", companyService.getCompanyByName("SomeCoolCompany"));
        return "graph_test_page";
    }
}
