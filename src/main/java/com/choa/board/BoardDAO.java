package com.choa.board;

import java.util.List;

import com.choa.util.Rowmaker;

public interface BoardDAO {
	
	public List<BoardDTO> boardList(Rowmaker rowmaker) throws Exception;
	
	public BoardDTO boardView(int num) throws Exception;
	
	public int boardWrite(BoardDTO boardDTO) throws Exception;
	
	public int boardUpdate(BoardDTO boardDTO) throws Exception;
	
	public int boradDelete(int num) throws Exception;
	
	public int boardCount() throws Exception;
	
	public void boardHit(int num) throws Exception;

}
