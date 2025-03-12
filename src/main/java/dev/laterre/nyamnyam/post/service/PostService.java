package dev.laterre.nyamnyam.post.service;

import dev.laterre.nyamnyam.likes.repository.LikesRepository;
import dev.laterre.nyamnyam.member.model.MemberEntity;
import dev.laterre.nyamnyam.member.repository.MemberRepository;
import dev.laterre.nyamnyam.post.dto.PostDto;
import dev.laterre.nyamnyam.post.dto.PostUpdateDto;
import dev.laterre.nyamnyam.post.model.PostEntity;
import dev.laterre.nyamnyam.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final LikesRepository likesRepository;

    // 게시글 저장(생성)
    @Transactional(readOnly = true)
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
        postEntity.setMediaData(postDto.getMediaData());

        postRepository.save(postEntity);

        return postDto;
    }

    // 게시판별 모든 게시글 조회
    @Transactional(readOnly = true)
    public List<PostDto> findPosts(Long boardId) {
        List<PostEntity> postEntities = postRepository.findByBoardId(boardId);
        try {
            List<PostDto> postDtoList = postEntities.stream()
                    .map(post -> {
                        PostDto postDto = new PostDto();
                        postDto.setId(post.getId());
                        postDto.setBoardId(post.getBoardId());
                        postDto.setMemberId(post.getMember().getId());
                        postDto.setNickname(post.getMember().getNickname());
                        postDto.setAddress(post.getAddress());
                        postDto.setShopName(post.getShopName());
                        postDto.setCategory(post.getCategory());
                        postDto.setTitle(post.getTitle());
                        postDto.setContent(post.getContent());
                        postDto.setMediaData(post.getMediaData());
                        postDto.setLikes(likesRepository.countByPostId(post.getId()));

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
    @Transactional(readOnly = true)
    public PostDto findPost(Long id) {
        Optional<PostEntity> postOptional = postRepository.findById(id);

        if (postOptional.isPresent()) {
                log.info("findPost : {}", postOptional.get());
                PostEntity postEntity = postOptional.get();
                PostDto postDto = new PostDto();
                postDto.setId(postEntity.getId());
                postDto.setBoardId(postEntity.getBoardId());
                postDto.setMemberId(postEntity.getMember().getId());
                postDto.setAddress(postEntity.getAddress());
                postDto.setNickname(postEntity.getMember().getNickname());
                postDto.setShopName(postEntity.getShopName());
                postDto.setCategory(postEntity.getCategory());
                postDto.setTitle(postEntity.getTitle());
                postDto.setContent(postEntity.getContent());
                postDto.setMediaData(postEntity.getMediaData());
                postDto.setLikes(likesRepository.countByPostId(postEntity.getId()));

                return postDto;
        } else {
            throw new RuntimeException("존재하지 않는 게시글입니다.");
        }
    }

    @Transactional(readOnly = true)
    public PostDto findTopPost(Long boardId) {
        List<PostEntity> postEntities = postRepository.findByBoardId(boardId);
            List<PostDto> postDtoList = postEntities.stream()
                    .map(post -> {
                        PostDto postDto = new PostDto();
                        postDto.setId(post.getId());
                        postDto.setBoardId(post.getBoardId());
                        postDto.setMemberId(post.getMember().getId());
                        postDto.setNickname(post.getMember().getNickname());
                        postDto.setAddress(post.getAddress());
                        postDto.setShopName(post.getShopName());
                        postDto.setCategory(post.getCategory());
                        postDto.setTitle(post.getTitle());
                        postDto.setContent(post.getContent());
                        postDto.setMediaData(post.getMediaData());
                        postDto.setLikes(likesRepository.countByPostId(post.getId()));

                        return postDto;
                    }).collect(Collectors.toList());

                    var result = postDtoList.stream()
                    .max((p1, p2) -> Integer.compare(Math.toIntExact(p1.getLikes()), Math.toIntExact(p2.getLikes()))).get();

                    log.info("postDtoList : {}", postDtoList);
                    return result;
    }

    // 특정 게시글 수정
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
        postDto.setId(savedPostEntity.getId());
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
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<PostEntity> getPostsByBoard(Long boardId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);;
        return postRepository.findByBoardId(boardId, pageable);
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
