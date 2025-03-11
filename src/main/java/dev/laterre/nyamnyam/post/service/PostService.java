package dev.laterre.nyamnyam.post.service;

import dev.laterre.nyamnyam.post.dto.PostUpdateDto;
import dev.laterre.nyamnyam.post.entity.Post;
import dev.laterre.nyamnyam.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 게시글 저장(생성)
    @Transactional
    public Post save(Post post) {
        return postRepository.save(post);
    }

    // 게시판별 모든 게시글 조회
    public List<Post> findPosts(Long boardId) {
        return postRepository.findByBoardId(boardId);
    }

    // 특정 게시글 조회
    public Post findPost(Long id) {
        Optional<Post> post = postRepository.findById(id);

        if (post.isPresent()) {
            return post.get();
        } else {
            throw new RuntimeException("존재하지 않는 게시글입니다.");
        }
    }

    // 특정 게시글 수정
    @Transactional
    public Post updatePost(Long id, PostUpdateDto postUpdateDto) {
        // TODO postUpdateDto에 파일 수정 넣을지 말지
        Optional<Post> post = postRepository.findById(id);
        Post postResult;

        if (post.isPresent()) {
            postResult = post.get();
        } else {
            throw new RuntimeException("존재하지 않는 게시글입니다.");
        }

        postResult.setTitle(postUpdateDto.getTitle());
        postResult.setContent(postUpdateDto.getContent());
        postResult.setAddress(postUpdateDto.getAddress());
        postResult.setCategory(postUpdateDto.getCategory());

        return postRepository.save(postResult);
    }

    // 특정 게시글 삭제
    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
