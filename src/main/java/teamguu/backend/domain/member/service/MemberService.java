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
import teamguu.backend.domain.member.dto.member.GetMemberInfoResponseDto;
import teamguu.backend.domain.member.entity.Member;
import teamguu.backend.exception.situation.MemberNotFoundException;
import teamguu.backend.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final RedisService redisService;
    private final AmazonS3Service amazonS3Service;

    public GetMemberInfoResponseDto getCurrentMemberInfo() {
        return GetMemberInfoResponseDto.toDto(getCurrentMember());
    }

    public void deleteMember() {
        Member currentMember = getCurrentMember();
        redisService.deleteValues("RT: " + currentMember.getUsername());
        deleteProfileImageIfExists(currentMember);
        memberRepository.delete(currentMember);
    }

    @Transactional
    public String changeProfileImageToNew(MultipartFile profileImage){
        Member currentMember = getCurrentMember();
        deleteProfileImageIfExists(currentMember);
        return currentMember.changeProfileImageUrl(amazonS3Service.uploadFile((profileImage)));
    }

    @Transactional
    public void changeProfileImageToBasic() {
        Member currentMember = getCurrentMember();
        String deleteProfileImageUrl = currentMember.getProfileImageUrl();
        currentMember.changeProfileImageUrl("basic_profile.png");
        amazonS3Service.deleteFile(deleteProfileImageUrl);
    }

    @Transactional
    public void editMemberInfo(EditMemberInfoRequestDto editMemberRequestDto) {
        getCurrentMember().editMember(editMemberRequestDto.getName(), editMemberRequestDto.getPhone(), editMemberRequestDto.getBirth());
    }

    public Member getCurrentMember() {
        return memberRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(MemberNotFoundException::new);
    }

    private void deleteProfileImageIfExists(Member memberToCheck) {
        if (!memberToCheck.getProfileImageUrl().equals("basic_profile.png")) {
            amazonS3Service.deleteFile(memberToCheck.getProfileImageUrl());
        }
    }
}
