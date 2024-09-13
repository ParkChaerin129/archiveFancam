package com.fancam.fancam.controller;


import com.fancam.fancam.model.search.SearchDto;
import com.fancam.fancam.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public SearchDto findFancamInfo(@PathVariable String id){
        return searchService.findFancamInfoById(id);
    }

    @GetMapping("/name")
    public List<SearchDto> findFancamInfoByName(@RequestParam String q){
        return searchService.findFancamInfoByName(q);
    }

}
