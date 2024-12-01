package com.server.pokiwar.controller;

import com.server.pokiwar.exception.MessageResponse;
import com.server.pokiwar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/userPlayer")
public class UserPlayerController {
    @Autowired
    UserService userService;

    @PostMapping("/updateEnergy")
    public ResponseEntity<?> updateEnergy(@RequestBody Map<String,Long> request) {
        MessageResponse<?> messageResponse = userService.updateEnergy(request);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
