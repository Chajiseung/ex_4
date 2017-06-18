package com.choa.free;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.choa.board.BoardDTO;
import com.choa.board.BoardService;
import com.choa.util.PageMaker;

@Service
public class FreeBoardServiceImpl implements BoardService {
	
	@Inject
	private FreeBoardDAOImpl freeBoardDAOImpl;

	@Override
	public List<BoardDTO> boardList(int curPage) throws Exception {
		PageMaker pageMaker = new PageMaker(1, 30);
		String kind = null;
		String search = null;
		
		return freeBoardDAOImpl.boardList(pageMaker.getRowmaker(kind, search));
	}

	@Override
	public BoardDTO boardView(int num) throws Exception {
		return freeBoardDAOImpl.boardView(num);
	}

	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return freeBoardDAOImpl.boardWrite(boardDTO);
	}

	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return freeBoardDAOImpl.boardUpdate(boardDTO);
	}

	@Override
	public int boardDelete(int num) throws Exception {
		// TODO Auto-generated method stub
		return freeBoardDAOImpl.boradDelete(num);
	}

}
