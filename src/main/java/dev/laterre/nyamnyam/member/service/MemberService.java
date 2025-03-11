package dev.laterre.nyamnyam.member.service;

import dev.laterre.nyamnyam.member.dto.MemberLoginDto;
import dev.laterre.nyamnyam.member.dto.MemberRequestDto;
import dev.laterre.nyamnyam.member.dto.MemberUpdateDto;
import dev.laterre.nyamnyam.member.model.Api;
import dev.laterre.nyamnyam.member.model.ApiData;
import dev.laterre.nyamnyam.member.model.MemberEntity;
import dev.laterre.nyamnyam.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public ApiData<Object> login(MemberLoginDto memberLoginDto) {
        Optional<MemberEntity> member = memberRepository.findByMemberRealId(memberLoginDto.getMemberRealId());
        log.info("login member_real_id: {}", memberLoginDto.getMemberRealId());
        log.info("login member: {}", member);


        if(member.isPresent()) {
            log.info("login memberLoginDto.getPassword: {}", memberLoginDto.getPassword());
            log.info("member.get().getPassword(): {}", member.get().getPassword());
            if(memberLoginDto.getPassword().equals(member.get().getPassword())) {
                var result = ApiData.builder()
                        .resultCode("200")
                        .resultMessage("로그인!")
                        .data(member)
                        .build();
                return result;
            } else {
                var result = ApiData.builder()
                        .resultCode("202")
                        .resultMessage("비밀 번호 틀림!")
                        .data(null)
                        .build();
                return result;
            }
        }

        var result = ApiData.builder()
                .resultCode("202")
                .resultMessage("존재하지 않는 ID 입니다.")
                .data(null)
                .build();

        return result;

    }

    @Transactional
    public Api save(MemberRequestDto memberRequestDto) {
        boolean existMemberRealId = findMemberByMemberRealId(memberRequestDto.getMemberRealId());
        boolean existNickedName = findMemberByNickname(memberRequestDto.getNickname());
        log.info("existMemberRealId: {}", memberRequestDto.getMemberRealId() );
        log.info("existNickedName: {}", memberRequestDto.getNickname() );
        if(existMemberRealId) {
            var result = Api.builder()
                    .resultCode("202")
                    .resultMessage("이미 존재하는 유저 ID입니다.")
                    .build();
            return result;
        }

        if(existNickedName) {
            var result = Api.builder()
                    .resultCode("202")
                    .resultMessage("이미 존재하는 닉네임입니다.")
                    .build();
            return result;
        }

        var member = MemberEntity.builder()
                        .email(memberRequestDto.getEmail())
                        .memberRealId(memberRequestDto.getMemberRealId())
                        .password(memberRequestDto.getPassword())
                        .nickname(memberRequestDto.getNickname())
                        .role(memberRequestDto.getRole())
                        .build();

        memberRepository.save(member);

        var result = Api.builder()
                .resultCode("200")
                .resultMessage("회원가입 완료!")
                .build();
        return result;
    }

    public Optional<MemberEntity>  findMember(Long id) {
        Optional<MemberEntity> member = memberRepository.findById(id);

        if (member.isPresent()) {
            return member;
        } else {
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }
    }

    public boolean  findMemberByMemberRealId (String memberRealId) {
        return memberRepository.existsByMemberRealId(memberRealId);
    }

    public boolean  findMemberByNickname (String Nickname) {
        return memberRepository.existsByNickname(Nickname);
    }


    @Transactional
    public MemberEntity updateMember(Long id, MemberUpdateDto memberUpdateDto) {

        Optional<MemberEntity> member = memberRepository.findById(id);

        if (member.isPresent()) {
            var result = MemberEntity.builder()
                    .email(memberUpdateDto.getEmail())
                    .password(memberUpdateDto.getPassword())
                    .nickname(memberUpdateDto.getNickname())
                    .role(memberUpdateDto.getRole())
                    .build();

            return memberRepository.save(result);
        } else {
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }
    }


    @Transactional
    public void deleteMember(Long id) {
        // Member 조회
        MemberEntity member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        memberRepository.deleteById(member.getId());
    }
}
