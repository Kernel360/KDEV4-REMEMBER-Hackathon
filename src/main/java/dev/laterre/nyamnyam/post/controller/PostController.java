package dev.laterre.nyamnyam.post.controller;

import dev.laterre.nyamnyam.member.entity.Member;
import dev.laterre.nyamnyam.member.service.MemberService;
import dev.laterre.nyamnyam.post.dto.PostUpdateDto;
import dev.laterre.nyamnyam.post.entity.Post;
import dev.laterre.nyamnyam.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final MemberService memberService;


    // 게시글 저장(생성)
    @PostMapping("/{boardId}")
    public Post save(@RequestBody Post post, @RequestParam Long memberId, @PathVariable Long boardId) {
        Member member = memberService.findMember(memberId);
        post.setMember(member);
        post.setBoardId(boardId);

        Post postResult = postService.save(post);

        return postResult;
    }

    // 게시판별 모든 게시글 조회
    @GetMapping("/{boardId}")
    public List<Post> getPosts(@PathVariable Long boardId) {
        log.info("temp {}", boardId);
        return postService.findPosts(boardId);
    }

    // 특정 게시글 조회
    @GetMapping("/{boardId}/{id}")
    public Post getPost(@PathVariable Long boardId, @PathVariable Long id) {
        Post post = postService.findPost(id);
        log.info("boardId: {}", boardId);

        return post;
    }

    // 특정 게시글 수정
    @PutMapping("/{boardId}/{id}")
    public Post updatePost(@PathVariable Long boardId, @PathVariable Long id, @RequestBody PostUpdateDto postUpdateDto) {
        Post post = postService.updatePost(id, postUpdateDto);
        log.info("boardId: {}", boardId);

        return post;
    }

    // 특정 게시글 삭제
    @DeleteMapping("/{boardId}/{id}")
    public void deletePost(@PathVariable Long boardId, @PathVariable Long id) {
        postService.deletePost(id);
        log.info("boardId: {}", boardId);
    }
}
