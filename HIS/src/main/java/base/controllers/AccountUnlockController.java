package base.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import base.constents.HisConstants;
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
			Boolean updateAccount = service.updateAccountStatus(umodel);
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
	@GetMapping(value = {"/","/loginPage"})
	public String loadLoginPage(Model model) {
		AccountModel accModel=new AccountModel();
		model.addAttribute("accountModel", accModel);
		return "loginPage";
	}
	@GetMapping(value = "/logout")
	public String logout(Model model, HttpSession session, SessionStatus status) {
		System.out.println("LOGGING OUT====>>>>"
				+(String) session.getAttribute(HisConstants.LOGIN_EMAIL.toString()));
		status.setComplete();
		session.invalidate();

		AccountModel accModel=new AccountModel();
		model.addAttribute("accountModel", accModel);
		model.addAttribute("msg", "LOGGED OUT SUCCESSFULLY.");

		return "loginPage";
	}

}
