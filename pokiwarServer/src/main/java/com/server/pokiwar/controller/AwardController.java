package com.server.pokiwar.controller;

import com.server.pokiwar.dto.RequestAward;
import com.server.pokiwar.exception.MessageResponse;
import com.server.pokiwar.service.AwardService;
import com.server.pokiwar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/award")
public class AwardController {

    @Autowired
    AwardService awardService;

    @Autowired
    UserService userService;

    @GetMapping("/gen")
    public ResponseEntity<?> genKey(@RequestParam Long idUser) {
        MessageResponse<?> messageResponse = userService.genKey(idUser);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PostMapping("/give")
    public ResponseEntity<?> createMember(@RequestBody RequestAward request) {
        MessageResponse<?> messageResponse = awardService.giveAward(request);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
