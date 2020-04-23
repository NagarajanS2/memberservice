package com.aetna.member.jpa.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class MemberEntity {

	@Id
	private String memberId;
	private String firstName;
	private String lastName;
	private Date dateofbirth;
	private int memeberAge;
	
	private String streetLine1;
	private String streetLine2;
	private String city;
	private String sate;
	private String zipcode;
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<DependentEntity> dependents;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this); 
	}
}
