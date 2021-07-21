package com.gestiongastos.CRUDdbGGastos.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.gestiongastos.CRUDdbGGastos.model.Expense;
import com.gestiongastos.CRUDdbGGastos.model.Member;
import com.gestiongastos.CRUDdbGGastos.repository.ExpenseRepository;
import com.gestiongastos.CRUDdbGGastos.repository.MemberRepository;
//import com.github.javafaker.Faker;
import com.github.javafaker.Faker;

@Controller
public class HomeController {
	
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private ExpenseRepository expenseRepository;

// ------------------------HOME---------------------------
	@RequestMapping({ "/home", "/" })
	public String home() {
		return "home/home";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

// -------------------- fill in our DB ---------------------------------------
		@RequestMapping({ "/fillin" })
		public String finInDB() {

			return "member/fillinmember"; //возврат на заполненние БД в html 
		}

		@RequestMapping({ "/fillinmember" })
		public String fillInDBMember(int qtyToCreate) {

			String alphabetChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!·$%&/()=?¿?=)()/*-+^*Ç¨_:;;:_+/+/";

			
			char stringRandom1, stringRandom2;

			Faker faker = new Faker(); // тестовый генератор фальшивый пользователь для БД
			
			int max = 1525;
			int count = 0;
			int intRandom;
			int intRandom2;
			while (count < qtyToCreate) {

				stringRandom1 = alphabetChars.charAt(createIntRandom(alphabetChars.length()));
				stringRandom2 = alphabetChars.charAt(createIntRandom(alphabetChars.length()));
				stringRandom3 = alphabetChars.charAt(createIntRandom(alphabetChars.length()));
				intRandom = createIntRandom(max);
				intRandom2 = createIntRandom(max * 10);
				
				//Estoy creando nuevo objeto member, xq no lo reconoce ya creado y no hace import
				com.gestiongastos.CRUDdbGGastos.model.Member member = new com.gestiongastos.CRUDdbGGastos.model.Member();
				
				member.setAge(intRandom2);
				member.setEmail(alphabetChars);
				member.setName(alphabetChars);
				member.setSurname(alphabetChars);
				member.setPassword(alphabetChars);
				member.addExpense(null);

				memberRepository.save(new Member(faker.name().firstName(), faker.name().lastName(),
						faker.number().numberBetween(16, 65), faker.name().firstName() + "@java.com",
						faker.number().randomDouble(2, 5, 6000),
						String.valueOf((intRandom + 5) * (count + 1) * 6) + stringRandom1 + stringRandom2 + stringRandom3));

				expenseRepository.save(new Expense(faker.beer().name(), faker.date().birthday(0, 3),
						faker.number().randomDouble(2, 50, 2000)));

				count++;
			}

			return "redirect:/member/allMembers";
		}


//// -------------------------- error path web----------------------------
		
		@RequestMapping({ "*", "*", "*/*" })
		public String notFound(Model model) {

			String pattern = "yyyy-MM-dd HH:mm:ssZ";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			model.addAttribute("serverTime", simpleDateFormat.format(new Date()));
			model.addAttribute("smoker", true);

			return "home/notFound";
		}

		

//// ------------------ SERVICE ------------------------------------------------

		public static int createIntRandom(int top) {

			Random rand = new Random();

			// Generate random integers in range 0 to top, if top=10 random 0 to 9
			int intRandom = rand.nextInt(top);
			// System.out.println(intRandom);
			return intRandom;

		}

}
