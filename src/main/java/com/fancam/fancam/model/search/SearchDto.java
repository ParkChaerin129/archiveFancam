package com.fancam.fancam.model.search;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class SearchDto {

    @Getter
    @Setter
    public static class Fancam{

        private String Name;

        private String date;

        private String member;

        private String fancam_url;

        private String status;

        private Long fancamidx;
    }

    @Getter
    @Setter
    public static class Likes{
        private Long likeCount;
    }

    @Getter
    @Setter
    public static class Tag{
        private String TagNames;
    }

    private Fancam fancam;
    private Likes likes;
    private ArrayList<Tag> tags;

}
