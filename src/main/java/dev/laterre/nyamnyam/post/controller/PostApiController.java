package dev.laterre.nyamnyam.post.controller;

import dev.laterre.nyamnyam.post.db.PostEntity;
import dev.laterre.nyamnyam.post.model.PostRequest;
import dev.laterre.nyamnyam.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/post")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;
    @GetMapping("/id/{boardId}")
    public List<PostEntity> find(
            @PathVariable("boardId")
            Long boardId
    ) {
        log.info("id : {}", boardId);
        return postService.find(boardId);
    }


    @PostMapping("/add")
    public PostEntity save(
            @RequestBody PostRequest postRequest
    ) {
        log.info("postRequest: {}", postRequest);
        return postService.save(postRequest);
    }
}