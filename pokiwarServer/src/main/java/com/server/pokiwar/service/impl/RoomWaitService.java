package com.server.pokiwar.service.impl;

import com.server.pokiwar.dto.*;
import com.server.pokiwar.exception.MessageResponse;
import com.server.pokiwar.model.CountPass;
import com.server.pokiwar.model.EnemyPet;
import com.server.pokiwar.model.Pet;
import com.server.pokiwar.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class RoomWaitService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    CardUserRepository cardUserRepository;
    @Autowired
    PetUserRepository petUserRepository;
    @Autowired
    CountPassRepository countPassRepository;
    @Autowired
    PetRepository petRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    EnemyPetRepository enemyPetRepository;
    @Autowired
    ModelMapper mapper;

    public MessageResponse<?> join(RequestJoinRoom request) {
        // thông tin người chơi
        UserPlayerRoomDto userPlayerDto = mapper.map( userRepository.findById(request.getIdUser()).orElse(null), UserPlayerRoomDto.class);
        PetUserDto petUserDto = mapper.map(petUserRepository.findById(userPlayerDto.getIdPetUser()).orElse(null), PetUserDto.class);
        List<ImageDto> userImg = imageRepository.findByIdPet(request.getIdUser())
                .stream()
                .map(entity -> mapper.map(entity, ImageDto.class))
                .toList();
        petRepository.findById(petUserDto.getIdPet()).ifPresent(petUser -> petUserDto.setThumbnailPet(petUser.getThumbnail()));
        userPlayerDto.setThumbnailPetUser(petUserDto.getThumbnailPet());
        userPlayerDto.setImageUser(userImg);

        // số lần thắng
        CountPass countPass = countPassRepository.countPassBy(request.getIdEnemyPet(),request.getIdUser()).orElse(null);
        if(countPass ==null){
            userPlayerDto.setCountPass(0);
        }else{
            userPlayerDto.setCountPass(countPass.getCount());

        }

        // thông tin pet địch
        EnemyPet enemyPet = enemyPetRepository.findById(request.getIdEnemyPet()).orElse(null);
        if(enemyPet!=null){
            Pet petEnemy = petRepository.findById(enemyPet.getIdPet()).orElse(null);
            userPlayerDto.setEnemyPet(enemyPet);
            if(petEnemy!=null){
                List<ImageDto> enemyPetImg = imageRepository.findByIdPet(enemyPet.getIdPet())
                        .stream()
                        .map(entity -> mapper.map(entity, ImageDto.class))
                        .toList();
                userPlayerDto.setImageEnemyPet(enemyPetImg);
                userPlayerDto.setNamePetEnemy(petEnemy.getName());

            }
        }



        // ds chọn thẻ

        List<CardUserDto> cardUserDtos = cardUserRepository.listCardUser(request.getIdUser()).stream()
                .map(entity -> mapper.map(entity, CardUserDto.class)).toList();
        userPlayerDto.setListChooseCard(cardUserDtos);

        // ds chọn pet
        List<PetUserDto> petUserDtos = petUserRepository.listPetUser(request.getIdUser()).stream()
                .map(entity -> mapper.map(entity, PetUserDto.class)).toList();
        userPlayerDto.setListChoosePet(petUserDtos);

        return new MessageResponse<>(LocalDateTime.now(), 200, true, "Join room", userPlayerDto);

    }
}
