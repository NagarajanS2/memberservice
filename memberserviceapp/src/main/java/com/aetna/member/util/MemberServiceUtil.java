package com.aetna.member.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aetna.member.domain.AetnaMember;
import com.aetna.member.domain.MemberAddress;
import com.aetna.member.domain.MemberDependent;
import com.aetna.member.jpa.entity.DependentEntity;
import com.aetna.member.jpa.entity.MemberEntity;

public class MemberServiceUtil {

	private static final Logger logger = LoggerFactory.getLogger(MemberServiceUtil.class);
	
	public static AetnaMember populateAetnaMember(MemberEntity member) {
		
		AetnaMember aetnaMember=new AetnaMember();
		aetnaMember.setMemberId(member.getMemberId());
		aetnaMember.setFirstName(member.getFirstName());
		aetnaMember.setLastName(member.getLastName());
		aetnaMember.setDateofbirth(member.getDateofbirth());
		aetnaMember.setMemeberAge(member.getMemeberAge());

		MemberAddress address=new MemberAddress();
		address.setStreetLine1(member.getStreetLine1());
		address.setStreetLine2(member.getStreetLine2());
		address.setCity(member.getCity());
		address.setSate(member.getSate());
		address.setZipcode(member.getZipcode());
		aetnaMember.setMemberAddress(address);

		if(member.getDependents()!=null) {
			List<MemberDependent> dependentList=new ArrayList<MemberDependent>();
			
			member.getDependents().forEach( dependentEnty ->{				
				MemberDependent dependent=new MemberDependent();
				dependent.setDependentId(dependentEnty.getDependentId());
				dependent.setDependentFirstName(dependentEnty.getDependentFirstName());
				dependent.setDependentLastName(dependentEnty.getDependentLastName());
				dependent.setDependentDateofBirth(dependentEnty.getDependentDateofBirth());
				dependent.setRelationship(dependentEnty.getRelationship());
				
				dependentList.add(dependent);
			});
			
			aetnaMember.setMemberDependents(Optional.of(dependentList));
		}
		
		return aetnaMember;
	}
	
	public static MemberEntity populateMemberEntity(AetnaMember member) {
		
		MemberEntity memberEnty=new MemberEntity();
		memberEnty.setMemberId(member.getMemberId());
		memberEnty.setFirstName(member.getFirstName());
		memberEnty.setLastName(member.getLastName());
		memberEnty.setDateofbirth(member.getDateofbirth());
		
		memberEnty.setStreetLine1(member.getMemberAddress().getStreetLine1());
		memberEnty.setStreetLine2(member.getMemberAddress().getStreetLine2());
		memberEnty.setCity(member.getMemberAddress().getCity());
		memberEnty.setSate(member.getMemberAddress().getSate());
		memberEnty.setZipcode(member.getMemberAddress().getZipcode());
		
		member.getMemberDependents().ifPresent(dependentlist -> {
			List<DependentEntity> dependentEntyList=new ArrayList<DependentEntity>();
			dependentlist.forEach(dependent -> {
				logger.info("Dependent="+dependent.toString());
				DependentEntity dependentEnty=new DependentEntity();
				dependentEnty.setDependentId(dependent.getDependentId());
				dependentEnty.setDependentFirstName(dependent.getDependentFirstName());
				dependentEnty.setDependentLastName(dependent.getDependentLastName());
				dependentEnty.setDependentDateofBirth(dependent.getDependentDateofBirth());
				dependentEnty.setRelationship(dependent.getRelationship());
				dependentEnty.setMember(memberEnty);
				dependentEntyList.add(dependentEnty);
			});
			memberEnty.setDependents(dependentEntyList);
		});
		
		return memberEnty;
	}
	
	public static DependentEntity populateDependentEntity(MemberDependent dependent) {
		
		DependentEntity dependentEnty=new DependentEntity();
		dependentEnty.setDependentId(dependent.getDependentId());
		dependentEnty.setDependentFirstName(dependent.getDependentFirstName());
		dependentEnty.setDependentLastName(dependent.getDependentLastName());
		dependentEnty.setDependentDateofBirth(dependent.getDependentDateofBirth());
		dependentEnty.setRelationship(dependent.getRelationship());
		
		return dependentEnty;
		
	}
}
