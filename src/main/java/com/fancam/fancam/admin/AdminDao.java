package com.fancam.fancam.admin;

import com.fancam.fancam.model.FancamInfoDto;
import com.fancam.fancam.model.tag.TagInfoDto;
import com.fancam.fancam.model.tag.TaggingInfoDto;
import com.fancam.fancam.model.tag.TaggingInfoDtoId;
import com.fancam.fancam.search.FancamRepository;
import com.fancam.fancam.search.TagRepository;
import com.fancam.fancam.search.TaggingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AdminDao {


    private final FancamRepository fancamRepository;
    private final TagRepository tagRepository;
    private final TaggingRepository taggingRepository;



    public Long saveFancamToDB(FancamInfoDto fancamInfoDto) throws Exception{

        FancamInfoDto save = fancamRepository.save(fancamInfoDto);
        return save.getFancamidx();

    }

    public FancamInfoDto getFancamFromDB(Long fancamIdx){
        Optional<FancamInfoDto> fancamInfoDto = fancamRepository.findById(fancamIdx);
        return fancamInfoDto.get();
    }

    public Long saveTagToDB(TagInfoDto tagInfoDto) throws Exception{
        TagInfoDto save = tagRepository.save(tagInfoDto);
        return save.getTagIdx();
    }

    public TagInfoDto getTagFromDB(Long tagIdx){
        Optional<TagInfoDto> tagInfoDto = tagRepository.findByTagIdx(tagIdx);
        return tagInfoDto.get();
    }

    public ArrayList<Long> getTagNameIdxArray(ArrayList<String> tagNames) throws Exception {

        ArrayList<Long> TagNameIdxArray = new ArrayList<>();

        for(String tagName : tagNames){

            Long id;
            TagInfoDto tagInfoDto = tagRepository.findByTagName(tagName).orElse(null);
            if(tagInfoDto==null){
                TagInfoDto newTagInfoDto = TagInfoDto.builder().tagName(tagName).status("ACTIVE").build();
                id= saveTagToDB(newTagInfoDto);
            }
            else{
                id=tagInfoDto.getTagIdx();
            }

            TagNameIdxArray.add(id);
        }
        return TagNameIdxArray;
    }

    public void saveTaggingByNewFancam(Long fancamIdx, ArrayList<Long> tagIdxs){

        for(Long tagIdx: tagIdxs){
            TaggingInfoDtoId taggingInfoDtoId = new TaggingInfoDtoId();
            taggingInfoDtoId.setFancamidx(fancamIdx);
            taggingInfoDtoId.setTagidx(tagIdx);

            TaggingInfoDto taggingInfoDto = TaggingInfoDto.builder().taggingInfoDtoId(taggingInfoDtoId).status("ACTIVE").build();
            taggingRepository.save(taggingInfoDto);
        }
    }

    public void saveTagging(TaggingInfoDto taggingInfoDto){
        taggingRepository.save(taggingInfoDto);
    }


}
