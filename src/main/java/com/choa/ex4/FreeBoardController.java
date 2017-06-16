package com.choa.ex4;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.choa.free.FreeBoardServiceImpl;

@Controller
@RequestMapping(value="/free/**")
public class FreeBoardController {
	
	@Inject
	private FreeBoardServiceImpl freeBoardSericeImpl;
	
	@RequestMapping(value="freeboardList", method = RequestMethod.GET)
	public String boardList(@RequestParam(defaultValue="1") Integer curPage, Model model) throws Exception{
		
		model.addAttribute("board", "freeboard");
		model.addAttribute("list", freeBoardSericeImpl.boardList(curPage));
		
		return "board/boardList";
		
	}
	
	
	

}
