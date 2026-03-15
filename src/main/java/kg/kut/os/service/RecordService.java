package kg.kut.os.service;

import kg.kut.os.entity.User;
import kg.kut.os.repository.RecordRepository;
import kg.kut.os.entity.Record;
import kg.kut.os.entity.RecordStatus;
import kg.kut.os.entity.dto.RecordsContainerDto;
import kg.kut.os.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class RecordService {

    private final RecordRepository recordRepository;
    private final UserService userService;

    public RecordService(RecordRepository recordRepository, UserService userService) {
        this.recordRepository = recordRepository;
        this.userService = userService;
    }

    public RecordsContainerDto getAllRecords(String filterMode) {
        User user = userService.getCurrentUser();
        List<Record> records = user.getRecords().stream()
                .sorted(Comparator.comparingInt(Record::getId))
                .collect(Collectors.toList());

        long numberOfActiveRecords = records.stream().filter(record -> record.getStatus() == RecordStatus.ACTIVE).count();
        long numberOfDoneRecords = records.stream().filter(record -> record.getStatus() == RecordStatus.DONE).count();

        if (filterMode != null && !filterMode.isBlank()) {
            try {
                RecordStatus statusFilter = RecordStatus.valueOf(filterMode.toUpperCase());
                records = records.stream()
                        .filter(record -> record.getStatus() == statusFilter)
                        .collect(Collectors.toList());
            } catch (IllegalArgumentException ignored) {
                // If filter is invalid (e.g., 'all'), show all records
            }
        }

        return new RecordsContainerDto(user.getName(), records, (int) numberOfActiveRecords, (int) numberOfDoneRecords);
    }
    public void saveRecord(String title) {
        if (title != null && !title.isEmpty()) {
            User user = userService.getCurrentUser();
            recordRepository.save(new Record(title, user));
        }

    }

    public void updateRecordStatus(Integer id, RecordStatus newStatus) {
        recordRepository.update(id, newStatus);
    }

    public void deleteRecord(Integer id) {
        recordRepository.deleteById(id);
    }
}
