package com.practice.api.search.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.practice.api.search.model.entity.SearchWordLog;
import com.practice.api.search.model.resp.SearchWordRank;

public interface SearchWordLogRepository extends JpaRepository<SearchWordLog, Long> {
	
	@Query("SELECT new com.practice.api.search.model.resp.SearchWordRank("
			+ " A.searchWord, COUNT(*))"
			+ " FROM SearchWordLog A"
			+ " GROUP BY A.searchWord"
			+ " ORDER BY COUNT(*) DESC"
			+ " LIMIT 10")
	List<SearchWordRank> findSearchWordRank();

}
