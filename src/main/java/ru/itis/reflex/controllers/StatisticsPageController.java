package ru.itis.reflex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.reflex.models.MoodData;
import ru.itis.reflex.models.User;
import ru.itis.reflex.services.interfaces.AuthService;
import ru.itis.reflex.services.interfaces.CompanyService;
import ru.itis.reflex.services.interfaces.MoodDataService;
import ru.itis.reflex.services.interfaces.UserService;

import java.util.Calendar;
import java.util.Date;

@Controller
public class StatisticsPageController {

    @Autowired
    private MoodDataService moodDataService;

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
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        MoodData lastMoodByUser = moodDataService.getLastMoodByUser(currentUser);
        //костыль
        Date date = new Date();
        if (lastMoodByUser==null){
            date = new Date(System.currentTimeMillis());
        } else date = moodDataService.getLastMoodByUser(currentUser).getDate();
        cal1.setTime(date);
        Date currentDate = new Date(System.currentTimeMillis());
        cal2.setTime(currentDate);
        boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        System.out.println(sameDay);
        model.addAttribute("isSameDay", sameDay);
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
