package com.server.pokiwar.service.impl;

import com.server.pokiwar.dto.*;
import com.server.pokiwar.exception.MessageResponse;
import com.server.pokiwar.model.CountPass;
import com.server.pokiwar.model.EnemyPet;
import com.server.pokiwar.model.Pet;
import com.server.pokiwar.model.UserPlayer;
import com.server.pokiwar.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
        // Fetch basic player info and pet in a single query if possible
        UserPlayerRoomDto userPlayerDto = mapper.map(
                userRepository.findById(request.getIdUser())
                        .orElseThrow(() -> new EntityNotFoundException("User not found")),
                UserPlayerRoomDto.class
        );

        PetUserDto petUserDto = mapper.map(
                petUserRepository.findById(userPlayerDto.getIdPetUser())
                        .orElseThrow(() -> new EntityNotFoundException("Pet user not found")),
                PetUserDto.class
        );

        // Preload images for user and pet
        CompletableFuture<List<ImageDto>> userImagesFuture = CompletableFuture.supplyAsync(() ->
                imageRepository.findByIdPet(request.getIdUser())
                        .stream()
                        .map(entity -> mapper.map(entity, ImageDto.class))
                        .toList()
        );

        // Fetch enemy pet details
        CompletableFuture<EnemyPet> enemyPetFuture = CompletableFuture.supplyAsync(() ->
                enemyPetRepository.findById(request.getIdEnemyPet()).orElse(null)
        );



        // Fetch count pass
        CompletableFuture<Integer> countPassFuture = CompletableFuture.supplyAsync(() ->
                {
                    try {
                        return countPassRepository.countPassBy(enemyPetFuture.get().getIdPet(), request.getIdUser())
                                .map(CountPass::getCount)
                                .orElse(0);
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        // Resolve futures
        EnemyPet enemyPet = enemyPetFuture.join();
        int countPass = countPassFuture.join();

        // Set user data
        userPlayerDto.setThumbnailPetUser(petUserDto.getThumbnailPet());
        userPlayerDto.setCountPass(countPass);

        if (enemyPet != null) {
            Pet petEnemy = petRepository.findById(enemyPet.getIdPet()).orElse(null);
            if (petEnemy != null) {
                List<ImageDto> enemyPetImg = imageRepository.findByIdPet(enemyPet.getIdPet())
                        .stream()
                        .map(entity -> mapper.map(entity, ImageDto.class))
                        .toList();
                userPlayerDto.setImageEnemyPet(enemyPetImg);
                userPlayerDto.setNamePetEnemy(petEnemy.getName());
            }
            userPlayerDto.setEnemyPet(enemyPet);
        }

        // Fetch cards and pets in parallel
        CompletableFuture<List<CardUserDto>> cardsFuture = CompletableFuture.supplyAsync(() ->
                cardUserRepository.listCardUser(request.getIdUser())
                        .stream()
                        .map(entity -> mapper.map(entity, CardUserDto.class))
                        .toList()
        );

        CompletableFuture<List<PetUserDto>> petsFuture = CompletableFuture.supplyAsync(() ->
                petUserRepository.listPetUser(request.getIdUser())
                        .stream()
                        .map(entity -> mapper.map(entity, PetUserDto.class))
                        .toList()
        );
        try {
            Pet petEnemy = petRepository.findById(enemyPetFuture.get().getIdPet()).get();
            userPlayerDto.getEnemyPet().setRequestPass(petEnemy.getConditionPass());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        // Set additional data
        userPlayerDto.setListChooseCard(cardsFuture.join());
        userPlayerDto.setListChoosePet(petsFuture.join());

        return new MessageResponse<>(LocalDateTime.now(), 200, true, "Join room", userPlayerDto);
    }

    public void pet(Long userId, Long petUserId) {
        UserPlayer userPlayer = userRepository.findById(userId).orElse(null);
        if (userPlayer != null) {
            userPlayer.setIdPetUser(petUserId);
            userRepository.save(userPlayer);
        }

    }
}
