package com.fancam.fancam.search;

import com.fancam.fancam.model.search.SearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService{

    final SearchDao searchDao;

    @Override
    public List<SearchDto> allFancamInfo() {
        return searchDao.searchAllFancamFromDB();
    }

    @Override
    public SearchDto findFancamInfoById(String id) {

        Long fancamIdx = Long.valueOf(id);
        return searchDao.searchFancamFromDBByFancamIdx(fancamIdx);
    }
}
