package kg.kut.os.controller;

import kg.kut.os.entity.Record;
import kg.kut.os.entity.RecordStatus;
import kg.kut.os.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    public String getMainPage(Model model) {
        List<Record> records = recordService.getAllRecords();
        int numberOfActiveRecords = (int) records.stream().filter(record -> record.getRecordStatus() == RecordStatus.Active).count();
        int numberOfDoneRecords = (int) records.stream().filter(record -> record.getRecordStatus() == RecordStatus.Done).count();
        model.addAttribute("records", records);
        model.addAttribute("numberOfActiveRecords", numberOfActiveRecords);
        model.addAttribute("numberOfDoneRecords", numberOfDoneRecords);
        return "main-page";
    }

    @RequestMapping(value ="/add-record", method = RequestMethod.POST)
    public String addRecord(@RequestParam String title) {

        recordService.saveRecord(title);
        return "redirect:/home";
    }

    @RequestMapping(value ="/make-record-done", method = RequestMethod.POST)
    public String makeRecordDone(@RequestParam int id) {

        recordService.updateRecordStatus(id, RecordStatus.Done);
        return "redirect:/home";
    }

    @RequestMapping(value ="/delete-record", method = RequestMethod.POST)
    public String deleteRecord(@RequestParam int id) {
        recordService.deleteRecord(id);
        return "redirect:/home";
    }
}
