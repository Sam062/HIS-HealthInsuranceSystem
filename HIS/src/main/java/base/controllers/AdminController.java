package base.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import base.model.AccountModel;
import base.service.AdminAccountService;

@Controller
public class AdminController {
	@Autowired
	private AdminAccountService service;

	@GetMapping("/loadCreateAccount")
	public String loadCreateAccountPage(Model model) {
		AccountModel adminModel=new AccountModel();
		model.addAttribute("adminModel", adminModel);
		return "createAccount";
	}

	@PostMapping("/AdminCreateAccount")
	public String adminCreateAccount(@ModelAttribute("adminModel")AccountModel amodel,Model model) {
		String saveStatus = service.saveAdminDetails(amodel);
		model.addAttribute("msg", saveStatus);
		return "createAccount";
	}

}
