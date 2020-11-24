package base.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import base.model.AccountModel;
import base.service.AdminAccountService;

@Controller
public class AdminController {
	@Autowired
	private AdminAccountService service;

	@GetMapping(value = {"/","/loadCreateAccount"})
	public String loadCreateAccountPage(Model model) {
		AccountModel adminModel=new AccountModel();
		model.addAttribute("adminModel", adminModel);
		return "createAccount";
	}

	@PostMapping("/AdminCreateAccount")
	public String adminCreateAccount(@ModelAttribute("adminModel")AccountModel amodel,Model model) {
		Boolean saveStatus = service.saveAdminDetails(amodel);
		if(saveStatus)
			model.addAttribute("msg", "Hello "+amodel.getFname()+" , <br/>Thank you for registering here, Registration almost done. Please check your email.");
		else
			model.addAttribute("msg", "Something Went Wrong! ;(");
		return "data";
	}

	@GetMapping("/emailVaidation")
	public @ResponseBody String emailValidation(@RequestParam("email") String email) {
		return service.findByEmail(email);
	}

	//	@PostMapping("/data")
	//	public String showData(@ModelAttribute("accModel")AccountModel accModel, Model model) {
	//		String isUserSaved=service.saveAdminDetails(accModel);
	//
	//		if(isUserSaved){
	//			model.addAttribute("msg", "Hello "+accModel.getFname()+" , <br/>Thank you for registering here, Registration almost done. Please check your email.");
	//		}
	//		else
	//			model.addAttribute("msg", "SOMETHING WENT WRONG :(");
	//		return "data";
	//	}

}
