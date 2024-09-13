package com.fancam.fancam.service;

public interface LikeService {
    void createNewLike(Long fancamIdx, Long userIdx);
    void inactiveLike(Long fancamIdx,Long userIdx);
    boolean isLiked(Long fancamIdx,Long userIdx);
}
