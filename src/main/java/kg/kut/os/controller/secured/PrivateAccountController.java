package kg.kut.os.controller.secured;

import kg.kut.os.entity.RecordStatus;
import kg.kut.os.entity.User;
import kg.kut.os.entity.dto.RecordsContainerDto;
import kg.kut.os.service.RecordService;
import kg.kut.os.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class PrivateAccountController {
    private final UserService userService;
    private final RecordService recordService;

    @Autowired
    public PrivateAccountController(UserService userService, RecordService recordService) {
        this.userService = userService;
        this.recordService = recordService;
    }

    @GetMapping
    public String getMainPage(Model model, @RequestParam(name = "filter", required = false) String filterMode) {
        RecordsContainerDto container = recordService.getAllRecords(filterMode);
        model.addAttribute("userName", container.getUserName());
        model.addAttribute("records", container.getRecords());
        model.addAttribute("numberOfActiveRecords", container.getNumberOfActiveRecords());
        model.addAttribute("numberOfDoneRecords", container.getNumberOfDoneRecords());
        return "private/account-page";
    }

    @PostMapping(value ="/add-record")
    public String addRecord(@RequestParam String title) {

        recordService.saveRecord(title);
        return "redirect:/account";
    }

    @PostMapping(value ="/make-record-done")
    public String makeRecordDone(@RequestParam Integer id,
                                 @RequestParam(name="filter", required = false) String filterMode) {

        recordService.updateRecordStatus(id, RecordStatus.DONE);
        return "redirect:/account" + (filterMode != null ? "?filter=" + filterMode : "");
    }

    @PostMapping(value ="/delete-record")
    public String deleteRecord(@RequestParam Integer id,
                               @RequestParam(name="filter", required = false) String filterMode) {
        recordService.deleteRecord(id);
        return "redirect:/account" + (filterMode != null ? "?filter=" + filterMode : "");
    }
}
