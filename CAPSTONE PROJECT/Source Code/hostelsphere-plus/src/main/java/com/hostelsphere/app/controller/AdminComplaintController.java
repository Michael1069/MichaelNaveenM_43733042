package com.hostelsphere.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hostelsphere.app.entity.Complaint;
import com.hostelsphere.app.service.ComplaintService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AdminComplaintController {

    private final ComplaintService complaintService;

    public AdminComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    // View all complaints
    @GetMapping("/admin/complaints")
    public String viewAllComplaints(Model model) {

        List<Complaint> complaints = complaintService.getAllComplaints();
        model.addAttribute("complaints", complaints);

        return "admin-complaints";
    }
    @GetMapping("/debug-auth")
    @ResponseBody
    public String debug(Authentication auth) {
        return auth.getAuthorities().toString();
    }


    // Update complaint status
    @GetMapping("/admin/complaint/{id}/status/{status}")
    public String updateComplaintStatus(
            @PathVariable Long id,
            @PathVariable String status) {

        try {
            complaintService.updateStatus(id, status);
            return "redirect:/admin/complaints?success";
        } catch (Exception e) {
            return "redirect:/admin/complaints?error";
        }
    }
    }
