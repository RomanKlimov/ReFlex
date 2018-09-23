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
import ru.itis.reflex.models.MoodData;
import ru.itis.reflex.models.User;
import ru.itis.reflex.security.webConfig.WebSecurityConfig;
import ru.itis.reflex.services.interfaces.*;

import java.util.Date;
import java.util.List;

@Controller
public class BossController {

    @Autowired
    private AuthService authService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private KeyService keyService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private MoodDataService moodDataService;

    @GetMapping("/{company}/boss")
    public String apply(@PathVariable("company") String company, Authentication authentication, ModelMap modelMap){
        User user = authService.getUserByAuthentication(authentication);
        Company companyByName = companyService.getCompanyByName(company);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("company", companyByName);
        Date date = new Date(System.currentTimeMillis());
//        List<MoodData> moodDataByDate = moodDataService.getMoodDataByDate(user, date);
//        if (moodDataByDate.size() == 1 && moodDataByDate.get(0) == null){
//            modelMap.addAttribute("checkMood", true);
//        } else modelMap.addAttribute("checkMood", false);

        return "boss";
    }

    @PostMapping("/keyBoss")
    public String sendBossInvite(@RequestParam("emails") String emails, ModelMap modelMap, Authentication authentication) {
        User user = authService.getUserByAuthentication(authentication);
        keyService.addKeys(emails, user);
        emailService.sendEmail(emails);

        List<Key> keys = keyService.getAllByHead(user);
        modelMap.addAttribute("keys", keys);

        return "boss";
    }

}
