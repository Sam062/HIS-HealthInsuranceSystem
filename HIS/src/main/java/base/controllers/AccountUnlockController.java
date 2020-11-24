package base.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import base.model.UnlockAccountModel;

public class AccountUnlockController {
	
	@GetMapping("/unlockAccount")
	public String loadUnlockAccount(@RequestParam("email")String email,Model model) {
		UnlockAccountModel umodel=new UnlockAccountModel();
		umodel.setEmail(email);
		model.addAttribute("unlockAccountModel", umodel);
		return "unlockAccount";
	}
	
	@PostMapping("/unlockAdminAccount")
	public String unlockAdminAccount(@ModelAttribute("unlockAdminAccount")UnlockAccountModel umodel,Model model) {
		return "";
	}

}
