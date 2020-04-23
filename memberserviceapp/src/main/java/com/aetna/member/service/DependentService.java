package com.aetna.member.service;

import com.aetna.member.domain.AetnaMember;
import com.aetna.member.domain.DependentResponse;

public interface DependentService {

	public DependentResponse addDependent(AetnaMember member);
	
	public DependentResponse updateDependent(AetnaMember member);
	
	public DependentResponse listDependents(String memberId);
	
	public DependentResponse removeDependent(String dependentId);
}
