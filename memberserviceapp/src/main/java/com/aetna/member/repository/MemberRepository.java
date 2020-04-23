package com.aetna.member.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aetna.member.jpa.entity.MemberEntity;

@Repository
public interface MemberRepository extends CrudRepository<MemberEntity,String>{

}
