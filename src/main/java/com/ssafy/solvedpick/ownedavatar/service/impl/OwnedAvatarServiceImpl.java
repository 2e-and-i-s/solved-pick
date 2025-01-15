package com.ssafy.solvedpick.ownedavatar.service.impl;

import com.ssafy.solvedpick.avatars.domain.Grade;
import com.ssafy.solvedpick.ownedavatar.dto.OwnedAvatarDTO;
import com.ssafy.solvedpick.ownedavatar.repository.OwnedAvatarRepository;
import com.ssafy.solvedpick.ownedavatar.service.OwnedAvatarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OwnedAvatarServiceImpl implements OwnedAvatarService {

    private final OwnedAvatarRepository ownedAvatarRepository;

    @Override
    public List<OwnedAvatarDTO> getOwnedAvatars(Long memberId) {
        return ownedAvatarRepository.findAllByMemberId(memberId)
                .stream()
                .map(ownedAvatar -> OwnedAvatarDTO.builder()
                        .visible(ownedAvatar.isVisible())
                        .ownedAvatarId(ownedAvatar.getId())
                        .name(ownedAvatar.getAvatar().getTitle())
                        .rarity(Grade.fromValue(ownedAvatar.getAvatar().getGrade()).name())
                        .dropRate(Grade.fromValue(ownedAvatar.getAvatar().getGrade()).getProbability())
                        .build())
                .toList();
    }
}