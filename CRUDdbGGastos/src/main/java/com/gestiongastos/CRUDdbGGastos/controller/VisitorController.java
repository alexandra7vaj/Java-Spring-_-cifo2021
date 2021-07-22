package com.gestiongastos.CRUDdbGGastos.controller;

import java.io.IOException;
//import java.lang.reflect.Member;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.gestiongastos.CRUDdbGGastos.repository.VisitorRepository;
import com.gestiongastos.CRUDdbGGastos.model.Visitor;

@Controller
@RequestMapping("/visitor")
public class VisitorController {

	@Autowired
	VisitorRepository visitorRepository;

	@RequestMapping("/allVisitors")
	public String getAllVisitors(Model boxToView) {

		boxToView.addAttribute("visitorListfromControllerAndDB", visitorRepository.findAll());

		return "visitors";
	}

	//----------------------- DELETE ----------------------------------
	@RequestMapping("/deleteVisitor")
	public String removeVisitor(int id, Model model) {

		// System.out.println("inside removeEmployee" + id);
		Optional<Visitor> visitorFound = findOneVisitorById(id);

		// System.out.println("find inside removeEmployee" + employeeFound.get());

		if (visitorFound.isPresent()) {

			visitorRepository.deleteById(id);
			model.addAttribute("message", "done");
			model.addAttribute("visitorDeleted", visitorFound.get());
		}

		else {
			model.addAttribute("message", "error");
		}

		
		return "deletedvisitor.html"; // envia a html
	}

	@RequestMapping("/deleteAllVisitors")
	public String deleteAllVisitors () {

		
		visitorRepository.deleteAll();
		

		return "redirect:/visitor/allVisitors";

	}
	
	//----------------------- ADD ----------------------------------------
	@RequestMapping("/newVisitorr")
	public String newVisitor() {

		return "newvisitor.html";
	}

	@RequestMapping("/addVisitor")
	public String inserVisitor(com.gestiongastos.CRUDdbGGastos.model.Visitor visitor) {

		visitorRepository.save(visitor);

		return "redirect:/visitor/allVisitors";
	}

	//----------------------- UPDATE ---------------------------------------
	@RequestMapping("/updateVisitor")
	public String updateEmpoyee(int id, Model model) {

		Optional<Visitor> visitorFound = findOneVisitorById(id);

		if (visitorFound.isPresent()) {

			model.addAttribute("visitorfromController", visitorFound.get());
			return "updatevisitor";
		}

		else
			return "notfound.html";
	}

	@PostMapping("/replaceVisitor/{idFromView}")
	public String replaceVisitor(@PathVariable("idFromView") int id, Visitor visitor) {

		Optional<Visitor> visitorFound = findOneVisitorById(id);

		if (visitorFound.isPresent()) {

			if (visitor.getName() != null)
				(visitorFound.get()).setName(visitor.getName());
			
			if (visitor.getSurname() != null)
				(visitorFound.get()).setSurname(visitor.getSurname());
			
			if (visitor.getPassword() != null)
				(visitorFound.get()).setPassword(visitor.getPassword());
			
			if (visitor.getEmail() != null)
				(visitorFound.get()).setEmail(visitor.getEmail());
			
			if (visitor.getAge() != 0)
				(visitorFound.get()).setAge(visitor.getAge());
			
      // No reconoce class Member
			visitorRepository.save(visitorFound.get());
			return "redirect:/visitor/allVisitors";

		} else
			return "notfound.html";

	}
	
	//----------------------- DETAIL ---------------------------------------
	@RequestMapping("/detailVisitor")
	public String detailVisitor(int id, Model model) {

		Optional<Visitor> visitorFound = findOneVisitorById(id);

		if (visitorFound.isPresent()) {

			model.addAttribute("visitorfromController", visitorFound.get());
			return "detailvisitor";
		}

		else
			return "notfound.html";
	}
	

//------------------------- service to controller --------------------------------

	private Optional<Visitor> findOneVisitorById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional findOneVisitoreById(int id) {

		
		Optional visitorFound = visitorRepository.findById(id);
		
		return visitorFound;
	}
}
