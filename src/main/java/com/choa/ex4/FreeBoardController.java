package com.choa.ex4;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choa.board.BoardDTO;
import com.choa.free.FreeBoardServiceImpl;
import com.choa.free.FreeBoradDTO;

@Controller
@RequestMapping(value = "/freeboard/**")
public class FreeBoardController {
	@Inject
	private FreeBoardServiceImpl freeboardService;
	
	@RequestMapping(value = "freeboardList", method = RequestMethod.GET)
	public String freeboardList(@RequestParam(defaultValue = "1") int curPage, Model model) throws Exception {
		List<BoardDTO> ar = freeboardService.boardList(curPage);
		
		model.addAttribute("list", ar);
		model.addAttribute("board", "freeboard");
		
		return "board/boardList";
	}
	
	@RequestMapping(value = "freeBoardView", method = RequestMethod.GET)
	public String freeboardView(Integer num, Model model) throws Exception {
		BoardDTO boardDTO = freeboardService.boardView(num);
		
		model.addAttribute("dto", boardDTO);
		model.addAttribute("board", "freeboard");
		
		return "board/boardView";
	}
	
	@RequestMapping(value = "freeBoardWrite", method = RequestMethod.GET)
	public String freeboardWrite(Model model) {
		model.addAttribute("path", "Write");
		model.addAttribute("board", "freeboard");
		
		return "board/boardWrite";
	}
	
	@RequestMapping(value = "freeBoardWrite", method = RequestMethod.POST)
	public String freeboardWrite(FreeBoradDTO freeboardDTO, Model model) throws Exception {
		int result = freeboardService.boardWrite(freeboardDTO);
		String message = "FAIL";
		
		if(result>0) {
			message = "SUCCESS";
		}
		
		model.addAttribute("message", message);
		model.addAttribute("path", "freeboardList?curPage=1");
		
		return "common/result";
	}
	
	@RequestMapping(value = "freeBoardUpdate", method = RequestMethod.GET)
	public String freeboardUpdate(Integer num, Model model) throws Exception {
		BoardDTO boardDTO = freeboardService.boardView(num);
		
		model.addAttribute("update", boardDTO);
		model.addAttribute("path", "Update");
		model.addAttribute("board", "freeboard");
		
		return "board/boardWrite";
	}
	
	@RequestMapping(value = "freeBoardUpdate", method = RequestMethod.POST)
	public String freeboardUpdate(BoardDTO boardDTO, Model model) throws Exception {
		int result = freeboardService.boardUpdate(boardDTO);
		String message = "FAIL";
		
		if(result>0) {
			message = "SUCCESS";
		}
		
		model.addAttribute("message", message);
		model.addAttribute("path", "freeboardList?curPage=1");
		
		return "common/result";
	}
	
	@RequestMapping(value = "freeBoardDelete", method = RequestMethod.GET)
	public String freeboardDelete(Integer num, Model model) throws Exception {
		int result = freeboardService.boardDelete(num);
		String message = "FAIL";
		
		if(result>0) {
			message = "SUCCESS";
		}
		
		model.addAttribute("message", message);
		model.addAttribute("path", "noticeList?curPage=1");
		
		return "common/result";
	}
	
	
	

}
