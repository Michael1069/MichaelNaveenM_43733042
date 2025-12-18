package com.hostelsphere.app.service;

import java.util.List;

import com.hostelsphere.app.entity.Complaint;
import com.hostelsphere.app.entity.User;

public interface ComplaintService {

    Complaint raiseComplaint(Complaint complaint);

    List<Complaint> getAllComplaints();

    List<Complaint> getComplaintsByUser(User user);

    List<Complaint> getComplaintsByStatus(String status);

    Complaint updateStatus(Long complaintId, String status);
}
