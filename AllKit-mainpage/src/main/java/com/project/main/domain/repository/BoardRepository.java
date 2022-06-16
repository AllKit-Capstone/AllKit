package com.project.main.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.project.main.domain.entity.Board;
import com.project.main.dto.BoardDto;



public interface BoardRepository extends JpaRepository<Board,Integer> {
		@Transactional
		@Modifying
	    @Query("update Board b set b.boardHit = b.boardHit + 1 where boardid = ?1") //게시글 클릭할때마다 조회수 증가 sql문이라보면됨
	   int updateCount(Integer boardId);
		
		
		 //List<Board> findByboardTitleContaining(String boardTitle);
		 //List<Board>findByboardTitle(String boardTitle);
		 /*
		 * List<Board>findByTitleOrContent(String boardTitle, String boardContent);
		 */
	   Page<Board> findByboardTitleContaining(String searchKeyword,Pageable pageable);




	   
}
 