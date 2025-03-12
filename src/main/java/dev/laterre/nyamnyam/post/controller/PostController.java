package dev.laterre.nyamnyam.post.controller;

import dev.laterre.nyamnyam.member.service.MemberService;
import dev.laterre.nyamnyam.post.dto.PostDto;
import dev.laterre.nyamnyam.post.dto.PostUpdateDto;
import dev.laterre.nyamnyam.post.model.PostEntity;
import dev.laterre.nyamnyam.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final MemberService memberService;


    // 게시글 저장(생성)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PostDto save(
        @RequestPart("content") PostDto postDto, // JSON 데이터
        @RequestPart(value = "file", required = false) MultipartFile mediaData // 파일 데이터
    ) {

        if (mediaData != null) {
            String encodedMediaData = postService.encodeFileToBase64(mediaData);
            postDto.setMediaData(encodedMediaData);
            log.info("mediaData : {}",encodedMediaData);
        }

      return postService.save(postDto);
    }

    // 게시판별 모든 게시글 조회
    @GetMapping("/{boardId}")
    public List<PostDto> getPosts(@PathVariable("boardId") Long boardId) {
        log.info("boardId {}", boardId);
        return postService.findPosts(boardId);
    }

    // 특정 게시글 조회
    @GetMapping("/id/{id}")
    public PostDto getPost(@PathVariable("id") Long id) {
        PostDto postDto = postService.findPost(id);
        log.info("postId : {}", id);
        return postDto;
    }

    // 특정 게시글 조회
    @GetMapping("/{boardId}/topone")
    public PostDto getTopOne(
            @PathVariable(name = "boardId") Long boardId
    ) {
        return postService.findTopPost(boardId);
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

    // /page/1?page=2&size=6
    @GetMapping("/page/{boardId}")
    public ResponseEntity<Page<PostEntity>> getPostsByBoard(
            @PathVariable(name="boardId") Long boardId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "6") int size) {

        Page<PostEntity> posts = postService.getPostsByBoard(boardId, page, size);
        return ResponseEntity.ok(posts);
    }
}
