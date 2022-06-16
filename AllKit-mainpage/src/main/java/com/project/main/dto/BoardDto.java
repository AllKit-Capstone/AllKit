package com.project.main.dto;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.main.domain.entity.Board;

import lombok.*;


@Table(name = "board")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int boardId;
	
	private int userId;
	
	@Column(length = 100, nullable = false)
	private String boardTitle;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String boardContent;
	@Column(length = 10, nullable = false)
	private String boardWriter;
	private LocalDateTime boardDate;
	
	@Column(columnDefinition = "integer default 0")
	private int boardHit;	

	 
	
	public Board toEntity(){
		Board build = Board.builder()
	    		   .boardId(boardId)
                   .userId(userId)
                   .boardTitle(boardTitle)
                   .boardContent(boardContent)
                   .boardWriter(boardWriter)
                   .boardHit(boardHit)
                   .build();
	      return build;
	    }
	
	@Builder
    public BoardDto(Integer boardId, Integer userId, String boardTitle, String boardContent, String boardWriter, LocalDateTime boardDate, Integer boardHit){
        this.boardId = boardId;
        this.userId = userId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardWriter = boardWriter;
        this.boardDate = boardDate;
        this.boardHit = boardHit;
    }

	
	
	
	

}
