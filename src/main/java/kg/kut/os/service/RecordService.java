package kg.kut.os.service;

import kg.kut.os.repository.RecordRepository;
import kg.kut.os.entity.Record;
import kg.kut.os.entity.RecordStatus;
import kg.kut.os.entity.dto.RecordsContainerDto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class RecordService {

    private final RecordRepository recordRepository;
    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public RecordsContainerDto getAllRecords(String filterMode) {
        List<Record> records = recordRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
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
            recordRepository.save(new Record(title));
        }

    }

    public void updateRecordStatus(Integer id, RecordStatus newStatus) {
        recordRepository.update(id, newStatus);
    }

    public void deleteRecord(Integer id) {
        recordRepository.deleteById(id);
    }
}
