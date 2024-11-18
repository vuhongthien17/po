package com.server.pokiwar.service;

import com.server.pokiwar.dto.RequestAward;
import com.server.pokiwar.exception.MessageResponse;

public interface AwardService {

    MessageResponse<?> giveAward(RequestAward request);
}
