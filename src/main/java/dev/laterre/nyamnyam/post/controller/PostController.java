package dev.laterre.nyamnyam.post.controller;

import dev.laterre.nyamnyam.member.service.MemberService;
import dev.laterre.nyamnyam.post.dto.PostDto;
import dev.laterre.nyamnyam.post.dto.PostUpdateDto;
import dev.laterre.nyamnyam.post.entity.Post;
import dev.laterre.nyamnyam.post.repository.PostRepository;
import dev.laterre.nyamnyam.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/remember/post")
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final MemberService memberService;


    // 게시글 저장(생성)
    @PostMapping
    public PostDto save(@RequestBody PostDto postDto) {

        PostDto postDtoResult = postService.save(postDto);

        return postDtoResult;
    }

    // 게시판별 모든 게시글 조회
    @GetMapping("/{boardId}")
    public List<PostDto> getPosts(@PathVariable("boardId") Long boardId) {
        log.info("temp {}", boardId);
        return postService.findPosts(boardId);
    }

    // 특정 게시글 조회
    @GetMapping("/{boardId}/{id}")
    public PostDto getPost(@PathVariable("boardId") Long boardId, @PathVariable("id") Long id) {
        PostDto postDto = postService.findPost(id);
        log.info("boardId: {}", boardId);

        return postDto;
    }

    // 특정 게시글 수정
    @PutMapping("/{boardId}/{id}")
    public PostDto updatePost(@PathVariable("boardId") Long boardId, @PathVariable("id") Long id, @RequestBody PostUpdateDto postUpdateDto) {
        PostDto postDto = postService.updatePost(id, postUpdateDto);
        log.info("boardId: {}", boardId);

        return postDto;
    }

    // 특정 게시글 삭제
    @DeleteMapping("/{boardId}/{id}")
    public void deletePost(@PathVariable("boardId") Long boardId, @PathVariable("id") Long id) {
        postService.deletePost(id);
        log.info("boardId: {}", boardId);
    }


    @GetMapping("/page")
    public ResponseEntity<Page<Post>> getPostsByBoard(
            @RequestParam("boardId") Long boardId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "6") int size) {

        Page<Post> posts = postService.getPostsByBoard(boardId, page, size);
        return ResponseEntity.ok(posts);
    }


}
