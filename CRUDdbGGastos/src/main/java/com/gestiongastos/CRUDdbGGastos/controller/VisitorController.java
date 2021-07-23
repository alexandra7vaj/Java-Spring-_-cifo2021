package com.gestiongastos.CRUDdbGGastos.controller;

import java.io.IOException;
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

//------------------ CREATE ----------------------------------

@RequestMapping("/newVisitor")
public String newVisitor() {

	return "door";
}

@RequestMapping("/addVisitor")
public String insertVisitor(Visitor visitor) {
	
	visitorRepository.save(visitor);
	
	return "myspace";
}

//--------------------------- READ ------------------------------------

@RequestMapping("/getVisitor/{id}")
public Visitor findById(@PathVariable int id) {

	Optional<Visitor> guestFound = visitorRepository.findById(id);

	Optional<Visitor> visitorFound = null;
	if (visitorFound.isPresent()) {

		return visitorFound.get();
     }
	
	return null;
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

//  Methodo findOneVisitorById(int id){}

private Optional<Visitor> findOneVisitorById(int id) {
	
	return null;
}

@PostMapping("/replaceVisitor/{idFromView}")
public String replaceVisitor(@PathVariable("idFromView") int id, Visitor visitor) {

	Optional<Visitor> visitorFound = visitorRepository.findById(id);

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
		

		visitorRepository.save(visitorFound.get());
		return "redirect:/visitor/allVisitors";

	} else
		return "notfound.html";

}

// ------------------- DELETE ---------------------------------------

@RequestMapping("/deleteVisitor/{id}")
	public  String deleteVisitor(int id, Model model) {

		Optional<Visitor> visitorFound = visitorRepository.findById(id);

		if (visitorFound.isPresent()) {

			visitorRepository.deleteById(id);
		}
		
		return "deletedvisitor.html"; // envia a html

	}

@RequestMapping("/deleteAllVisitors")

public String deleteAllVisitors() {
	visitorRepository.deleteAll();
	
	return "redirect:/visitor/allVisitors";

}

//----------------------- ADD ----------------------------------------
//@RequestMapping("/newVisitorr")
//public String newVisitor1() {
//
//	return "newvisitor.html";
//  }
//
//@RequestMapping("/addVisitor")
//public String inserVisitor () {
//
//     visitorRepository.save(visitor1);
//
//	return "redirect:/visitor/allVisitors";
// }

}

