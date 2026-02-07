package kg.kut.os.dao;

import kg.kut.os.entity.RecordStatus;
import org.springframework.stereotype.Repository;
import kg.kut.os.entity.Record;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RecordDao {
    @PersistenceContext
    private EntityManager entityManager;


    public List<Record> getAllRecords() {
        Query query = entityManager.createQuery("SELECT r FROM Record r ORDER BY r.id ASC");
        List<Record> records = query.getResultList();

        return records;
    }

    public void saveRecord(Record record) {
        entityManager.persist(record);
    }

    public void updateRecordStatus(int id, RecordStatus newStatus) {
        Query query = entityManager.createQuery("UPDATE Record SET status = :status WHERE id = :id");
        query.setParameter("id", id);
        query.setParameter("status", newStatus);
        query.executeUpdate();

    }

    public void deleteRecord(int id) {
        Query query = entityManager.createQuery("DELETE  FROM Record WHERE id = :id");
        query.setParameter("id", id);
        query.executeUpdate();

    }

}
