package com.example.demo;

import com.example.demo.entity.AppUser;
import com.example.demo.entity.Leave;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.service.LeaveService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LeaveController {

    private final AppUserRepository userRepository;

    private final LeaveService leaveService;

    public LeaveController(AppUserRepository userRepository, LeaveService leaveService) {
        this.userRepository = userRepository;
        this.leaveService = leaveService;
    }

    @GetMapping("/user/{userId}/applyLeave")
    @ResponseBody
    Leave applyLeave(@PathVariable Integer userId) {
        Leave leaveRequest = new Leave();
        leaveRequest.setNumberOfDays(2);
        AppUser requestedByUserId = userRepository.findById(userId)
                                                  .orElseThrow(() -> new RuntimeException("User not found"));

        leaveRequest.setAppUserByRequestedByUserId(requestedByUserId);

        return leaveService.initProcessInstance(leaveRequest);
    }

    @GetMapping("/user/{userId}/leavesPendingApproval")
    @ResponseBody
    List<Leave> leavesPendingApproval(@PathVariable Integer userId) {
        return leaveService.getProcessInstancesPendingApproval(userId);
    }

    @GetMapping("/user/{userId}/approveLeaveStep/{leaveId}")
    @ResponseBody
    Leave approveLeaveStep(@PathVariable Integer userId, @PathVariable Integer leaveId) {
        return leaveService.approveCurrentProcessStep(userId, leaveId, "The leave is approved");
    }


}
