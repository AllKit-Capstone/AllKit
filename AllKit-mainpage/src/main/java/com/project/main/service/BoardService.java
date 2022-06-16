package com.project.main.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import com.project.main.domain.repository.BoardRepository;
import com.project.main.dto.BoardDto;

import lombok.RequiredArgsConstructor;

import com.project.main.domain.entity.Board;
import com.project.main.domain.entity.TimeEntity;

@Service
@RestController
@RequestMapping("/api")
public class BoardService extends TimeEntity implements Serializable {
	

	  private BoardRepository boardRepository;
	

	  public BoardService(BoardRepository boardRepository){
	      this.boardRepository = boardRepository;
	  }


		/* 글 저장 DB에도 form */
	  @Transactional
	  public Integer savePost(BoardDto boardDto){
	      return boardRepository.save(boardDto.toEntity()).getBoardId();
	  }




//
//	  public List<Board> getBoardlist(){
//
//		  return boardRepository.findAll();
//		
//	
//	  }
//	  
	  @Transactional
	    public Page<Board> getBoardListPage(Pageable pageable) {
	        return boardRepository.findAll(pageable);

	    }
	  
	  public Page<Board> boardSearchList(String searchKeyword, Pageable pageable){
		  return boardRepository.findByboardTitleContaining(searchKeyword,pageable);
	  }
	  
	  //게시글 리스트 체크
//	    @Transactional 
//	    public List<BoardDto> getBoardList(){
//    	Sort sort = Sort.by(Direction.DESC, "boardId", "boardDate");
//
//
//			List<Board> boards = boardRepository.findAll(sort);
//	        List<BoardDto> boardDtoList = new ArrayList<>();
//
//	        for(Board board : boards){
//	            BoardDto dto = BoardDto.builder()
//	                    .boardId(board.getBoardId())
//	                    .userId(board.getUserId())
//	                    .boardTitle(board.getBoardTitle())
//	                    .boardContent(board.getBoardContent())
//	                    .boardWriter(board.getBoardWriter())
//	                    .boardDate(board.getBoardDate())
//	                    .boardHit(board.getBoardHit())
//	                    .build();
//
//	            boardDtoList.add(dto);
//	        }
//	        return boardDtoList;
//	    }  
//	    
	
	    
		/* 글상세보기 메서드(detail) */
	    @Transactional
	    public BoardDto getPost(Integer boardId){
	        Optional<Board> boardWrapper = boardRepository.findById(boardId);
	        if(boardWrapper.isPresent())
	        {
	            Board board = boardWrapper.get();

	            BoardDto boardDto = BoardDto.builder()
	                    .boardId(board.getBoardId())
	                    .userId(board.getUserId())
	                    .boardTitle(board.getBoardTitle())
	                    .boardContent(board.getBoardContent())
	                    .boardWriter(board.getBoardWriter())
	                    .boardDate(board.getBoardDate())
	                    .boardHit(board.getBoardHit())
	                    .build(); 

	            return boardDto;
	        }

	        return null;
	    }
	    
	    
	    //글 상세보기 
//	    public Board boardView(Integer boardId) {
//			  
//	    	  
//			  return boardRepository.findById(boardId).get();
//		  }
//	    

	    
	    
	    //글 삭제하기
	    public void boardDelete(Integer boardId) {
	    	boardRepository.deleteById(boardId);
	    }

	    
	    //조회수
		public int increaseHitCount(Integer boardId) {
			return boardRepository.updateCount(boardId);
			
		}


	
		
	    
	    
	    
}
