package com.aetna.member.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.aetna.member.domain.AetnaMember;
import com.aetna.member.domain.MemberResponse;
import com.aetna.member.service.MemberService;

@Component
public class MemberResourceImpl implements MemberResource {

	@Autowired
	MemberService memberService;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberResourceImpl.class);
	@Override
	public ResponseEntity<MemberResponse> addAetnaMember(AetnaMember aetnaMember) {
		
		logger.info("Aetna Member details= "+aetnaMember.toString());
		
		MemberResponse response=memberService.addMember(aetnaMember);
		
		return ResponseEntity.ok(response);
		
	}
	
	@Override
	public ResponseEntity<MemberResponse> viewAetnaMember(String memberId) {
		
		logger.info("View Aetna Member details= "+memberId);
		
		MemberResponse response=memberService.viewMember(memberId);
		
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<MemberResponse> modifyMember(AetnaMember aetnaMember) {
		
		logger.info("Modify Aetna Member details= "+aetnaMember.getMemberId());
		
		MemberResponse response=memberService.updateMember(aetnaMember);
		
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<MemberResponse> removeAetnaMember(String memberId) {
		
		logger.info("Remove Aetna Member = "+memberId);
		
		MemberResponse response=memberService.removeMember(memberId);
		
		return ResponseEntity.ok(response);
	}
	
	/*@Override
	public ResponseEntity<String> healthCheck() {
		
		logger.info("Performing HealthCheck...");
		
		return ResponseEntity.ok("Success!!");
	}*/
}
