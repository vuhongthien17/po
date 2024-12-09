package com.server.pokiwar.controller;

import com.server.pokiwar.dto.RequestGroup;
import com.server.pokiwar.service.impl.GroupPetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/group")
public class GroupPetController {

    @Autowired
    GroupPetService groupPetService;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
       return new ResponseEntity<>(groupPetService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/detail")
    public ResponseEntity<?> detail(@RequestBody RequestGroup request) {
        return new ResponseEntity<>(groupPetService.detail(request), HttpStatus.OK);
    }
}
