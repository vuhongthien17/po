package com.server.pokiwar.service.impl;

import com.server.pokiwar.dto.*;
import com.server.pokiwar.exception.MessageResponse;
import com.server.pokiwar.model.*;
import com.server.pokiwar.repository.*;
import com.server.pokiwar.service.MatchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    CardUserRepository cardUserRepository;
    @Autowired
    PetUserRepository petUserRepository;
    @Autowired
    PetRepository petRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ModelMapper mapper;

    @Override
    public MessageResponse<?> start(RequestMatch request) {
        try {
            UserPlayer userPlayer = userRepository.findById(request.getIdUser()).orElse(null);
            PetUser petUser = petUserRepository.findById(request.getIdPetUser()).orElse(null);

            if (userPlayer == null) {
                return new MessageResponse<>(LocalDateTime.now(), 500, false, "không tìm thấy user.");
            }
            if (petUser == null) {
                return new MessageResponse<>(LocalDateTime.now(), 500, false, "hãy chọn lại pet.");
            }
            Pet pet = petRepository.findById(petUser.getIdPet()).orElse(null);
            if (pet == null) {
                return new MessageResponse<>(LocalDateTime.now(), 500, false, "Pet này không có trong hệ thống");
            }

            List<Long> idCard = request.getListCardUserId();
            List<CardUserDto> cardUserDtos = new ArrayList<>();

            // update lại thông số khi ra trận
            userPlayer.setMana(petUser.getMana() );
            userPlayer.setAttack(petUser.getAttack());
            userPlayer.setBlood(petUser.getBlood());
            UserPlayerDto userPlayerDto = mapper.map(userRepository.save(userPlayer), UserPlayerDto.class);

            userPlayerDto.setNamePet(pet.getName());
            if(!idCard.isEmpty()){
                //lấy list card có hình
                for (Long id : idCard) {
                    CardUserDto cardUserDto = mapper.map(cardUserRepository.findById(id).orElse(null), CardUserDto.class);
                    List<ImageDto> image = imageRepository.findByIdCard(id).stream()
                            .map(m -> mapper.map(m, ImageDto.class)).toList();
                    cardUserDto.setImageCard(image);
                    cardUserDtos.add(cardUserDto);
                }
                userPlayerDto.setListCard(cardUserDtos);
            }


            //lấy aảnh pet
            List<ImageDto> imagePet = imageRepository.findByIdPet(pet.getId())
                    .stream()
                    .map(entity -> mapper.map(entity, ImageDto.class))
                    .toList();
            userPlayerDto.setImagePet(imagePet);

            //lấy aảnh nhân vật
            List<ImageDto> imageUser = imageRepository.findByIdPet(userPlayerDto.getId())
                    .stream()
                    .map(entity -> mapper.map(entity, ImageDto.class))
                    .toList();
            userPlayerDto.setImageUser(imageUser);
            return new MessageResponse<>(LocalDateTime.now(), 200, true, "Bắt đầu", userPlayerDto);

        } catch (Exception e) {
            return new MessageResponse<>(LocalDateTime.now(), 500, false, "Lỗi hệ thống: " + e.getMessage());
        }

    }
}
