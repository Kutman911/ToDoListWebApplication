package kg.kut.os.service;

import kg.kut.os.dao.RecordDao;
import kg.kut.os.entity.Record;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {

    private final RecordDao recordDao;
    public RecordService(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    public List<Record> getAllRecords() {
        return recordDao.getAllRecords();
    }
}
