package com.aetna.member.domain;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AetnaMember {
	
	private String memberId;
	private String firstName;
	private String lastName;
	private Date dateofbirth;
	private int memeberAge;
	private MemberAddress memberAddress;
	private Optional<List<MemberDependent>> memberDependents=Optional.empty();
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this); 
	}
}
