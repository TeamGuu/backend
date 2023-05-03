package teamguu.backend.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import teamguu.backend.config.aws.AmazonS3Service;
import teamguu.backend.config.redis.RedisService;
import teamguu.backend.domain.member.dto.member.EditMemberInfoRequestDto;
import teamguu.backend.domain.member.entity.Member;
import teamguu.backend.exception.situation.MemberNotFoundException;
import teamguu.backend.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final RedisService redisService;
    private final AmazonS3Service amazonS3Service;

    public void deleteMember() {
        Member currentMember = getCurrentMember();
        redisService.deleteValues("RT: " + currentMember.getUsername());
        deleteProfileImageIfExits(currentMember);
        memberRepository.delete(currentMember);
    }

    public String changeProfileImageToNew(MultipartFile profileImage){
        Member currentMember = getCurrentMember();
        String uploadedProfileImageUrl = amazonS3Service.uploadFile((profileImage));
        deleteProfileImageIfExits(currentMember);
        return currentMember.changeProfileImageUrl(uploadedProfileImageUrl);
    }

    public void changeProfileImageToBasic() {
        Member currentMember = getCurrentMember();
        String deleteProfileImageUrl = currentMember.getProfileImageUrl();
        currentMember.changeProfileImageUrl("basic_profile.png");
        amazonS3Service.deleteFile(deleteProfileImageUrl);
    }

    public void editMemberInfo(EditMemberInfoRequestDto editMemberRequestDto) {
        Member currentMember = getCurrentMember();
        currentMember.editMember(editMemberRequestDto.getName(), editMemberRequestDto.getPhone(), editMemberRequestDto.getBirth());
    }

    @Transactional(readOnly = true)
    public Member getCurrentMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return memberRepository.findByUsername(authentication.getName())
                .orElseThrow(MemberNotFoundException::new);
    }

    private void deleteProfileImageIfExits(Member memberToCheck) {
        if (!memberToCheck.getProfileImageUrl().equals("basic_profile.png")) {
            amazonS3Service.deleteFile(memberToCheck.getProfileImageUrl());
        }
    }
}
