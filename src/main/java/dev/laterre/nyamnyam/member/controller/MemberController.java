package dev.laterre.nyamnyam.member.controller;

import dev.laterre.nyamnyam.member.dto.MemberLoginDto;
import dev.laterre.nyamnyam.member.dto.MemberRequestDto;
import dev.laterre.nyamnyam.member.dto.MemberUpdateDto;
import dev.laterre.nyamnyam.member.model.Api;
import dev.laterre.nyamnyam.member.model.ApiData;
import dev.laterre.nyamnyam.member.model.MemberEntity;
import dev.laterre.nyamnyam.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@CrossOrigin(origins = {"http://localhost:3000", "https://nyamnyam-frontend.vercel.app/"})
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ApiData<Object> login(@RequestBody MemberLoginDto memberLoginDto) {
        return memberService.login(memberLoginDto);
    }

    @PostMapping
    public Api save(@RequestBody MemberRequestDto memberRequestDto) {
        return memberService.save(memberRequestDto);
    }

    @GetMapping("/{id}")
    public Optional<MemberEntity> getMember(@PathVariable("id") Long id) {
        return memberService.findMember(id);
    }

    @PutMapping("/{id}")
    public MemberEntity updateMember(@PathVariable("id") Long id, @RequestBody MemberUpdateDto memberUpdateDto) {
        return memberService.updateMember(id, memberUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable("id") Long id) {
        memberService.deleteMember(id);
    }

}
