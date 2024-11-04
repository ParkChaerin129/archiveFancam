package com.fancam.fancam.service;

import com.fancam.fancam.model.search.SearchDto;
import com.fancam.fancam.model.tag.TagInfoDto;

import java.util.List;

public interface SearchService {

    List<SearchDto> allFancamInfo();
    SearchDto findFancamInfoById(String id);
    List<SearchDto> findFancamInfoByName(String fancamName);

    List<SearchDto> findFancamInfoByTag(String tagIdx);

    List<TagInfoDto> findAllTagInfo();
}
