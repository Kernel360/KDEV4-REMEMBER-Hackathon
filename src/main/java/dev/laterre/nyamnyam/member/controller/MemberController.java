package dev.laterre.nyamnyam.member.controller;

import dev.laterre.nyamnyam.member.dto.MemberUpdateDto;
import dev.laterre.nyamnyam.member.entity.Member;
import dev.laterre.nyamnyam.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public Member save(@RequestBody Member member) {
        return memberService.save(member);
    }

    @GetMapping("/{id}")
    public Member getPost(@PathVariable Long id) {
        Member member = memberService.findMember(id);

        return member;
    }

    @PutMapping("/{id}")
    public Member updateMember(@PathVariable Long id, @RequestBody MemberUpdateDto memberUpdateDto) {
        Member member = memberService.updateMember(id, memberUpdateDto);

        return member;
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}
