package com.aetna.member.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetna.member.domain.AetnaMember;
import com.aetna.member.domain.MemberResponse;
import com.aetna.member.jpa.entity.DependentEntity;
import com.aetna.member.jpa.entity.MemberEntity;
import com.aetna.member.repository.MemberRepository;
import com.aetna.member.util.MemberServiceUtil;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberRepository memberRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Override
	public MemberResponse addMember(AetnaMember member) {
		
		MemberEntity memberEnty=MemberServiceUtil.populateMemberEntity(member);
		logger.info("Member Entity= "+memberEnty.toString());
		
		memberRepo.save(memberEnty);
		
		MemberResponse response=new MemberResponse();
		response.setResponseCode("SAVED");
		response.setResponseMessage(memberEnty.getMemberId()+" Saved Successfully!");
		return response;
	}
	
	@Override
	public MemberResponse viewMember(String memberId) {
		
		Optional<MemberEntity> memberEnty=memberRepo.findById(memberId);
		
		MemberResponse response=new MemberResponse();
		
		memberEnty.ifPresentOrElse(member ->{			
				AetnaMember aetnaMember=MemberServiceUtil.populateAetnaMember(member);
				response.setAetnaMember(aetnaMember);
				response.setResponseCode("RETRIEVED");
				response.setResponseMessage(aetnaMember.getMemberId()+" Retrived Successfully!");
			}, 
			()->{
				response.setResponseCode("ERROR-MEMBER-NOT-FOUND");
				response.setErrorMessage(memberId+" Not Found");
			}
		);
		
		
		return response;
	}
	
	@Override
	public MemberResponse updateMember(AetnaMember member) {
		
		MemberResponse response=this.addMember(member);
		
		response.setResponseCode("MODIFIED");
		response.setResponseMessage("Member "+member.getMemberId()+" Modified Successfully!");
		
		return response;
	}
	
	@Override
	public MemberResponse removeMember(String memberId) {
		
		Optional<MemberEntity> memberEnty=memberRepo.findById(memberId);
		
		MemberResponse response=new MemberResponse();
		
		memberEnty.ifPresentOrElse(member ->{			
				memberRepo.delete(member);
				response.setResponseCode("REMOVED");
				response.setResponseMessage(memberId+" Removed Successfully!");
			}, 
			()->{
				response.setResponseCode("ERROR-MEMBER-NOT-FOUND");
				response.setErrorMessage(memberId+" Not Found");
			}
		);
		
		
		return response;
	}
	
	@PostConstruct
	private void loadMemberData() {
		
		MemberEntity member=new MemberEntity();
		member.setMemberId("SSN6712");
		member.setFirstName("Jonathan");
		member.setLastName("Mike");
		member.setDateofbirth(new Date());
		member.setStreetLine1("13671 Masters way");
		member.setCity("Alpharetta");
		member.setSate("Georgia");
		member.setZipcode("30005");

		DependentEntity dependent1=new DependentEntity();
		dependent1.setDependentId("SSN2324");
		dependent1.setDependentFirstName("Flora");
		dependent1.setDependentLastName("Mike");
		dependent1.setDependentDateofBirth(new Date());
		dependent1.setRelationship("Spouse");
		dependent1.setMember(member);
		
		DependentEntity dependent2=new DependentEntity();
		dependent2.setDependentId("SSN2325");
		dependent2.setDependentFirstName("Morgan");
		dependent2.setDependentLastName("Mike");
		dependent2.setDependentDateofBirth(new Date());
		dependent2.setRelationship("Son");
		dependent2.setMember(member);
		
		List<DependentEntity> dependentList=new ArrayList<DependentEntity>();
		dependentList.add(dependent1);
		dependentList.add(dependent2);
		
		
		member.setDependents(dependentList);
		
		memberRepo.save(member);
	}
	
	/*public static void main(String[] args) {
		
		AetnaMember member=new AetnaMember();
		member.setMemberId("SSN6712");
		member.setFirstName("Jonathan");
		member.setLastName("Mike");
		member.setDateofbirth(new Date());
		
		MemberAddress address=new MemberAddress();
		address.setStreetLine1("13671 Masters way");
		address.setCity("Alpharetta");
		address.setSate("Georgia");
		address.setZipcode("30005");
		member.setMemberAddress(address);

		MemberDependent dependent1=new MemberDependent();
		dependent1.setDependentId("SSN2324");
		dependent1.setDependentFirstName("Flora");
		dependent1.setDependentLastName("Mike");
		dependent1.setDependentDateofBirth(new Date());
		//dependent1.setRelationship("Spouse");
		
		
		MemberDependent dependent2=new MemberDependent();
		dependent2.setDependentId("SSN2325");
		dependent2.setDependentFirstName("Morgan");
		dependent2.setDependentLastName("Mike");
		dependent2.setDependentDateofBirth(new Date());
		//dependent2.setRelationship("Son");
		//dependent2.setMember(member);
		
		List<MemberDependent> dependentList=new ArrayList<MemberDependent>();
		dependentList.add(dependent1);
		dependentList.add(dependent2);
				
		//member.setMemberDependents(Optional.of(dependentList));
		//member.setMemberDependents(Optional.empty());
		
		System.out.println("Aetna Member= "+member.toString());
		System.out.println("Aetna Member= "+member.getMemberDependents());
		
		member.getMemberDependents().ifPresent(dependentlist -> {
			dependentlist.forEach(dependent -> {
				System.out.println("Dependent="+dependent.toString());
			});
		});
	}*/
}
