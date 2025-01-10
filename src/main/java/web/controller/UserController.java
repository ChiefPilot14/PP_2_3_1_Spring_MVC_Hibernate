package web.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.User;
import web.service.UserServiceImpl;

import java.util.List;

@Controller
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;

    }

    @GetMapping("/")
    public String showCars(HttpServletRequest request, Model model) {
        int count = parseCount(request);
        List<User> users = userService.getAllUsers();

        model.addAttribute("users", users);
        return "users";
    }

    private int parseCount(HttpServletRequest request) {
        String param = request.getParameter("count");
        if (param != null && !param.isEmpty()) {
            try {
                return Integer.parseInt(param);
            } catch (NumberFormatException e) {
                System.err.println("Invalid count parameter: " + param);
            }
        }
        return 0;
    }
}