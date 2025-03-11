package dev.laterre.nyamnyam.member.service;

import dev.laterre.nyamnyam.member.dto.MemberRequestDto;
import dev.laterre.nyamnyam.member.dto.MemberUpdateDto;
import dev.laterre.nyamnyam.member.entity.Member;
import dev.laterre.nyamnyam.member.repository.MemberRepository;
import dev.laterre.nyamnyam.post.entity.Post;
import dev.laterre.nyamnyam.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Transactional
    public MemberRequestDto save(MemberRequestDto memberRequestDto) {
        Member member = new Member();
        member.setEmail(memberRequestDto.getEmail());
        member.setMemberRealId(memberRequestDto.getMemberRealId());
        member.setPassword(memberRequestDto.getPassword());
        member.setNickname(memberRequestDto.getNickname());
        member.setRole(memberRequestDto.getRole());

        memberRepository.save(member);

        return memberRequestDto;
    }

    public MemberRequestDto findMember(Long id) {
        Optional<Member> member = memberRepository.findById(id);

        if (member.isPresent()) {
            MemberRequestDto memberRequestDto = new MemberRequestDto();
            memberRequestDto.setMemberRealId(member.get().getMemberRealId());
            memberRequestDto.setPassword(member.get().getPassword());
            memberRequestDto.setEmail(member.get().getEmail());
            memberRequestDto.setNickname(member.get().getNickname());
            memberRequestDto.setRole(member.get().getRole());
            return memberRequestDto;
        } else {
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }
    }

    @Transactional
    public MemberRequestDto updateMember(Long id, MemberUpdateDto memberUpdateDto) {

        Optional<Member> member = memberRepository.findById(id);
        Member memberResult;

        if (member.isPresent()) {
            memberResult = member.get();
        } else {
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }

        memberResult.setEmail(memberUpdateDto.getEmail());
        memberResult.setPassword(memberUpdateDto.getPassword());
        memberResult.setNickname(memberUpdateDto.getNickname());
        memberResult.setRole(memberUpdateDto.getRole());

        Member savedMember = memberRepository.save(memberResult);

        MemberRequestDto memberRequestDto = new MemberRequestDto();
        memberRequestDto.setMemberRealId(savedMember.getMemberRealId());
        memberRequestDto.setPassword(savedMember.getPassword());
        memberRequestDto.setEmail(savedMember.getEmail());
        memberRequestDto.setNickname(savedMember.getNickname());
        memberRequestDto.setRole(savedMember.getRole());

        return memberRequestDto;
    }


    @Transactional
    public void deleteMember(Long id) {
        // Member 조회
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        List<Post> posts = postRepository.findAll().stream()
                .filter(p -> p.getMember().getId() == id)
                .collect(Collectors.toList());

        // Member와 연결된 Post들의 연관 관계 끊기
        for (Post post : posts) {
            post.setMember(null);  // Post의 member 필드를 null로 설정
        }

        memberRepository.deleteById(id);
    }
}
