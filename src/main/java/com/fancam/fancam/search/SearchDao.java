package com.fancam.fancam.search;


import com.fancam.fancam.model.FancamInfoDto;
import com.fancam.fancam.model.search.SearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SearchDao {

    private final FancamRepository fancamRepository;

    public List<SearchDto> searchAllFancamFromDB(){

        List<SearchDto> searchDtoList = new ArrayList<>();

        List<Object[]> results = fancamRepository.howManyLikesFancam();
        for(Object[] result:results){
            SearchDto searchDto = new SearchDto();
            SearchDto.Fancam fancam = new SearchDto.Fancam();

            //fancam 정보

            FancamInfoDto f = (FancamInfoDto) result[0];
            fancam.setName(f.getName());
            fancam.setDate(f.getDate());
            fancam.setStatus(f.getStatus());
            fancam.setMember(f.getMember());
            fancam.setFancamidx(f.getFancamidx());
            fancam.setFancam_url(f.getFancam_url());
            searchDto.setFancam(fancam);

            // fancamIdx
            Long fancamidx = f.getFancamidx();

            // 좋아요 수
            Long l = (Long) result[1];
            SearchDto.Likes likes = new SearchDto.Likes();
            likes.setLikeCount(l);
            searchDto.setLikes(likes);

            //태그 리스트 찾기
            List<String> tagNames = fancamRepository.findTagNamesByPostId(fancamidx);

            ArrayList<SearchDto.Tag> tags = new ArrayList<>();
            for(String tagName:tagNames){
                SearchDto.Tag tag = new SearchDto.Tag();
                tag.setTagNames(tagName);
                tags.add(tag);
            }
            searchDto.setTags(tags);

            // searchDto 배열에 담기
            searchDtoList.add(searchDto);

        }

        return searchDtoList;
    }

    public SearchDto searchFancamFromDBByFancamIdx(Long fancamidx){
        SearchDto searchDto = new SearchDto();
        SearchDto.Fancam fancam = new SearchDto.Fancam();

        List<Object[]> results = fancamRepository.fancamInfo(fancamidx);

        if(results.isEmpty()){
            return null;
        }

        Object[] result = results.get(0);

        FancamInfoDto f = (FancamInfoDto) result[0];
        fancam.setName(f.getName());
        fancam.setDate(f.getDate());
        fancam.setStatus(f.getStatus());
        fancam.setMember(f.getMember());
        fancam.setFancamidx(f.getFancamidx());
        fancam.setFancam_url(f.getFancam_url());
        searchDto.setFancam(fancam);

        // 좋아요 수
        Long l = (Long) result[1];
        SearchDto.Likes likes = new SearchDto.Likes();
        likes.setLikeCount(l);
        searchDto.setLikes(likes);

        List<String> tagNames = fancamRepository.findTagNamesByPostId(fancamidx);

        ArrayList<SearchDto.Tag> tags = new ArrayList<>();
        for(String tagName:tagNames){
            SearchDto.Tag tag = new SearchDto.Tag();
            tag.setTagNames(tagName);
            tags.add(tag);
        }
        searchDto.setTags(tags);

        return searchDto;

    }
}
