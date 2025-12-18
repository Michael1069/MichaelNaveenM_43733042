package com.hostelsphere.app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hostelsphere.app.entity.Complaint;
import com.hostelsphere.app.entity.User;
import com.hostelsphere.app.repository.ComplaintRepository;
import com.hostelsphere.app.service.ComplaintService;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @Override
    public Complaint raiseComplaint(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    @Override
    public List<Complaint> getComplaintsByUser(User user) {
        return complaintRepository.findByRaisedBy(user);
    }

    @Override
    public List<Complaint> getComplaintsByStatus(String status) {
        return complaintRepository.findByStatus(status);
    }

    @Override
    public Complaint updateStatus(Long complaintId, String status) {
        Complaint complaint = complaintRepository.findById(complaintId).orElse(null);
        if (complaint != null) {
            complaint.setStatus(status);
            return complaintRepository.save(complaint);
        }
        return null;
    }
}
