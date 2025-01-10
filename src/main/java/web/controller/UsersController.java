package web.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.User;
import web.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {
    private final UserServiceImpl userService;

    @Autowired
    public UsersController(UserServiceImpl userService) {
        this.userService = userService;

    }

    @GetMapping("/")
    public String showAllUsers(HttpServletRequest request, Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/delete{id}")
    public String removeUserById(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        userService.removeUserById(id);
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/edit{id}")
    public String editUserById(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        List<User> users = userService.getUserById(id);
        model.addAttribute("users", users);
        return "edit";
    }

    @GetMapping("/save{id}")
    public String saveUserById(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        byte age = Byte.parseByte(request.getParameter("age"));
        userService.saveUserById(id, name, lastname, age);
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }


//    private int parseId(HttpServletRequest request) {
//        String param = request.getParameter("id");
//        if (param != null && !param.isEmpty()) {
//            try {
//                return Integer.parseInt(param);
//            } catch (NumberFormatException e) {
//                System.err.println("Invalid id parameter: " + param);
//            }
//        }
//        return 0;
//    }
}