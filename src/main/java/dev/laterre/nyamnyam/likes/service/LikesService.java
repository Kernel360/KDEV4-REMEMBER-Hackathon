package dev.laterre.nyamnyam.likes.service;

import dev.laterre.nyamnyam.likes.model.Api;
import dev.laterre.nyamnyam.likes.model.LikesEntity;
import dev.laterre.nyamnyam.likes.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository;


    public Api likesPost(Long memberId, Long postId) {
        if (likesRepository.existsByMemberIdAndPostId(memberId, postId)) {
            // 실패 , 이미 눌렀어
            var result = Api.builder()
                    .resultCode("202")
                    .resultMessage("좋아요 이미 눌렀어").build();
            return result;
        } else {
            var entity = LikesEntity.builder()
                    .postId(postId).memberId(memberId).build();
            likesRepository.save(entity);

            var result = Api.builder()
                    .resultCode("200")
                    .resultMessage("좋아요!").build();

            return result;
        }
    }


    public long countLikesByPostId(Long postId) {
        return likesRepository.countByPostId(postId);
    }
}
