package com.server.pokiwar.service.impl;

import com.server.pokiwar.dto.*;
import com.server.pokiwar.exception.MessageResponse;
import com.server.pokiwar.model.*;
import com.server.pokiwar.repository.*;
import com.server.pokiwar.service.AwardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AwardServiceImpl implements AwardService {

    @Autowired
    AwardRepository awardRepository;

    @Autowired
    CountPassRepository countPassRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    PetUserRepository petUserRepository;

    @Autowired
    UpgradeStoneUserRepository upgradeStoneUserRepository;
    @Autowired
    UpgradeStoneRepository upgradeStoneRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public MessageResponse<?> giveAward(RequestAward request) {
        try {
            UserPlayer userPlayer = userRepository.findByIdAndCode(request.getIdUser(), request.getCode()).orElse(null);
            CountPass countPass = countPassRepository.countPassBy(request.getIdPet(), request.getIdUser()).orElse(null);
            Pet pet = petRepository.findById(request.getIdPet()).orElse(null);
            List<Award> awards = awardRepository.findALLByCondition(request.getIdPet(), request.getIdGroupPet());
            if (userPlayer == null) {
                return new MessageResponse<>(LocalDateTime.now(), 500, false, "bạn chưa hoàn thaành trận đấu.");
            }
            if (pet == null) {
                return new MessageResponse<>(LocalDateTime.now(), 500, false, "không tìm thấy Pet đang đánh.");
            }

            // kiếm tra nếu đã thắng lần đầu thì tạo mới count còn ngược lại thì update
            if (countPass == null) {
                countPass = new CountPass();
                countPass.setCount(1);
                countPass.setIdPet(request.getIdPet());
                countPass.setIdUser(request.getIdUser());
            } else {
                countPass.setCount(countPass.getCount() + 1);
                countPass.setIdPet(request.getIdPet());
                countPass.setIdUser(request.getIdUser());
            }
            CountPass countPassUpdate = countPassRepository.save(countPass);

            // set key đảm bảo api chỉ hoọat động 1 lần
            userPlayer.setCode(UUID.randomUUID().toString());
            userRepository.save(userPlayer);

            // kiểm tra xem đủ số lần nhận pet chưa, nếu chưa thì nhận đá, nếu rồi thì nhận pet
            if (countPassUpdate.getCount() != pet.getConditionPass()) {
                //lấy list quà là đá
                List<AwardDto> filteredAwards = awards.stream()
                        .filter(a -> a.getIdUpgradeStone() != null && !a.getIdUpgradeStone().equals(0L))
                        .map(award -> mapper.map(award, AwardDto.class))
                        .toList();
                for (AwardDto item : filteredAwards) {
                    AwardDto awardDto = mapper.map(item, AwardDto.class);
                    UpgradeStoneUser upgradeStoneUser = upgradeStoneUserRepository.findById(item.getIdUpgradeStone()).orElse(null);

                    //nếu chưa có đá thì sẽ thêm ngược lại + count
                    if (upgradeStoneUser == null) {
                        upgradeStoneUser = new UpgradeStoneUser();
                        upgradeStoneUser.setIdAward(item.getId());
                        upgradeStoneUser.setIdUpgradeStone(item.getIdUpgradeStone());
                        upgradeStoneUser.setCount(item.getCount());
                        upgradeStoneUser.setIdUser(countPassUpdate.getIdUser());
                        upgradeStoneUser.setCreateAt(LocalDateTime.now());
                        upgradeStoneUser.setCreateBy(userPlayer.getUser());

                    } else {
                        upgradeStoneUser.setCount(upgradeStoneUser.getCount() + item.getCount());
                    }
                    // set hình ảnh
                    upgradeStoneUserRepository.save(upgradeStoneUser);
                    UpgradeStoneDto upgradeStoneUserDto = mapper.map(upgradeStoneRepository.findById(item.getIdUpgradeStone()).orElse(new UpgradeStone()),UpgradeStoneDto.class);
                    List<ImageDto> imageDto = imageRepository.findByIdUpgradeStone(item.getIdUpgradeStone())
                            .stream()
                            .map(entity -> mapper.map(entity, ImageDto.class))
                            .collect(Collectors.toList());

                    upgradeStoneUserDto.setImage(imageDto);
                    item.setUpgradeStone(upgradeStoneUserDto);
                    item.setPet(null);
                }

                return new MessageResponse<>(LocalDateTime.now(), 200, true, "Nhận đá", filteredAwards);
            } else {

                // tạm thời chưa kiểm tra thông số
                PetUser petUser = new PetUser();
                petUser.setIdPet(pet.getId());
                petUser.setCreateAt(LocalDateTime.now());
                petUser.setLever(1);
                petUser.setCreateBy(userPlayer.getUser());
                petUser.setIdUser(countPass.getIdUser());

                // lấy quà là pet
                awards.stream()
                        .filter(a -> a.getIdPet() != null && a.getIdPet() != 0L)
                        .findFirst().ifPresent(item -> petUser.setIdAward(item.getId()));
                PetUserDto petUserUpdate = mapper.map(petUserRepository.save(petUser), PetUserDto.class);

                //set ảnh
                List<ImageDto> imageDto = imageRepository.findByIdPet(pet.getId())
                        .stream()
                        .map(entity -> mapper.map(entity, ImageDto.class))
                        .toList();
                petUserUpdate.setImage(imageDto);
                return new MessageResponse<>(LocalDateTime.now(), 200, true, "Nhận Pet", petUserUpdate);
            }
        } catch (Exception e) {
            return new MessageResponse<>(LocalDateTime.now(), 500, false, "Lỗi hệ thống: "+e.getMessage());
        }
    }

}
