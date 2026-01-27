package kg.kut.os.entity.dto;

import kg.kut.os.entity.Record;

import java.util.List;

public class RecordsContainerDto {
    private final List<Record> records;
    private final int numberOfActiveRecords;
    private final int numberOfDoneRecords;

    public RecordsContainerDto(List<Record> records, int numberOfActiveRecords, int numberOfDoneRecords) {
        this.records = records;
        this.numberOfActiveRecords = numberOfActiveRecords;
        this.numberOfDoneRecords = numberOfDoneRecords;
    }

    public int getNumberOfActiveRecords() {
        return numberOfActiveRecords;
    }

    public List<Record> getRecords() {
        return records;
    }

    public int getNumberOfDoneRecords() {
        return numberOfDoneRecords;
    }
}
