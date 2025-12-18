package com.hostelsphere.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hostelsphere.app.entity.Complaint;
import com.hostelsphere.app.entity.User;
import com.hostelsphere.app.entity.Room;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findByRaisedBy(User user);

    List<Complaint> findByRoom(Room room);

    List<Complaint> findByStatus(String status);
}
