package base.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import base.constents.HisConstants;
import base.model.AccountModel;
import base.service.AdminAccountService;
import base.utils.TwilloMsg;

@Controller
public class AdminController { // Admin Module
	@Autowired
	private AdminAccountService service;

	@GetMapping("/loadCreateAccount")
	public String loadCreateAccountPage(Model model) {
		AccountModel adminModel = new AccountModel();
		model.addAttribute("adminModel", adminModel);
		return "createAccount";
	}

	@PostMapping("/AdminCreateAccount")
	public String adminCreateAccount(@ModelAttribute("adminModel") AccountModel amodel, Model model) {
		Boolean saveStatus = service.saveAdminDetails(amodel);
		if (saveStatus)
			model.addAttribute("msg", "Hello " + amodel.getFname()
					+ " , <br/>Thank you for registering here, Registration almost done. Please check your email.");
		else
			model.addAttribute("msg", "Something Went Wrong! ;(");
		return "adminRegSuccess";
	}

	@GetMapping("/emailVaidation")
	public @ResponseBody String emailValidation(@RequestParam("email") String email) {
		AccountModel findByEmail = service.findByEmail(email);
		if (findByEmail != null)
			return "duplicate";
		else
			return "unique";
	}

	@PostMapping("/validateAdminLogin")
	public String validateAdminLogin(@ModelAttribute("accountModel") AccountModel accModel, Model model,
			HttpServletRequest req) {
		AccountModel accModel1 = service.findByEmailAndPwd(accModel.getEmail(), accModel.getPwd());
		if (accModel1 != null) {
			if (accModel1.getAccountStatus().equalsIgnoreCase(HisConstants.INACTIVE.toString())) {
				model.addAttribute("msg", "Please Unlock your account first.");
				return "loginPage";
			} else {
				if (accModel1.getDeleteStatus().equalsIgnoreCase(HisConstants.ACTIVE.toString())) {
					HttpSession session = req.getSession(true);
					session.setMaxInactiveInterval(20);
					session.setAttribute(HisConstants.LOGIN_EMAIL.toString(), accModel.getEmail());
					
					if (accModel1.getRole() != null && accModel1.getRole().equalsIgnoreCase("ADMIN"))
						return "adminDashboard";
					else
						return "caseWorkerDashboard";
				} else {
					model.addAttribute("msg", "Please activate your account first.");
					return "loginPage";
				}
			}
		} else {
			model.addAttribute("msg", "Invalid Credentials!");
			return "loginPage";
		}
	}

	@GetMapping("/forgotPwd")
	public String loadForgotPasswordPage(Model model) {
		AccountModel accModel = new AccountModel();
		model.addAttribute("accModel", accModel);
		return "forgotPwdPage";
	}

	@PostMapping("/pwdRecovery")
	public String pwdRecovery(@ModelAttribute("accModel") AccountModel accModel, Model model) {
		AccountModel findByEmail = service.findByEmail(accModel.getEmail());
		if (findByEmail != null)
			if (findByEmail.getAccountStatus().equals(HisConstants.INACTIVE.toString()))
				model.addAttribute("msg", "Please Activate your account first.");
			else {
				try {
					TwilloMsg.sendMsg(findByEmail.getMobileNo(),
							"Your HIS admin Password is : " + findByEmail.getPwd());
					StringBuilder number = new StringBuilder("*********");
					number.append(findByEmail.getMobileNo().substring(9, 13).toString());
					model.addAttribute("msg", "Password sent to " + number);
					AccountModel aModel = new AccountModel();
					model.addAttribute("accountModel", aModel);
					return "loginPage";
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("msg", "Couldn't send messege.");
				}
			}
		else
			model.addAttribute("msg", "Email doesn't exist");
		return "forgotPwdPage";
	}

	@GetMapping("/getAllAccounts")
	public String getAllAccounts(@RequestParam(value = "role", defaultValue = "cw") String role, Model model) {
		if (role.equals("cw"))
			role = "case worker";
		List<AccountModel> findAllAccounts = service.findAllAccounts(role.toUpperCase());
		if (findAllAccounts != null) {
			if (findAllAccounts.isEmpty())
				model.addAttribute("msg", "No Accounts Found !");
			else
				model.addAttribute("list", findAllAccounts);
		} else
			model.addAttribute("msg", "No Accounts Found !");
		return "viewAll";
	}

	@GetMapping("/getall")
	public @ResponseBody List<AccountModel> getall(@RequestParam("role") String role, Model model) {
		if (role.equals("cw"))
			role = "case worker";
		List<AccountModel> findAllAccounts = service.findAllAccounts(role.toUpperCase());
		if (findAllAccounts != null) {
			if (findAllAccounts.isEmpty())
				model.addAttribute("msg", "No Accounts Found !");
			else
				model.addAttribute("list", findAllAccounts);
		} else
			model.addAttribute("msg", "No Accounts Found !");
		return findAllAccounts;
	}

	@GetMapping("/loadeditpage")
	public String showEditPage(@RequestParam("id") Integer id, Model model) {
		AccountModel accModel = service.findByadminId(id);
		model.addAttribute("adminModel", accModel);
		return "editPage";
	}

	@PostMapping("/adminaccountedit")
	public String editAdminAccount(@ModelAttribute("adminModel") AccountModel accModel, RedirectAttributes model) {
		Boolean update = service.update(accModel);
		if (update)
			model.addFlashAttribute("msg", "Account updated successfully.");
		else
			model.addFlashAttribute("msg", "Account couldn't update.");
		return "redirect:/getAllAccounts";
	}

	@GetMapping("/updateaccountstatus")
	public String activeData(@RequestParam("id") Integer id, @RequestParam("status") String status,
			RedirectAttributes model) {
		AccountModel findByadminId = service.findByadminId(id);
		boolean result = false;
		if (findByadminId != null) {
			if (status.equalsIgnoreCase("ACTIVE")) {
				findByadminId.setDeleteStatus(HisConstants.ACTIVE.toString());
				result = service.update(findByadminId);
			} else {
				findByadminId.setDeleteStatus(HisConstants.INACTIVE.toString());
				result = service.update(findByadminId);
			}
		}
		if (result) {
			model.addFlashAttribute("msg",
					findByadminId.getFname() + " " + findByadminId.getLname() + " " + findByadminId.getDeleteStatus());
			return "redirect:/getAllAccounts";
		}
		return "viewAllAccounts";
	}
}
