package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.domain.Address;
import com.example.domain.Branch;
import com.example.domain.User;
import com.example.service.BranchService;

@Controller
public class BranchController {
	
	@Autowired BranchService branchService;
	
	@PostMapping("/api/branch/createBranch")
	public ModelAndView createBranch(@Validated  @ModelAttribute Branch branch, BindingResult br ) {
		ModelAndView mv = new ModelAndView("branchForm");
		if(!br.hasErrors()) {
			branchService.createBranch(branch);
		}
		
		mv.addObject("branches", branchService.getAllBranches());
		return mv;
	}
	
	@GetMapping("/api/branch/updateBranch/{branchId}")
	public ModelAndView updateBranch( @ModelAttribute Branch branch, BindingResult br,@PathVariable Long branchId ) {
		ModelAndView mv = new ModelAndView("branchForm");
		Branch b = branchService.getBranch(branchId);
		System.out.println(b);
		mv.addObject("branches", branchService.getAllBranches());
		mv.addObject("b", b);
		return mv;
	}
	
	@GetMapping("/api/branch/deleteBranch/{branchId}")
	public ModelAndView deleteBranch( @ModelAttribute Branch branch, BindingResult br, Long branchId ) {
		ModelAndView mv = new ModelAndView("branchForm");
		Branch b = branchService.getBranch(branchId);
		branchService.deleteBranch(b);
		mv.addObject("branches", branchService.getAllBranches());
		return mv;
	}
	
	@GetMapping("api/branch/branchForm")
	public ModelAndView getForm( Branch branch, BindingResult br) {
		ModelAndView mv = new ModelAndView("branchForm");
		mv.addObject("branches", branchService.getAllBranches());
		return mv;
	}


}
