package com.example.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Branch;
import com.example.service.BranchService;

@RestController
public class BranchRestController {
	
	@Autowired BranchService branchService;
	
	@PostMapping("rest/api/branch/createBranch")
	public Branch createBranch( @RequestBody Branch branch, BindingResult br ) {
		return branchService.createBranch(branch);
	}
	
	@GetMapping("rest/api/branch/updateBranch/{branchId}")
	public Branch updateBranch( @PathVariable Long branchId ) {
		Branch b = branchService.getBranch(branchId);
		return b;
	}
	
	@GetMapping("rest/api/branch/deleteBranch/{branchId}")
	public void deleteBranch( Long branchId ) {
		Branch b = branchService.getBranch(branchId);
		branchService.deleteBranch(b);
	}
	
	@GetMapping("rest/api/branch/findAll")
	public List<Branch> findAllBranches(  ) {
		List<Branch> branches = branchService.getAllBranches();
		return branches;
	}
	

}
