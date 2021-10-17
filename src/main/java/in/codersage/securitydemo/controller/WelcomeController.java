package in.codersage.securitydemo.controller;

import in.codersage.securitydemo.model.Role;
import in.codersage.securitydemo.model.User;
import in.codersage.securitydemo.repository.RoleRepository;
import in.codersage.securitydemo.service.SecurityService;
import in.codersage.securitydemo.service.UserService;
import in.codersage.securitydemo.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

@Controller
public class WelcomeController {
    @Autowired
    UserValidator userValidator;
    @Autowired
    UserService userService;
    @Autowired
    SecurityService securityService;
    @Autowired
    RoleRepository roleRepository;

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    @RequestMapping(value={"/", "/home"})
    public String welcome(Map<String, Object> model) {
        model.put("message", this.message);
        return "home";
    }

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {
        return "fancy-login";
    }

    @RequestMapping("/student/studentHome")
    public String studentHome() {
        return "studentHome";
    }

    @RequestMapping("/instructor/instructorHome")
    public String instructorHome() {
        return "instructorHome";
    }

    @RequestMapping("/admin/adminHome")
    public String adminHome(Map<String, Object> model) {
        return "adminHome";
    }

    @RequestMapping("/access-denied")
    public String accessdenied() {
        return "access-denied";
    }


    @GetMapping("/registration")
    public String showRegistration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String doRegistration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, @RequestParam("roles") String role) {
        Role userRole = roleRepository.findByName("ROLE_"+role.toUpperCase());
        System.out.println("Inside controller");
        userForm.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userValidator.validate(userForm, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
        userService.save(userForm);
        return "fancy-login";
    }


}
