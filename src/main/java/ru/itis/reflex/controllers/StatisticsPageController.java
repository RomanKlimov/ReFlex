package ru.itis.reflex.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticsPageController {

    @GetMapping("/user_statistics")
    public String getUserStatisticPage() {
        return "graph_test_page";
    }
}
