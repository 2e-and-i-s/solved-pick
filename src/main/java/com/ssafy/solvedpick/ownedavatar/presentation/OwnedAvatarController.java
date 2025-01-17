package com.ssafy.solvedpick.ownedavatar.presentation;

import com.ssafy.solvedpick.ownedavatar.dto.MemberAvatarResponseDTO;
import com.ssafy.solvedpick.ownedavatar.dto.OwnedAvatarDTO;
import com.ssafy.solvedpick.ownedavatar.service.OwnedAvatarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("user/me")
public class OwnedAvatarController {

    private final OwnedAvatarService ownedAvatarService;

    // TODO: JWT 기능의 부재로 현재는 Authorization header
    @GetMapping("/avatar")
    public ResponseEntity<?> getMemberAvatar(@RequestHeader(value = "Authorization") Long memberId) {
        List<OwnedAvatarDTO> avatars = ownedAvatarService.getOwnedAvatars(memberId);
        MemberAvatarResponseDTO result = MemberAvatarResponseDTO.builder()
                .avatars(avatars)
                .build();

        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/avatar/{ownedAvatarId}")
    public ResponseEntity<?> updateAvatarVisibility(
            @RequestHeader(value = "Authorization") Long memberId,
            @PathVariable Long ownedAvatarId
    ) {
        ownedAvatarService.updateAvatarVisibility(memberId, ownedAvatarId);
        return ResponseEntity.noContent().build();
    }
}
