package base.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import base.model.AccountModel;
import base.model.UnlockAccountModel;
import base.service.AdminAccountService;

@Controller
public class AccountUnlockController {
	@Autowired
	private AdminAccountService service;

	@GetMapping("/unlockAccount")
	public String loadUnlockAccount(@RequestParam("email")String email,Model model) {
		UnlockAccountModel umodel=new UnlockAccountModel();
		umodel.setEmail(email);
		model.addAttribute("unlockAccountModel", umodel);
		return "unlockAccount";
	}

	@PostMapping("/unlockAdminAccount")
	public String unlockAdminAccount(@ModelAttribute("unlockAccountModel")UnlockAccountModel umodel,Model model) {
		AccountModel accModel = service.findByEmailAndPwd(umodel.getEmail(), umodel.getTempPwd());
		if(accModel!=null) {
			Boolean updateAccount = service.updateAccount(umodel);
			if(updateAccount) {
				model.addAttribute("msg", "Account Activated Successfully.");
				AccountModel aModel=new AccountModel();
				model.addAttribute("accountModel", aModel);
			}
			return "loginPage";	
		}
		else{
			model.addAttribute("msg","Incorrect Email or Temporary Password!");
			return "unlockAccount";
		}
	}
	@GetMapping("/loginPage")
	public String loadLoginPage(Model model) {
		AccountModel accModel=new AccountModel();
		model.addAttribute("accountModel", accModel);
		return "loginPage";
	}

}
