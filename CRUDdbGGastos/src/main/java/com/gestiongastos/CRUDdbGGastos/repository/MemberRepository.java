package com.gestiongastos.CRUDdbGGastos.repository;

import org.springframework.data.repository.CrudRepository;

import com.gestiongastos.CRUDdbGGastos.model.Member;



public interface MemberRepository extends CrudRepository<Member,Integer> {
	
}
