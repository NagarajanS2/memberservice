package com.aetna.member.service;

import com.aetna.member.domain.AetnaMember;
import com.aetna.member.domain.MemberResponse;

public interface MemberService {

	public MemberResponse addMember(AetnaMember member);
	
	public MemberResponse viewMember(String memberId);
	
	public MemberResponse updateMember(AetnaMember member);
	
	public MemberResponse removeMember(String memberId);
}
