package kg.kut.os.service;

import kg.kut.os.dao.RecordDao;
import kg.kut.os.entity.Record;
import kg.kut.os.entity.RecordStatus;
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
    public void saveRecord(String title) {
        if (title != null && !title.isEmpty()) {
            recordDao.saveRecord(new Record(title));
        }

    }

    public void updateRecordStatus(int id, RecordStatus newStatus) {
        recordDao.updateRecordStatus(id, newStatus);
    }

    public void deleteRecord(int id) {
        recordDao.deleteRecord(id);
    }
}
