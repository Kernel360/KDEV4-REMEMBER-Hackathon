package dev.laterre.nyamnyam.post.service;

import dev.laterre.nyamnyam.post.db.PostEntity;
import dev.laterre.nyamnyam.post.db.PostRepository;
import dev.laterre.nyamnyam.post.model.PostRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostEntity> find(Long boardId) {
        var result = postRepository.findByBoardId(boardId);
        log.info("boardId : {}", boardId);
        log.info("boardId : {}", result);

        return result;
    }

    public PostEntity save(PostRequest postRequest) {
        var entity = PostEntity.builder()
                .boardId(postRequest.getBoardId())
                .userId(postRequest.getUserId())
                .address(postRequest.getAddress())
                .category(postRequest.getCategory())
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .build();

        return postRepository.save(entity);
    }

}
