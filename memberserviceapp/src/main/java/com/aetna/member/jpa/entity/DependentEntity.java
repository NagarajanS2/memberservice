package com.aetna.member.jpa.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class DependentEntity {
	@Id
	private String dependentId;
	private String dependentFirstName;
	private String dependentLastName;
	private Date dependentDateofBirth;
	private String relationship;
	
	@ManyToOne
    @JoinColumn(name = "MEMBER_ID", nullable = false)
	private MemberEntity member;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this); 
	}
}
