package kg.kut.os.service;

import kg.kut.os.dao.RecordDao;
import kg.kut.os.entity.Record;
import kg.kut.os.entity.RecordStatus;
import kg.kut.os.entity.dto.RecordsContainerDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class RecordService {

    private final RecordDao recordDao;
    public RecordService(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    public RecordsContainerDto getAllRecords(String filterMode) {
        List<Record> records = recordDao.getAllRecords();
        int numberOfActiveRecords = (int) records.stream().filter(record -> record.getStatus() == RecordStatus.ACTIVE).count();
        int numberOfDoneRecords = (int) records.stream().filter(record -> record.getStatus() == RecordStatus.DONE).count();
        if (filterMode == null || filterMode.isEmpty()) {
            return new RecordsContainerDto(records,numberOfActiveRecords, numberOfDoneRecords);
        }

        String filterModeInUpperCase = filterMode.toUpperCase();
        List<String> allowedFilterModes = Arrays.stream(RecordStatus.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        if (allowedFilterModes.contains(filterModeInUpperCase)) {
            List<Record> filteredRecords = records.stream()
                    .filter(record -> record.getStatus() == RecordStatus.valueOf(filterModeInUpperCase))
                    .collect(Collectors.toList());
            return new RecordsContainerDto(filteredRecords, numberOfActiveRecords, numberOfDoneRecords);
        }
        return new RecordsContainerDto(records,numberOfActiveRecords, numberOfDoneRecords);
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
