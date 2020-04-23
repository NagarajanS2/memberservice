package com.aetna.member.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.aetna.member.domain.AetnaMember;
import com.aetna.member.domain.DependentResponse;
import com.aetna.member.service.DependentService;

@Component
public class DependentResourceImpl implements DependentResource{

	private static final Logger logger = LoggerFactory.getLogger(DependentResourceImpl.class);
	
	@Autowired
	DependentService dependentService;
	
	@Override
	public ResponseEntity<DependentResponse> addDependent(AetnaMember aetnaMember) {
		
		logger.info("Add Aetna Member Dependent details= "+aetnaMember);
		
		DependentResponse response=dependentService.addDependent(aetnaMember);
		
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<DependentResponse> modifyDependent(AetnaMember aetnaMember) {
		
		logger.info("Modify Aetna Member Dependent details= "+aetnaMember);
		
		DependentResponse response=dependentService.updateDependent(aetnaMember);
		
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<DependentResponse> listDependents(String memberId) {
		logger.info("List all Dependent details= "+memberId);
		
		DependentResponse response=dependentService.listDependents(memberId);
		
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<DependentResponse> removDependent(String dependentId) {
		
		logger.info("Remove Dependent= "+dependentId);
		
		DependentResponse response=dependentService.removeDependent(dependentId);
		
		return ResponseEntity.ok(response);
	}
}
