package com.fancam.fancam.search;


import com.fancam.fancam.model.search.SearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService){
        this.searchService=searchService;
    }

    @GetMapping("/all")
    public List<SearchDto> allFancamInfo(){
        return searchService.allFancamInfo();
    }

}
