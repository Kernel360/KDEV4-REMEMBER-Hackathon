package dev.laterre.nyamnyam.post.service;

import dev.laterre.nyamnyam.member.entity.Member;
import dev.laterre.nyamnyam.member.repository.MemberRepository;
import dev.laterre.nyamnyam.post.dto.PostDto;
import dev.laterre.nyamnyam.post.dto.PostUpdateDto;
import dev.laterre.nyamnyam.post.entity.Post;
import dev.laterre.nyamnyam.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    // 게시글 저장(생성)
    @Transactional
    public PostDto save(PostDto postDto) {
        Post post = new Post();
        post.setBoardId(postDto.getBoardId());
        Optional<Member> memberOptional = memberRepository.findById(postDto.getMemberId());
        if (memberOptional.isPresent()) {
            post.setMember(memberOptional.get());
        } else {
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }
        post.setAddress(postDto.getAddress());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setShopName(post.getShopName());
        post.setCategory(postDto.getCategory());

        postRepository.save(post);

        return postDto;
    }

    // 게시판별 모든 게시글 조회
    public List<PostDto> findPosts(Long boardId) {
        List<Post> posts = postRepository.findByBoardId(boardId);

        List<PostDto> postDtoList = posts.stream()
                .map(post -> {
                    PostDto postDto = new PostDto();
                    postDto.setBoardId(post.getBoardId());
                    postDto.setMemberId(post.getMember().getId());
                    postDto.setAddress(post.getAddress());
                    postDto.setCategory(post.getCategory());
                    postDto.setTitle(post.getTitle());
                    postDto.setContent(post.getContent());

                    return postDto;
                }).collect(Collectors.toList());

        return postDtoList;
    }

    // 특정 게시글 조회
    public PostDto findPost(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            PostDto postDto = new PostDto();
            postDto.setBoardId(post.getBoardId());
            postDto.setMemberId(post.getMember().getId());
            postDto.setAddress(post.getAddress());
            postDto.setCategory(post.getCategory());
            postDto.setTitle(post.getTitle());
            postDto.setContent(post.getContent());

            return postDto;
        } else {
            throw new RuntimeException("존재하지 않는 게시글입니다.");
        }
    }

    // 특정 게시글 수정
    @Transactional
    public PostDto updatePost(Long id, PostUpdateDto postUpdateDto) {
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

        Post savedPost = postRepository.save(postResult);

        PostDto postDto = new PostDto();
        postDto.setBoardId(savedPost.getBoardId());
        postDto.setMemberId(savedPost.getMember().getId());
        postDto.setAddress(savedPost.getAddress());
        postDto.setCategory(savedPost.getCategory());
        postDto.setTitle(savedPost.getTitle());
        postDto.setContent(savedPost.getContent());

        return postDto;
    }

    // 특정 게시글 삭제
    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
