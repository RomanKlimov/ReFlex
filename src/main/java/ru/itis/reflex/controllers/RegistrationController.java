package ru.itis.reflex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.reflex.exceptions.CompanyExistsException;
import ru.itis.reflex.exceptions.EmailExistsException;
import ru.itis.reflex.forms.AdminRegistrationForm;
import ru.itis.reflex.forms.BossRegistrationForm;
import ru.itis.reflex.forms.UserRegistrationForm;
import ru.itis.reflex.services.impl.RegistrationServiceImpl;
import ru.itis.reflex.services.interfaces.CompanyService;
import ru.itis.reflex.services.interfaces.DepartmentService;

import java.io.IOException;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationServiceImpl registrationService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private DepartmentService departmentService;


    @GetMapping("/signUp")
    public String signUpAdmin(Model model){
        if (!model.containsAttribute("adminRegistrationForm")){
            model.addAttribute("adminRegistrationForm", new AdminRegistrationForm());
        }
        return "signUpAdmin";
    }


    @PostMapping("/signUp")
    public String registerAdminAccount(AdminRegistrationForm adminRegistrationForm,
                                      BindingResult result, RedirectAttributes attributes) throws InterruptedException, IOException, EmailExistsException, CompanyExistsException {
        if (result.hasErrors()){
            attributes.addFlashAttribute("adminRegistrationForm", adminRegistrationForm);
            attributes.addFlashAttribute("error" , result.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/signUp";
        }

        companyService.createCompany(adminRegistrationForm.getCompany());
        registrationService.createAdminAccount(adminRegistrationForm);
        companyService.addCompanyHead(adminRegistrationForm.getCompany(), adminRegistrationForm.getEmail());

        return "redirect:/login";
    }

    @GetMapping("/{company}/signUp")
    public String  signUpBoss(@PathVariable("company") String company, Model model){
        if (!model.containsAttribute("bossRegistrationForm")){
            model.addAttribute("bossRegistrationForm", new BossRegistrationForm());
        }
        model.addAttribute("company", company);

        return "signUpBoss";
    }

    @PostMapping("/signUpBoss")
    public String registerBossAccount(BossRegistrationForm bossRegistrationForm,
                                       BindingResult result, RedirectAttributes attributes) throws InterruptedException, IOException, EmailExistsException, CompanyExistsException {
        if (result.hasErrors()){
            attributes.addFlashAttribute("bossRegistrationForm", bossRegistrationForm);
            attributes.addFlashAttribute("error" , result.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/signUpBoss";
        }

        departmentService.addDepartment(bossRegistrationForm.getDepartment(), bossRegistrationForm.getKey());
        registrationService.createBossAccount(bossRegistrationForm);
        departmentService.addDepartmentHead(bossRegistrationForm.getDepartment(), bossRegistrationForm.getEmail());


        return "redirect:/login";
    }

    @GetMapping("/{company}/{department}/signUp")
    public String  signUpUser(@PathVariable("company") String company, Model model, @PathVariable("department") String department){
        if (!model.containsAttribute("userRegistrationForm")){
            model.addAttribute("userRegistrationForm", new UserRegistrationForm());
        }
        model.addAttribute("company", company);
        model.addAttribute("department", department);

        return "signUpUser";
    }

    @PostMapping("/signUpUser")
    public String registerUserAccount(UserRegistrationForm userRegistrationForm,
                                      BindingResult result, RedirectAttributes attributes) throws InterruptedException, IOException, EmailExistsException, CompanyExistsException {
        if (result.hasErrors()){
            attributes.addFlashAttribute("userRegistrationForm", userRegistrationForm);
            attributes.addFlashAttribute("error" , result.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/signUpUser";
        }

        registrationService.createUserAccount(userRegistrationForm);

        return "redirect:/login";
    }





}
