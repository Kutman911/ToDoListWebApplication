package kg.kut.os.entity;


public class Record {
    private final String title;
    private RecordStatus recordStatus;

    public Record(String title, RecordStatus recordStatus) {
        this.title = title;
        this.recordStatus = recordStatus;
    }

    public String getTitle() {
        return title;
    }
    public RecordStatus getRecordStatus() {
        return recordStatus;
    }
    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }
}
