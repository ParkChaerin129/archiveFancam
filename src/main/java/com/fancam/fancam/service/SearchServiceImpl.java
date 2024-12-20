package com.fancam.fancam.service;

import com.fancam.fancam.model.search.SearchDto;
import com.fancam.fancam.model.tag.TagInfoDto;
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
            SearchDto searchDto = searchDao.searchFancamFromDBByFancamIdx(id);
            if(searchDto != null) {
                searchDtoList.add(searchDto);
            }
        }
        return searchDtoList;
    }

    @Override
    public List<SearchDto> findFancamInfoByTag(String tagName) {

        Long tagIdx = searchDao.searchTagIdxByTagName(tagName);
        List<Long> fancamIdList = searchDao.searchFancamByTagIdx(tagIdx);
        List<SearchDto> searchDtoList = new ArrayList<>();
        for(final Long id : fancamIdList) {
            SearchDto searchDto = searchDao.searchFancamFromDBByFancamIdx(id);
            if(searchDto != null) {
                searchDtoList.add(searchDto);
            }
        }
        return searchDtoList;
    }

    @Override
    public List<TagInfoDto> findAllTagInfo() {


        return searchDao.searchAllTagInfo();
    }

    @Override
    public List<SearchDto> findFancamInfoByDate(String date) {

        List<Long> fancamIdList = searchDao.searchFancamByDateFromDB(date);
        List<SearchDto> searchDtoList = new ArrayList<>();
        for(final Long id : fancamIdList) {
            SearchDto searchDto = searchDao.searchFancamFromDBByFancamIdx(id);
            if(searchDto != null) {
                searchDtoList.add(searchDto);
            }
        }
        return searchDtoList;
    }

}
