package com.gestiongastos.CRUDdbGGastos.controller;

import java.io.IOException;
import java.lang.reflect.Member;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gestiongastos.CRUDdbGGastos.repository.MemberRepository;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberRepository memberRepository;

	@RequestMapping("/allMembers")
	public String getAllMembers(Model boxToView) {

		boxToView.addAttribute("memberListfromControllerAndDB", memberRepository.findAll());

		return "employees";
	}

	//----------------------- DELETE ----------------------------------
	@RequestMapping("/deleteMember")
	public String removeMember(int id, Model model) {

		// System.out.println("inside removeEmployee" + id);
		Optional<Member> memberFound = findOneMemberById(id);

		// System.out.println("find inside removeEmployee" + employeeFound.get());

		if (memberFound.isPresent()) {

			memberRepository.deleteById(id);
			model.addAttribute("message", "done");
			model.addAttribute("memberDeleted", memberFound.get());
		}

		else {
			model.addAttribute("message", "error");
		}

		
		return "deletedmember.html"; // envia a html
	}

	@RequestMapping("/deleteAllMembers")
	public String deleteAllMembers () {

		
		memberRepository.deleteAll();
		

		return "redirect:/member/allMembers";

	}
	
	//----------------------- ADD ----------------------------------------
	@RequestMapping("/newMember")
	public String newMember() {

		return "newmember.html";
	}

	@RequestMapping("/addMember")
	public String inserMember(Member member) {

		memberRepository.save(member);

		return "redirect:/member/allMembers";
	}

	//----------------------- UPDATE ---------------------------------------
	@RequestMapping("/updateMember")
	public String updateEmpoyee(int id, Model model) {

		Optional<Member> memberFound = findOneMemberById(id);

		if (memberFound.isPresent()) {

			model.addAttribute("memberfromController", memberFound.get());
			return "updatemember";
		}

		else
			return "notfound.html";
	}

	@PostMapping("/replaceMember/{idFromView}")
	public String replaceMember(@PathVariable("idFromView") int id, Member member) {

		Optional<Member> memberFound = findOneMemberById(id);

		if (memberFound.isPresent()) {

			if (member.getName() != null)
				((com.gestiongastos.CRUDdbGGastos.model.Member) memberFound.get()).setName(member.getName());
			
			if (((com.gestiongastos.CRUDdbGGastos.model.Member) member).getSurname() != null)
				((com.gestiongastos.CRUDdbGGastos.model.Member) memberFound.get()).setSurname(((com.gestiongastos.CRUDdbGGastos.model.Member) member).getSurname());
			
			if (((com.gestiongastos.CRUDdbGGastos.model.Member) member).getPassword() != null)
				((com.gestiongastos.CRUDdbGGastos.model.Member) memberFound.get()).setPassword(((com.gestiongastos.CRUDdbGGastos.model.Member) member).getPassword());
			
			if (((com.gestiongastos.CRUDdbGGastos.model.Member) member).getEmail() != null)
				((com.gestiongastos.CRUDdbGGastos.model.Member) memberFound.get()).setEmail(((com.gestiongastos.CRUDdbGGastos.model.Member) member).getEmail());
			
			if (((com.gestiongastos.CRUDdbGGastos.model.Member) member).getAge() != 0)
				((com.gestiongastos.CRUDdbGGastos.model.Member) memberFound.get()).setAge(((com.gestiongastos.CRUDdbGGastos.model.Member) member).getAge());
			

			memberRepository.save(memberFound.get());
			return "redirect:/member/allMembers";

		} else
			return "notfound.html";

	}
	
	//----------------------- DETAIL ---------------------------------------
	@RequestMapping("/detailMembere")
	public String detailMember(int id, Model model) {

		Optional<Member> memberFound = findOneMemberById(id);

		if (memberFound.isPresent()) {

			model.addAttribute("memberfromController", memberFound.get());
			return "detailmember";
		}

		else
			return "notfound.html";
	}
	

//------------------------- service to controller --------------------------------


	private Optional<Member> findOneMemberById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<com.gestiongastos.CRUDdbGGastos.model.Member> findOneMembereById(int id) {

		
		Optional<com.gestiongastos.CRUDdbGGastos.model.Member> memberFound = memberRepository.findById(id);
		
		return memberFound;
	}
}
