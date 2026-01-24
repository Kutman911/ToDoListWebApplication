package kg.kut.os.dao;

import kg.kut.os.entity.RecordStatus;
import org.springframework.stereotype.Repository;

import kg.kut.os.entity.Record;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class RecordDao {
    private final List<Record> records = new ArrayList<>(
            Arrays.asList(
                    new Record("Read at least farz namaz", RecordStatus.Active),
                    new Record("Read quran", RecordStatus.Done),
                    new Record("Make Balkyayl happy", RecordStatus.Active)
            )
    );

    public ArrayList<Record> getAllRecords() {
        return new ArrayList<>(records);
    }


}
