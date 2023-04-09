package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.Branch;

@Service
public interface BranchService {
	
	public Branch createBranch(Branch branch);
	public Branch getBranch(Long branchId);
	public void deleteBranch(Branch br);
	public List<Branch> getAllBranches();
;
}
