package com.example.examenpractico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/add_employee.html")
    public String addEmployee(Model model) {
        return "AddNewEmployee";
    }

    @GetMapping("/add_worked_hours.html")
    public String addWorkedHours(Model model) {
        return "AddWorkedHours";
    }

    @GetMapping("/employees_by_job.html")
    public String employeesByJob(Model model) {
        return "EmployeesByJob";
    }

    @GetMapping("/total_worked_hours_and_payment.html")
    public String totalWorkedHours(Model model) {
        return "EmployeeWorkAndPaymentDetails";
    }


}