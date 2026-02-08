package kg.kut.os.controller;

import kg.kut.os.entity.RecordStatus;
import kg.kut.os.entity.dto.RecordsContainerDto;
import kg.kut.os.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommonController {
    private RecordService recordService;
    @Autowired
    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }

    @RequestMapping("/")
    public String redirectToMainPage() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String getMainPage(Model model, @RequestParam(name = "filter", required = false) String filterMode) {
        RecordsContainerDto container = recordService.getAllRecords(filterMode);
        model.addAttribute("records", container.getRecords());
        model.addAttribute("numberOfActiveRecords", container.getNumberOfActiveRecords());
        model.addAttribute("numberOfDoneRecords", container.getNumberOfDoneRecords());
        return "main-page";
    }

    @RequestMapping(value ="/add-record", method = RequestMethod.POST)
    public String addRecord(@RequestParam String title) {

        recordService.saveRecord(title);
        return "redirect:/home";
    }

    @RequestMapping(value ="/make-record-done", method = RequestMethod.POST)
    public String makeRecordDone(@RequestParam int id,
                                 @RequestParam(name="filter", required = false) String filterMode) {

        recordService.updateRecordStatus(id, RecordStatus.DONE);
        return "redirect:/home" + (filterMode != null ? "?filter+" + filterMode : "");
    }

    @RequestMapping(value ="/delete-record", method = RequestMethod.POST)
    public String deleteRecord(@RequestParam int id,
                               @RequestParam(name="filter", required = false) String filterMode) {
        recordService.deleteRecord(id);
        return "redirect:/home" + (filterMode != null ? "?filter+" + filterMode : "");
    }
}
