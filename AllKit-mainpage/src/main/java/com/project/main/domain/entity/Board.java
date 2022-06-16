package com.project.main.domain.entity;

import org.hibernate.MappingException;
import org.springframework.boot.context.properties.bind.Name;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;



@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Board extends TimeEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer boardId;

	private Integer userId;	
	
	@Column(length = 10, nullable = false)
	private String boardWriter;
	
	@Column(length = 100, nullable = false)
	private String boardTitle;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String boardContent;
	
	@Column(columnDefinition = "integer default 0")
	private Integer boardHit;
	

	
	
		
	  	@Builder
	    public Board(Integer boardId, Integer userId, String boardWriter, String boardTitle, String boardContent,Integer boardHit){
	      this.boardId = boardId;
	      this.userId = userId;
	      this.boardWriter = boardWriter;
	      this.boardTitle = boardTitle;
	      this.boardContent = boardContent;
	      this.boardHit = boardHit;
	    }




}
