package dev.laterre.nyamnyam.likes.controller;

import dev.laterre.nyamnyam.likes.dto.LikesRequestDto;
import dev.laterre.nyamnyam.likes.model.Api;
import dev.laterre.nyamnyam.likes.model.LikesEntity;
import dev.laterre.nyamnyam.likes.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/remember/like")
@RequiredArgsConstructor
public class LikesController {

    private final LikesService likesService;

    @GetMapping("/{postId}")
    public Optional<LikesEntity> findLikesByPostId(
            @PathVariable("postId") Long postId
    ) {
        return likesService.findLikesByPostId(postId);
    }

    @PostMapping("/status")
    public Api likesPost(
            @RequestBody LikesRequestDto likesRequestDto
    ) {
        return likesService.likesPost(likesRequestDto.getMemberId(), likesRequestDto.getPostId());
    }

    @GetMapping("/count/{postId}")
    public ResponseEntity<Long> likesCount(@PathVariable("postId") Long postId) {
        long likeCount = likesService.countLikesByPostId(postId);
        return ResponseEntity.ok(likeCount);
    }

}
