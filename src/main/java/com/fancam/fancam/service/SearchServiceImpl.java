package com.fancam.fancam.service;

import com.fancam.fancam.model.search.SearchDto;
import com.fancam.fancam.repositrory.dao.SearchDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

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

    @Override
    public List<SearchDto> findFancamInfoByName(String fancamName) {
        List<Long> fancamIdList = searchDao.searchFancamByNamePartFromDB(fancamName);
        List<SearchDto> searchDtoList = new ArrayList<>();
        for(final Long id : fancamIdList) {
            searchDtoList.add(searchDao.searchFancamFromDBByFancamIdx(id));
        }
        return searchDtoList;
    }

}
