package kg.kut.os.controller.secured;

import kg.kut.os.entity.User;
import kg.kut.os.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrivateAdminController {
    private final UserService userService;

    @Autowired
    public PrivateAdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getManagementPage(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("userName", user.getName());

        return "private/admin/management-page";
    }
}
