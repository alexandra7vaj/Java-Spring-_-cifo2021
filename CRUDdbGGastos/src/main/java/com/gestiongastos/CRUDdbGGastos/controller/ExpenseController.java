package com.gestiongastos.CRUDdbGGastos.controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gestiongastos.CRUDdbGGastos.model.Expense;
import com.gestiongastos.CRUDdbGGastos.repository.ExpenseRepository;


     @Controller
     @RequestMapping("/expense")
     public class ExpenseController{
 	
    @Autowired
 	ExpenseRepository expenseRepository;
 //-------------CREATE-----------------------------------------------------------------------
    
    @RequestMapping("/newExpense")
		public String newExpense () {

			return "expense/newexpense.html";
		}

		@RequestMapping("/addExpense")
		public String inserExpense ( @Validated Expense expense, Model boxToView) {
			
			//System.out.println(expense);
			expenseRepository.save(expense);
			boxToView.addAttribute("expensesfromController", expenseRepository.findAll());
			boxToView.addAttribute("expenseAdded", expense);

			return "expense/expenses.html";
		}
		

 //----------------------- READ--------------------------------------------------------------
 		@RequestMapping("/allExpenses")
 		public String getAllEmployees(Model boxToView) {
 			
 			boxToView.addAttribute("expensesfromController", expenseRepository.findAll() );
 			
 			return "expense/expenses.html";
 		}
 		
 //-----------------------UPDATE---------------------------------------------------------------
 				@RequestMapping("/updateExpense")
 				public String updateExpense(int id, Model model) {

 					Optional<Expense> expenseFound = findOneExpenseById(id);

 					if (expenseFound.isPresent()) {

 						model.addAttribute("expensefromController", expenseFound.get());
 						return "expense/updateexpense.html";
 					}

 					else
 						return "home/notfound.html";
 				}

 				private Optional<Expense> findOneExpenseById(int id) {
					
					return null;
				}

				@PostMapping("/replaceExpense/{idFromView}")
 				public String replaceVisitor(@PathVariable("idFromView") int id, Expense expense, Model model) {

 					Optional<Expense> expenseFound = findOneExpenseById(id);

 					if (expenseFound.isPresent()) {

 						if (expense.getName() != null)
 							expenseFound.get().setName(expense.getName());
 						
 						
 						if (expense.getValue() != 0.0)
 							expenseFound.get().setValue(expense.getValue());

 						expenseRepository.save(expenseFound.get());
 						model.addAttribute("expensesfromController", expenseRepository.findAll());
 						model.addAttribute("expenseUpdated", expenseFound.get());
 						
 						return "expense/expenses.html";

 					} else
 						return "home/notfound.html";

 				}
 				
//-----------------------DELETE-----------------------------------------------------------------------------
 				
				@RequestMapping("/deleteExpense")
 				public String removeExpense(int id, Model boxToView) {

 					// System.out.println("inside removeEmployee" + id);
 					Optional<Expense> expenseFound = findOneExpenseById(id);

 					// System.out.println("find inside removeEmployee" + employeeFound.get());

 					if (expenseFound.isPresent()) {

 						expenseRepository.deleteById(id);
 						
 						boxToView.addAttribute("expensesfromController", expenseRepository.findAll());
 						boxToView.addAttribute("expenseDeleted", expenseFound.get());
 						
 					}

 					else {
 						boxToView.addAttribute("expenseDeleted", "error");
 					}

 					// System.out.println("finishing removeEmployee" + id);
 					return "expense/expenses.html";
 				}
 		 		
 	
//------------------------- service to controller --------------------------------------------------------
 		 		
 		 			public Optional<Expense> findOneExpenseByld(int id) {

 		 				   Optional<Expense> expenseFound = expenseRepository.findById(id);
 		 				
		 				   return expenseFound;
 		 			}
}
