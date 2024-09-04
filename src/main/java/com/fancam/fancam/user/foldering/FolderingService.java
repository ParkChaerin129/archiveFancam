package com.fancam.fancam.user.foldering;

import com.fancam.fancam.model.FancamInfoDto;
import com.fancam.fancam.model.search.SearchDto;

import java.util.List;

public interface FolderingService {

    void createNewFoldering(Long fancamIdx,Long folderIdx);
    void inactiveFoldering(Long fancamIdx,Long folderIdx);
    List<SearchDto> getFolderFancamList(Long userIdx,Long folderIdx);
}
