package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Branch;
import com.example.repository.BranchRepository;

@Service
public class BranchServiceImpl implements BranchService {
	
	@Autowired BranchRepository branchRepo;

	@Override
	public Branch createBranch(Branch branch) {
		Branch br = branchRepo.save(branch);
		return br;
	}

	@Override
	public Branch getBranch(Long branchId) {
		Branch br = branchRepo.findById(branchId).orElse(null);
		return br;
	}

	@Override
	public void deleteBranch(Branch branch) {
		branchRepo.delete(branch);
	}

	@Override
	public List<Branch> getAllBranches() {
		// TODO Auto-generated method stub
		return branchRepo.findAll();
	}

}
