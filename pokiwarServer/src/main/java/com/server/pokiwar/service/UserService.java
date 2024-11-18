package com.server.pokiwar.service;

import com.server.pokiwar.exception.MessageResponse;

import java.util.Map;

public interface UserService {
    MessageResponse<?> genKey(Long idUser);

    MessageResponse<?> updateEnergy(Map<String,Long> request);

}
