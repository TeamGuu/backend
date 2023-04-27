package teamguu.backend.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import teamguu.backend.config.aws.AmazonS3Service;
import teamguu.backend.config.redis.RedisService;
import teamguu.backend.domain.member.dto.member.EditMemberInfoRequestDto;
import teamguu.backend.domain.member.entity.Member;
import teamguu.backend.exception.situation.MemberNotFoundException;
import teamguu.backend.domain.member.repository.MemberRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final RedisService redisService;
    private final AmazonS3Service amazonS3Service;

    public void deleteMember(Member member) {
        redisService.deleteValues("RT: " + member.getUsername());
        deleteProfileImageIfExits(member);
        memberRepository.delete(member);
    }

    public String changeLogoImageToNew(MultipartFile logoImage, Member member){
        String uploadedProfileImageUrl = amazonS3Service.uploadFile((logoImage));
        deleteProfileImageIfExits(member);
        return member.changeProfileImageUrl(uploadedProfileImageUrl);
    }

    public void changeProfileImageToBasic(Member member) {
        String deleteProfileImageUrl = member.getProfileImageUrl();
        member.changeProfileImageUrl("nothing");
        amazonS3Service.deleteFile(deleteProfileImageUrl);
    }

    public void editMemberInfo(EditMemberInfoRequestDto editMemberRequestDto) {

        // TODO 중복 확인 or 휴대폰 인증번호
        Member currentMember = getCurrentMember();
        currentMember.editMember(editMemberRequestDto.getName(), editMemberRequestDto.getPhone(), editMemberRequestDto.getBirth());
    }

    public Member getCurrentMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return memberRepository.findByUsername(authentication.getName())
                .orElseThrow(MemberNotFoundException::new);
    }

    private void deleteProfileImageIfExits(Member memberToCheck) {
        if (!memberToCheck.getProfileImageUrl().equals("nothing")) {
            amazonS3Service.deleteFile(memberToCheck.getProfileImageUrl());
        }
    }
}
