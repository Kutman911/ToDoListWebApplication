package kg.kut.os.dao;

import kg.kut.os.entity.RecordStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import kg.kut.os.entity.Record;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Repository
public class RecordDao {
    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public RecordDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Record> getAllRecords() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Query query = entityManager.createQuery("SELECT r FROM Record r");
            List<Record> records = query.getResultList();

            entityManager.getTransaction().commit();
            return records;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return Collections.emptyList();
        } finally {
            entityManager.close();
        }
    }

    public void saveRecord(Record record) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            entityManager.persist(record);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    public void updateRecordStatus(int id, RecordStatus newStatus) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Query query = entityManager.createQuery("UPDATE Record SET status = :status WHERE id = :id");
            query.setParameter("id", id);
            query.setParameter("status", newStatus);
            query.executeUpdate();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    public void deleteRecord(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Query query = entityManager.createQuery("DELETE  FROM Record WHERE id = :id");
            query.setParameter("id", id);
            query.executeUpdate();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

}
