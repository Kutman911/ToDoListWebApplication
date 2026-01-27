package kg.kut.os.entity;


public class Record {
    private static int counter = 0;
    private final int id;
    private final String title;
    private RecordStatus recordStatus;

    public Record(String title, RecordStatus recordStatus) {
        this.id = counter++;
        this.title = title;
        this.recordStatus = recordStatus;
    }

    public Record(String title) {
        this.id = counter++;
        this.title = title;
        this.recordStatus = RecordStatus.ACTIVE;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public RecordStatus getRecordStatus() {
        return recordStatus;
    }
    public RecordStatus getStatus() {
        return recordStatus;
    }
    public void setStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }
}
