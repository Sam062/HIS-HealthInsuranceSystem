package base.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import base.constents.AdminAccountConstents;
import base.model.AccountModel;
import base.service.AdminAccountService;
import base.utils.TwilloMsg;

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
		AccountModel findByEmail = service.findByEmail(email);
		if(findByEmail!=null) 
			return "duplicate";
		else
			return "unique";
	}

	@PostMapping("/validateAdminLogin")
	public String validateAdminLogin(@ModelAttribute("accountModel") AccountModel accModel,Model model) {
		AccountModel findByEmailAndPwd = service.findByEmailAndPwd(accModel.getEmail(), accModel.getPwd());
		if(findByEmailAndPwd!=null) {
			if(findByEmailAndPwd.getAccountStatus().equals(AdminAccountConstents.INACTIVE.toString())) {
				model.addAttribute("msg", "Please activate your account first.");
				return "loginPage";
			}
			return "adminDashboard";
		}else {
			model.addAttribute("msg", "Invalid Credentials!");	
			return "loginPage";
		}
	}
	@GetMapping("/forgotPwd")
	public String loadForgotPasswordPage(Model model) {
		AccountModel accModel=new AccountModel();
		model.addAttribute("accModel", accModel);
		return "forgotPwdPage";
	}
	@PostMapping("/pwdRecovery")
	public String pwdRecovery(@ModelAttribute("accModel")AccountModel accModel,Model model) {
		AccountModel findByEmail = service.findByEmail(accModel.getEmail());
		if(findByEmail!=null)
			if(findByEmail.getAccountStatus().equals(AdminAccountConstents.INACTIVE.toString()))
				model.addAttribute("msg", "Please Activate your account first.");
			else {
				try {
					TwilloMsg.sendMsg(findByEmail.getMobileNo(), "Your HIS admin Password is : "+findByEmail.getPwd());
					StringBuilder number=new StringBuilder("*********");
					number.append(findByEmail.getMobileNo().substring(9, 13).toString());
					model.addAttribute("msg", "Password sent to "+number);
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("msg", "Couldn't send messege.");
				}
			}
		else
			model.addAttribute("msg", "Email doesn't exist");
		return "forgotPwdPage";
	}
}
