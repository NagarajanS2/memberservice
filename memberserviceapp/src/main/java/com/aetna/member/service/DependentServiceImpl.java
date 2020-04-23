package com.aetna.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aetna.member.domain.AetnaMember;
import com.aetna.member.domain.DependentResponse;
import com.aetna.member.domain.MemberDependent;
import com.aetna.member.jpa.entity.DependentEntity;
import com.aetna.member.jpa.entity.MemberEntity;
import com.aetna.member.repository.DependentRepository;
import com.aetna.member.repository.MemberRepository;
import com.aetna.member.util.MemberServiceUtil;

@Service
public class DependentServiceImpl implements DependentService {

	@Autowired
	DependentRepository dependentRepo;
	
	@Autowired
	MemberRepository memberRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(DependentServiceImpl.class);
	
	@Override
	public DependentResponse addDependent(AetnaMember member) {
		
		DependentResponse response=new DependentResponse();
		
		Optional<MemberEntity> memberEntyOption= memberRepo.findById(member.getMemberId());
		
		memberEntyOption.ifPresentOrElse(memberEnty ->{
					
			member.getMemberDependents().ifPresent(dependentlist -> {
				List<DependentEntity> dependentEntyList=new ArrayList<DependentEntity>();
				dependentlist.forEach(dependent -> {
					logger.info("Dependent="+dependent.toString());
					DependentEntity dependentEnty=MemberServiceUtil.populateDependentEntity(dependent);
					/*dependentEnty.setDependentId(dependent.getDependentId());
					dependentEnty.setDependentFirstName(dependent.getDependentFirstName());
					dependentEnty.setDependentLastName(dependent.getDependentLastName());
					dependentEnty.setDependentDateofBirth(dependent.getDependentDateofBirth());
					dependentEnty.setRelationship(dependent.getRelationship());*/
					dependentEnty.setMember(memberEnty);
					dependentEntyList.add(dependentEnty);
				});
				
				if(memberEnty.getDependents()!=null) {
					memberEnty.getDependents().addAll(dependentEntyList);
				}
			});
			memberRepo.save(memberEnty);
			response.setResponseCode("SAVED");
			response.setResponseMessage("Dependents of "+memberEnty.getMemberId()+" Saved Successfully!");
			response.setMemberId(memberEnty.getMemberId());
		},
		()->{
			response.setResponseCode("ERROR-MEMBER-NOT-FOUND");
			response.setErrorMessage(member.getMemberId()+" Not Found");
		});
		
		return response;
	}
	
	@Override
	public DependentResponse updateDependent(AetnaMember member) {

		DependentResponse response=this.addDependent(member);
		
		response.setResponseCode("MODIFIED");
		response.setResponseMessage("Dependent(s) of "+member.getMemberId()+" Modified Successfully!");
		
		return response;
		
	}
	
	@Override
	public DependentResponse listDependents(String memberId) {
		
		DependentResponse response=new DependentResponse();
		
		Optional<MemberEntity> memberEntyOption= memberRepo.findById(memberId);
		
		List<MemberDependent> dependentList=new ArrayList<MemberDependent>();
		
		memberEntyOption.ifPresentOrElse(memberEnty ->{
					
			if(memberEnty.getDependents()!=null) {
				memberEnty.getDependents().forEach(dependentEnty -> {
				
					MemberDependent dependent=new MemberDependent();
					dependent.setDependentId(dependentEnty.getDependentId());
					dependent.setDependentFirstName(dependentEnty.getDependentFirstName());
					dependent.setDependentLastName(dependentEnty.getDependentLastName());
					dependent.setDependentDateofBirth(dependentEnty.getDependentDateofBirth());
					dependent.setRelationship(dependentEnty.getRelationship());
					
					dependentList.add(dependent);
				});
				response.setResponseCode("RETRIEVED");
				response.setResponseMessage("Dependents of "+memberId+" Retrieved Successfully!");
				response.setMemberId(memberId);
				response.setMemberDependents(Optional.of(dependentList));
				
			}else {
				response.setResponseCode("NO_DEPENDENT_RETRIEVED");
				response.setResponseMessage("No Dependents added for "+memberId);
			}
		},
		() ->{
			response.setResponseCode("ERROR-MEMBER-NOT-FOUND");
			response.setErrorMessage(memberId+" Not Found");
		});
			
		return response;
	}
	
	@Override
	public DependentResponse removeDependent(String dependentId) {
		
		Optional<DependentEntity> dependentEnty=dependentRepo.findById(dependentId);
		
		DependentResponse response=new DependentResponse();
		
		dependentEnty.ifPresentOrElse(dependent ->{			
				dependentRepo.delete(dependent);
				response.setResponseCode("REMOVED");
				response.setResponseMessage(dependentId+" Removed Successfully!");
			}, 
			()->{
				response.setResponseCode("ERROR-MEMBER-NOT-FOUND");
				response.setErrorMessage(dependentId+" Not Found");
			}
		);
		
		
		return response;
	}
}
