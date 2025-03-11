package dev.laterre.nyamnyam.post.service;

import dev.laterre.nyamnyam.member.model.MemberEntity;
import dev.laterre.nyamnyam.member.repository.MemberRepository;
import dev.laterre.nyamnyam.post.dto.PostDto;
import dev.laterre.nyamnyam.post.dto.PostUpdateDto;
import dev.laterre.nyamnyam.post.model.PostEntity;
import dev.laterre.nyamnyam.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    // 게시글 저장(생성)
    @Transactional
    public PostDto save(PostDto postDto) {
        PostEntity postEntity = new PostEntity();
        postEntity.setBoardId(postDto.getBoardId());
        Optional<MemberEntity> memberOptional = memberRepository.findById(postDto.getMemberId());
        if (memberOptional.isPresent()) {
            postEntity.setMember(memberOptional.get());
        } else {
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }
        postEntity.setAddress(postDto.getAddress());
        postEntity.setTitle(postDto.getTitle());
        postEntity.setContent(postDto.getContent());
        postEntity.setShopName(postDto.getShopName());
        postEntity.setCategory(postDto.getCategory());

        postRepository.save(postEntity);

        return postDto;
    }

    // 게시판별 모든 게시글 조회
    public List<PostDto> findPosts(Long boardId) {
        List<PostEntity> postEntities = postRepository.findByBoardId(boardId);
        try {
            List<PostDto> postDtoList = postEntities.stream()
                    .map(post -> {
                        PostDto postDto = new PostDto();
                        postDto.setBoardId(post.getBoardId());
                        postDto.setMemberId(post.getMember().getId());
                        postDto.setAddress(post.getAddress());
                        postDto.setShopName(post.getShopName());
                        postDto.setCategory(post.getCategory());
                        postDto.setTitle(post.getTitle());
                        postDto.setContent(post.getContent());

                        return postDto;
                    }).collect(Collectors.toList());
            log.info("postDtoList : {}", postDtoList);
            return postDtoList;
        } catch (Exception e) {
            List<PostDto> list = new ArrayList<>();
            return list;
        }
    }

    // 특정 게시글 조회
    public PostDto findPost(Long id) {
        Optional<PostEntity> postOptional = postRepository.findById(id);

        if (postOptional.isPresent()) {
                PostEntity postEntity = postOptional.get();
                PostDto postDto = new PostDto();
                postDto.setBoardId(postEntity.getBoardId());
                postDto.setMemberId(postEntity.getMember().getId());
                postDto.setAddress(postEntity.getAddress());
                postDto.setShopName(postEntity.getShopName());
                postDto.setCategory(postEntity.getCategory());
                postDto.setTitle(postEntity.getTitle());
                postDto.setContent(postEntity.getContent());

                return postDto;
        } else {
            throw new RuntimeException("존재하지 않는 게시글입니다.");
        }
    }

    // 특정 게시글 수정
    @Transactional
    public PostDto updatePost(Long id, PostUpdateDto postUpdateDto) {
        // TODO postUpdateDto에 파일 수정 넣을지 말지
        Optional<PostEntity> post = postRepository.findById(id);
        PostEntity postEntityResult;

        if (post.isPresent()) {
            postEntityResult = post.get();
        } else {
            throw new RuntimeException("존재하지 않는 게시글입니다.");
        }

        postEntityResult.setTitle(postUpdateDto.getTitle());
        postEntityResult.setContent(postUpdateDto.getContent());
        postEntityResult.setAddress(postUpdateDto.getAddress());
        postEntityResult.setCategory(postUpdateDto.getCategory());
        postEntityResult.setShopName(postUpdateDto.getShopName());

        PostEntity savedPostEntity = postRepository.save(postEntityResult);

        PostDto postDto = new PostDto();
        postDto.setBoardId(savedPostEntity.getBoardId());
        postDto.setMemberId(savedPostEntity.getMember().getId());
        postDto.setShopName(savedPostEntity.getShopName());
        postDto.setAddress(savedPostEntity.getAddress());
        postDto.setCategory(savedPostEntity.getCategory());
        postDto.setTitle(savedPostEntity.getTitle());
        postDto.setContent(savedPostEntity.getContent());

        return postDto;
    }

    // 특정 게시글 삭제
    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public String encodeFileToBase64(MultipartFile file) {
        try {
            byte[] fileBytes = file.getBytes();
            return Base64.getEncoder().encodeToString(fileBytes);
        } catch (IOException e) {
            log.info(e.getMessage());
            return null;
        }
    }
}
