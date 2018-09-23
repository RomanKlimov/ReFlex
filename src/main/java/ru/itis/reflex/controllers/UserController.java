package ru.itis.reflex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.reflex.models.Company;
import ru.itis.reflex.models.Key;
import ru.itis.reflex.models.User;
import ru.itis.reflex.services.interfaces.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private MoodDataService moodDataService;

    @Autowired
    private TirednessDataService tirednessDataService;

    @GetMapping("/{company}/{department}/user")
    public String apply(@PathVariable("company") String company, @PathVariable("department") String department, Authentication authentication, ModelMap modelMap){
        User user = authService.getUserByAuthentication(authentication);
        Company companyByName = companyService.getCompanyByName(company);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("company", companyByName);
        modelMap.addAttribute("department", department);
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(moodDataService.getLastMoodByUser(user).getDate());
        Date currentDate = new Date(System.currentTimeMillis());
        cal2.setTime(currentDate);
        boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        System.out.println(sameDay);
        modelMap.addAttribute("isSameDay", sameDay);

        return "user";
    }

    @PostMapping("/sliderValue")
    public @ResponseBody String Submit(@RequestParam("mood") String mood, Authentication authentication,@RequestParam("tiredness") String tiredness) {
        User user = authService.getUserByAuthentication(authentication);
        moodDataService.addMorningMoodData(user, Integer.valueOf(mood));
        tirednessDataService.addMorningTirednessData(user, Integer.valueOf(tiredness));
        return mood;
    }

    @PostMapping("/sliderValueEv")
    public @ResponseBody String SubmitEv(@RequestParam("moodEv") String moodEv, Authentication authentication,@RequestParam("tirednessEv") String tirednessEv) {
        User user = authService.getUserByAuthentication(authentication);
        moodDataService.addEveningMoodData(user, Integer.valueOf(moodEv));
        tirednessDataService.addEveningTirednessData(user, Integer.valueOf(tirednessEv));
        return moodEv;
    }


}
