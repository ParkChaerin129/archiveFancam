package com.fancam.fancam.search;

import com.fancam.fancam.model.search.SearchDto;

import java.util.List;

public interface SearchService {

    List<SearchDto> allFancamInfo();
    SearchDto findFancamInfoById(String id);

}
