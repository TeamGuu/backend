package teamguu.backend.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamguu.backend.domain.member.entity.Member;
import teamguu.backend.exception.situation.MemberNotFoundException;
import teamguu.backend.domain.member.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

//    @Transactional(readOnly = true)
//    public List<MemberSimpleResponseDto> findAllMembers() {
//        List<Member> members = memberRepository.findAll();
//        return members.stream()
//                .map(MemberSimpleResponseDto::toDto)
//                .toList();
//    }
//
//    @Transactional(readOnly = true)
//    public MemberSimpleResponseDto findMember(Long id) {
//        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
//        return MemberSimpleResponseDto.toDto(member);
//    }
}
