package com.hostelsphere.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hostelsphere.app.entity.Complaint;
import com.hostelsphere.app.entity.Room;
import com.hostelsphere.app.entity.User;
import com.hostelsphere.app.service.ComplaintService;
import com.hostelsphere.app.service.RoomService;
import com.hostelsphere.app.service.UserService;

@Controller
public class ComplaintController {

    private final ComplaintService complaintService;
    private final UserService userService;
    private final RoomService roomService;

    public ComplaintController(
            ComplaintService complaintService,
            UserService userService,
            RoomService roomService) {
        this.complaintService = complaintService;
        this.userService = userService;
        this.roomService = roomService;
    }

    // Show complaint form
    @GetMapping("/complaint/new")
    public String showComplaintForm(Model model) {
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);
        return "raise-complaint";
    }

    // Submit complaint
    @PostMapping("/complaint/save")
    public String submitComplaint(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String category,
            @RequestParam String priority,
            @RequestParam Long roomId) {

        // TEMP user (later replaced by logged-in user)
        User user = userService.getUserByEmail("test@student.com");

        Room room = roomService.getRoomById(roomId);

        Complaint complaint = new Complaint(
                title,
                description,
                category,
                priority,
                user,
                room
        );

        complaintService.raiseComplaint(complaint);

        return "redirect:/complaint/new?success";
    }
}
