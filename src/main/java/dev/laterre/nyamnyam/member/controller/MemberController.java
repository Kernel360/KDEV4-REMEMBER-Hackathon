package dev.laterre.nyamnyam.member.controller;

import dev.laterre.nyamnyam.member.dto.MemberRequestDto;
import dev.laterre.nyamnyam.member.dto.MemberUpdateDto;
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
    public MemberRequestDto save(@RequestBody MemberRequestDto memberRequestDto) {
        return memberService.save(memberRequestDto);
    }

    @GetMapping("/{id}")
    public MemberRequestDto getMember(@PathVariable("id") Long id) {
        MemberRequestDto memberRequestDto = memberService.findMember(id);

        return memberRequestDto;
    }

    @PutMapping("/{id}")
    public MemberRequestDto updateMember(@PathVariable("id") Long id, @RequestBody MemberUpdateDto memberUpdateDto) {
        MemberRequestDto memberRequestDto = memberService.updateMember(id, memberUpdateDto);

        return memberRequestDto;
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable("id") Long id) {
        memberService.deleteMember(id);
    }
}
