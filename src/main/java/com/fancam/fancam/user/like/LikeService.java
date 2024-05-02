package com.fancam.fancam.user.like;

public interface LikeService {
    void createNewLike(Long fancamIdx, Long userIdx);
    void inactiveLike(Long fancamIdx,Long userIdx);
}
