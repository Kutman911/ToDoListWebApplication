package kg.kut.os.controller;

import kg.kut.os.entity.Record;
import kg.kut.os.entity.RecordStatus;
import kg.kut.os.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CommonController {
    private RecordService recordService;
    @Autowired
    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }


    @RequestMapping({"/", "/home"})
    public String getMainPage(Model model) {
        List<Record> records = recordService.getAllRecords();
        int numberOfActiveRecords = (int) records.stream().filter(record -> record.getRecordStatus() == RecordStatus.Active).count();
        int numberOfDoneRecords = (int) records.stream().filter(record -> record.getRecordStatus() == RecordStatus.Done).count();
        model.addAttribute("numberOfActiveRecords", numberOfActiveRecords);
        model.addAttribute("numberOfDoneRecords", numberOfDoneRecords);
        return "main-page";
    }
}
