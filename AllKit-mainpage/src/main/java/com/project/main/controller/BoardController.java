package com.project.main.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.main.domain.entity.Board;
import com.project.main.domain.repository.BoardRepository;
import com.project.main.dto.BoardDto;

import com.project.main.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {

	

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private BoardService boardService;

	//글목록
	@GetMapping("/list")
	public String list(Model model,@PageableDefault(size=3,sort = "boardId",direction = Sort.Direction.DESC) Pageable pageable, 
			String searchKeyword) {
	  
		//Page<Board> boards = boardService.getBoardListPage(pageable);
		
		Page<Board> boards = null;
		
		if(searchKeyword == null)
		{
			 boards = boardService.getBoardListPage(pageable);
		}else
		{
			 boards = boardService.boardSearchList(searchKeyword, pageable);
		}
		
	
		//Page<Board> boards = boardRepository.findByBoardTitleContainingOrBoardContentContaining(searchText, searchText, pageable);
		int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
		int endPage = Math.min(boards.getTotalPages(),boards.getPageable().getPageNumber() + 4);
		 model.addAttribute("startPage",startPage);
		 model.addAttribute("endPage",endPage);
		 model.addAttribute("boards",boards);

        return "board/list";
       
	} 
	

	//글작성
	@GetMapping("/form")
	  public String form(){
		

		return "board/form";
	 }

	 @PostMapping("/form")
	 public String form(BoardDto dto) {
	     boardService.savePost(dto);
	     return "redirect:/board/list";
	 }
	 
	
	  
	 //글 상세보기
	  @GetMapping("/detail") 
      public String detail( Model model, Integer boardId) {
      BoardDto boardDto = boardService.getPost(boardId);
      boardService.increaseHitCount(boardId);
      model.addAttribute("boardDto", boardDto);
      return "board/detail";
    }
	  
	  //글삭제
	  @GetMapping("/delete")
	  public String boardDelete(Integer boardId) {
		  
		  boardService.boardDelete(boardId);
		  return "redirect:/board/list";
	  }
	  
	  //수정
	  @GetMapping("/modify/{boardId}")
	  public String boardModify(@PathVariable(value = "boardId") Integer boardId, Model model)
	  {
		  
	      BoardDto boardDto = boardService.getPost(boardId);
		  model.addAttribute("boardDto",boardDto);
		  return "board/modify";
	  }
	
	  @PostMapping("/update/{boardId}")
	  public String boardUpdate(@PathVariable(value = "boardId") Integer boardId, BoardDto boardDto)
	  {
		  BoardDto boardTemp = boardService.getPost(boardId);
		  	  
		  //덮어씌우기
		  boardTemp.setBoardTitle(boardDto.getBoardTitle());
		  boardTemp.setBoardContent(boardDto.getBoardContent());
		  
		  boardService.savePost(boardTemp);
		  
		  
		  return "redirect:/board/list";
	  }
	 
	

	 
	
	/*
	 * @GetMapping("/detail") public String detail(Model model) {
	 * 
	 * 
	 * model.addAttribute("board",new BoardDto()); return "board/detail"; }
	 * 
	 * 
	 * @GetMapping("/form") 
	 * public String form(Model model,@RequestParam(required =
	 * false) Integer board_id) {
	 * 
	 * if(board_id == null) { System.out.println("신규");
	 * model.addAttribute("board",new BoardDto());
	 * 
	 * } else { System.out.println("수정"); BoardDto board =
	 * boardRepository.findById(board_id).orElse(null);
	 * model.addAttribute("board",board);
	 * 
	 * }
	 * 
	 * return "board/form"; }
	 * 
	 * @PostMapping("/form") 
	 * public String greetingSubmit(@ModelAttribute BoardDto
	 * board) throws ParseException { //System.out.println(board.getBoard_date());
	 * 
	 * LocalDateTime datetime = LocalDateTime.now(); board.setBoard_date(datetime);
	 * boardRepository.save(board); //기존에 데이터가 수정되었을 경우 update, 기존에 데이터가 없는 상태에서
	 * save하면 insert return "redirect:/board/list"; //완료가되면 이동될 페이지(게시판) }
	 * 
	 */

}
