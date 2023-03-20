package com.practice.api.search.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.api.search.model.entity.SearchWordLog;
import com.practice.api.search.model.resp.SearchWordRank;
import com.practice.api.search.repository.SearchWordLogRepository;

@Service
public class SearchService {
	private final SearchWordLogRepository searchWordRankRepository;

	@Autowired
	SearchService(SearchWordLogRepository searchWordRankRepository){
		this.searchWordRankRepository = searchWordRankRepository;
	}

	public List<SearchWordRank> selectSearchWordRank() {
		return searchWordRankRepository.findSearchWordRank();
	}

	public void saveSearchWordLog(String word) {
		var entity = new SearchWordLog();
		entity.setSearchWord(word);
		entity.setCreateAt(LocalDateTime.now());
		searchWordRankRepository.save(entity);
	}
}
