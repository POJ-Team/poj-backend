package com.poj.controller.admin;

import com.poj.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    @GetMapping("/confirm")
    public ResponseEntity<String> confirm(@RequestParam Long memberId){
        adminService.upgradeToUser(memberId);
        return ResponseEntity.ok("success");
    }
}
