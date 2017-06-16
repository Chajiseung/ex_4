package com.choa.ex4;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choa.board.BoardDTO;
import com.choa.notice.NoticeDTO;
import com.choa.notice.NoticeServiceImpl;
import com.choa.util.MakePage;
import com.choa.util.PageMaker;
import com.choa.util.Rowmaker;

@Controller
@RequestMapping(value="/notice/**")
public class NoticeController {
	
	@Inject //데이터 type으로 찾음.
	private NoticeServiceImpl noticeService;
	
	
	@RequestMapping(value="noticeList", method = RequestMethod.GET)
	public String noticeList(Model model, @RequestParam(defaultValue="1")Integer curPage) throws Exception{
		List<BoardDTO> ar = noticeService.boardList(curPage);
		model.addAttribute("list", ar);
		model.addAttribute("board", "notice");
		
		return "board/boardList";
	}
	
	@RequestMapping(value="noticeView", method = RequestMethod.GET)
	public void noticeView(Integer num, Model model) throws Exception{
		
			
		BoardDTO boardDTO = noticeService.boardView(num);
		model.addAttribute("dto", boardDTO);
	}
	//Write Form
	@RequestMapping(value="noticeWrite", method = RequestMethod.GET)
	public void noticeWrite(Model model){
		model.addAttribute("path", "write");
	}
	
	@RequestMapping(value="noticeWrite", method = RequestMethod.POST)
	public String noticeWrite(BoardDTO boardDTO, Model model, RedirectAttributes rd) throws Exception{
		
		
		int result = noticeService.boardWrite(boardDTO);
		String message = "fail";
		
		if(result > 0){
			message ="success";
		}
		/*model.addAttribute("message", message);*/
		rd.addFlashAttribute("message", message);
		
		/*return "notice/result";*/
		return "redirect:noticeList";
		
	}
	//Update Form
	@RequestMapping(value="noticeUpdate", method = RequestMethod.GET)
	public String noticeUpdate(Integer num, Model model) throws Exception{
		
		BoardDTO boardDTO = noticeService.boardView(num);
		
		model.addAttribute("dto", boardDTO);
		model.addAttribute("path", "Update");
		
		return "notice/noticeWrite";
	}
	
	@RequestMapping(value="noticeUpdate", method = RequestMethod.POST)
	public String noticeUpdate(BoardDTO boardDTO, RedirectAttributes rd) throws Exception{
		
		int result = noticeService.boardUpdate(boardDTO);
		String message = "fail";
		
		if(result > 0){
			message = "success";
		}
		rd.addFlashAttribute("message", message);
		
		return "redirect:noticeList";
	}
	
	@RequestMapping(value="noticeDelete", method = RequestMethod.GET)
	public String noticeDelete(Integer num, RedirectAttributes rd) throws Exception{
		
		
			int result = noticeService.boardDelete(num);
			String message = "fail";
			
			if(result > 0){
				message = "success";
			}
			
			rd.addFlashAttribute("message", message);
			
			return "redirect:noticeList";
	}
	

}
