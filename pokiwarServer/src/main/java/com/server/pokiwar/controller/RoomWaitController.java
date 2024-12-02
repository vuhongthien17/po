package com.server.pokiwar.controller;

import com.server.pokiwar.dto.RequestJoinRoom;
import com.server.pokiwar.dto.RequestMatch;
import com.server.pokiwar.exception.MessageResponse;
import com.server.pokiwar.service.UserService;
import com.server.pokiwar.service.impl.RoomWaitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/roomWait")
public class RoomWaitController {
    @Autowired
    RoomWaitService roomWaitService;


    @PostMapping("/join")
    public ResponseEntity<?> joinRoom(@RequestBody RequestJoinRoom request) {
        MessageResponse<?> messageResponse = roomWaitService.join(request);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping("/pet")
    public void pet(@RequestParam() Long userId,@RequestParam() Long petId ) {
        roomWaitService.pet(userId,petId);
    }

}
