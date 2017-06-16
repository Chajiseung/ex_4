package com.choa.notice;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.choa.board.BoardDTO;
import com.choa.board.BoardService;
import com.choa.util.MakePage;
import com.choa.util.PageMaker;
import com.choa.util.Rowmaker;

@Service
//NoticeService noticeService = new NoticeService();
public class NoticeServiceImpl implements BoardService{
	
	@Inject
	private NoticeDAOImpl noticeDAO;
	
	/*public NoticeService(NoticeDAO noticeDAO){
		this.noticeDAO = noticeDAO;
	}*/
	
	
	
	
	
	@Override
	public List<BoardDTO> boardList(int curPage) throws Exception {
		int result = noticeDAO.boardCount();
		PageMaker pageMaker = new PageMaker(curPage);
		
		String kind = null;
		String search = null;
		
		return noticeDAO.boardList(pageMaker.getRowmaker(kind, search));
	}

	@Override
	public BoardDTO boardView(int num) throws Exception {
		
		return noticeDAO.boardView(num);
	}

	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		
		return noticeDAO.boardWrite(boardDTO);
	}

	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		
		return noticeDAO.boardUpdate(boardDTO);
	}

	@Override
	public int boardDelete(int num) throws Exception {
		
		return noticeDAO.boradDelete(num);
	}
	
	/*public NoticeDTO noticeView(int num) throws Exception{
		
		
		
	}

	public List<NoticeDTO> noticeList(Integer curPage) throws Exception{
		
		
		
		
		
	}

	public int noticeWrite(NoticeDTO noticeDTO) throws Exception{
		
		return noticeDAO.noticeWrite(noticeDTO);
	}
	
	public int noticeUpdate(NoticeDTO noticeDTO) throws Exception{
		return noticeDAO.noticeUpdate(noticeDTO);
	}
	
	public int noticeDelete(int num) throws Exception{
		return noticeDAO.noticeDelete(num);
	}*/

}
