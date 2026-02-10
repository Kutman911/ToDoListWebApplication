package kg.kut.os.repository;

import kg.kut.os.entity.Record;
import kg.kut.os.entity.RecordStatus;
import kg.kut.os.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {
    @Modifying
    @Query("UPDATE Record r SET r.status = :status WHERE r.id = :id")
    void update(@Param("id") Integer id, @Param("status") RecordStatus newStatus);

    List<Record> findAllByStatus(RecordStatus status);

}
