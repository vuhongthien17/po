package com.server.pokiwar.service.impl;

import com.server.pokiwar.dto.EnemyPetDTO;
import com.server.pokiwar.dto.RequestGroup;
import com.server.pokiwar.model.CountPass;
import com.server.pokiwar.model.GroupPet;
import com.server.pokiwar.model.Pet;
import com.server.pokiwar.repository.CountPassRepository;
import com.server.pokiwar.repository.EnemyPetRepository;
import com.server.pokiwar.repository.GroupPetRepository;
import com.server.pokiwar.repository.PetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class GroupPetService {
    @Autowired
    GroupPetRepository groupPetRepository;
    @Autowired
    CountPassRepository countPassRepository;
    @Autowired
    EnemyPetRepository enemyPetRepository;
    @Autowired
    PetRepository petRepository;
    @Autowired
    ModelMapper mapper;

    public List<GroupPet> getAll(){
        return groupPetRepository.findAll();
    }

    public List<EnemyPetDTO> detail(RequestGroup request) {

        List<EnemyPetDTO> enemyPetDTOS = enemyPetRepository.getAllPetByGroup(request.getIdGroupPet())
                .stream()
                .map(enemyPet -> mapper.map(enemyPet, EnemyPetDTO.class))
                .toList();
        enemyPetDTOS.forEach(i->{

            //get pet
            Pet pet = petRepository.findById(i.getIdPet()).orElse(null);
            assert pet != null;
            i.setRequestPass(pet.getConditionPass());
            i.setThumbnail(pet.getThumbnail());
            //get count pass
            CompletableFuture<Integer> countPassFuture = CompletableFuture.supplyAsync(() ->
                    {
                        return countPassRepository.countPassBy(i.getIdPet(), request.getUserId())
                                .map(CountPass::getCount)
                                .orElse(0);
                    }
            );
            int countPass = countPassFuture.join();
            i.setCount(countPass);


        });

        return enemyPetDTOS;
    }
}
