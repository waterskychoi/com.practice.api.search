package com.practice.api.search.model.resp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchWordRank {
    private String searchWord;
    private Long searchCount;
    
    SearchWordRank(String searchWord, Long searchCount){
    	this.searchWord = searchWord;
    	this.searchCount = searchCount;
    }
}
