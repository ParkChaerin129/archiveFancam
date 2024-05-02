package com.fancam.fancam.admin;

import com.fancam.fancam.model.FancamInfoDto;
import com.fancam.fancam.model.tag.TagInfoDto;
import com.fancam.fancam.model.tag.TaggingInfoDto;
import com.fancam.fancam.model.tag.TaggingInfoDtoId;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class AdminDao {


    private final FancamRepository fancamRepository;
    private final TagRepository tagRepository;
    private final TaggingRepository taggingRepository;



    public Long createNewFancamToDB(FancamInfoDto fancamInfoDto) throws Exception{

        FancamInfoDto save = fancamRepository.save(fancamInfoDto);
        return save.getFancamIdx();

    }

    public Long createNewTagToDB(TagInfoDto tagInfoDto) throws Exception{
        TagInfoDto save = tagRepository.save(tagInfoDto);
        return save.getTagIdx();
    }

    public ArrayList<Long> getTagNameIdxArray(ArrayList<String> tagNames) throws Exception {

        ArrayList<Long> TagNameIdxArray = new ArrayList<>();

        for(String tagName : tagNames){

            Long id;
            TagInfoDto tagInfoDto = tagRepository.findByTagName(tagName).orElse(null);
            if(tagInfoDto==null){
                TagInfoDto newTagInfoDto = TagInfoDto.builder().tagName(tagName).build();
                id=createNewTagToDB(newTagInfoDto);
            }
            else{
                id=tagInfoDto.getTagIdx();
            }

            TagNameIdxArray.add(id);
        }
        return TagNameIdxArray;
    }

    public void createNewTagging(Long fancamIdx,ArrayList<Long> tagIdxs){

        for(Long tagIdx: tagIdxs){
            TaggingInfoDtoId taggingInfoDtoId = new TaggingInfoDtoId();
            taggingInfoDtoId.setFancamidx(fancamIdx);
            taggingInfoDtoId.setTagidx(tagIdx);

            TaggingInfoDto taggingInfoDto = TaggingInfoDto.builder().taggingInfoDtoId(taggingInfoDtoId).build();
            taggingRepository.save(taggingInfoDto);
        }
    }

}
