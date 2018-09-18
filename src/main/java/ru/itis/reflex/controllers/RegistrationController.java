package ru.itis.reflex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.reflex.exceptions.CompanyExistsException;
import ru.itis.reflex.exceptions.EmailExistsException;
import ru.itis.reflex.forms.AdminRegistrationForm;
import ru.itis.reflex.services.impl.RegistrationServiceImpl;
import ru.itis.reflex.services.interfaces.CompanyService;
import ru.itis.reflex.services.interfaces.RegistrationSevice;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationServiceImpl registrationService;

    @Autowired
    private CompanyService companyService;


    @GetMapping("/signUp")
    public String signUp(Model model){
        if (!model.containsAttribute("adminRegistrationForm")){
            model.addAttribute("adminRegistrationForm", new AdminRegistrationForm());
        }
        return "signUp";
    }


    @PostMapping("/signUp")
    public String registerUserAccount(@ModelAttribute("userForm") AdminRegistrationForm adminRegistrationForm,
                                      BindingResult result, RedirectAttributes attributes) throws InterruptedException, IOException, EmailExistsException, CompanyExistsException {
        if (result.hasErrors()){
            attributes.addFlashAttribute("adminRegistrationForm", adminRegistrationForm);
            attributes.addFlashAttribute("error" , result.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/signUp";
        }


        companyService.createCompany(adminRegistrationForm.getCompany());
        registrationService.createAdminAccount(adminRegistrationForm);

        return "redirect:/login";
    }



}
