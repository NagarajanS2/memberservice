package com.aetna.member.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aetna.member.jpa.entity.DependentEntity;

@Repository
public interface DependentRepository extends CrudRepository<DependentEntity,String>{

}
