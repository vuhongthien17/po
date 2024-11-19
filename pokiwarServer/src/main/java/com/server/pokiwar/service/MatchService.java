package com.server.pokiwar.service;

import com.server.pokiwar.dto.RequestMatch;
import com.server.pokiwar.exception.MessageResponse;

public interface MatchService {
    MessageResponse<?> start(RequestMatch request);
}
