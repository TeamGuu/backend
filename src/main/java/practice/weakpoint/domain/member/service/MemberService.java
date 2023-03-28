package practice.weakpoint.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.weakpoint.domain.member.entity.Member;
import practice.weakpoint.domain.member.dto.MemberSimpleResponseDto;
import practice.weakpoint.exception.situation.MemberNotFoundException;
import practice.weakpoint.domain.member.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MemberSimpleResponseDto> findAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(MemberSimpleResponseDto::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public MemberSimpleResponseDto findMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        return MemberSimpleResponseDto.toDto(member);
    }
}
