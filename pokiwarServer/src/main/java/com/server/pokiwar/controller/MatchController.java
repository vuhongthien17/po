package com.server.pokiwar.controller;

import com.server.pokiwar.dto.RequestAward;
import com.server.pokiwar.dto.RequestMatch;
import com.server.pokiwar.exception.MessageResponse;
import com.server.pokiwar.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/match")
public class MatchController {

    @Autowired
    MatchService matchService;

    @PostMapping("/start")
    public ResponseEntity<?> createMember(@RequestBody RequestMatch request) {
        MessageResponse<?> messageResponse = matchService.start(request);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

}
