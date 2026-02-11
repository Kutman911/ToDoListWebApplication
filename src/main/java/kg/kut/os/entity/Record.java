package kg.kut.os.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "records")
public class Record {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RecordStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Record() {}

    public Record(String title, User user) {
        this.title = title;
        this.status = RecordStatus.ACTIVE;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RecordStatus getStatus() {
        return status;
    }

    public void setStatus(RecordStatus recordStatus) {
        this.status = recordStatus;
    }
}
