package com.server.pokiwar.service.impl;

import com.server.pokiwar.exception.MessageResponse;
import com.server.pokiwar.model.UserPlayer;
import com.server.pokiwar.repository.UserRepository;
import com.server.pokiwar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public MessageResponse<?> genKey(Long idUser) {
        try {
            UserPlayer userPlayer = userRepository.findById(idUser).orElse(null);
            if (userPlayer == null) {
                return new MessageResponse<>(LocalDateTime.now(), 500, false, "user không tồn tại.");
            }
            userPlayer.setCode(UUID.randomUUID().toString());
            userRepository.save(userPlayer);
            return new MessageResponse<>(LocalDateTime.now(), 200, true, "đã gen key", userPlayer.getCode());
        } catch (Exception e) {
            return new MessageResponse<>(LocalDateTime.now(), 500, false, "Lỗi hệ thống: " + e.getMessage());
        }
    }

    @Override
    public MessageResponse<?> updateEnergy(Map<String, Long> request) {
        try {
            Long idUser = request.get("idUser");
            Long type = request.get("type");
            UserPlayer userPlayer = userRepository.findById(idUser).orElse(null);
            if (userPlayer == null) {
                return new MessageResponse<>(LocalDateTime.now(), 500, false, "user không tồn tại.");
            }

            // = 0 thì cộng 1 năng lượng và ngược lại thì trừ
            if (type != 0) {
                //tối đa 80 năng lượng
                if(userPlayer.getEnergy()<80){
                    userPlayer.setEnergy(userPlayer.getEnergy() + 1);
                }

            } else {
                if (userPlayer.getEnergy() == 0) {
                    return new MessageResponse<>(LocalDateTime.now(), 200, true, "bạn đã hết năng lượng", userPlayer.getEnergy());
                }
                userPlayer.setEnergy(userPlayer.getEnergy() - 1);
            }

            userRepository.save(userPlayer);
            return new MessageResponse<>(LocalDateTime.now(), 200, true, "đã update năng lượng", userPlayer.getEnergy());
        } catch (Exception e) {
            return new MessageResponse<>(LocalDateTime.now(), 500, false, "Lỗi hệ thống: " + e.getMessage());
        }
    }
}
