package base.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import base.constents.AdminAccountConstents;
import base.model.ApplicantModel;
import base.service.impl.ApplicantRegisterService;

@Controller
public class ApplicantRegistrationController {// AR Module

	@Autowired
	private ApplicantRegisterService appRegService;

	@GetMapping("/loadApplicantReg")
	public String loadRegPage(Model model) {
		ApplicantModel am = new ApplicantModel();
		model.addAttribute("applicantModel", am);
		return "ApplcantRegForm";
	}


	@PostMapping("/registerApplicant")
	public String registerApplicant(@ModelAttribute ApplicantModel applicantModel,RedirectAttributes model) {
		
		if(applicantModel!=null && applicantModel.getSsnNumber()!=null && !applicantModel.getSsnNumber().isEmpty()) {
			String stateName=appRegService.getStateNameBySSN(applicantModel.getSsnNumber());
			
			if(stateName.equalsIgnoreCase(AdminAccountConstents.NJ.toString())) {
				Integer isApplicantSaved = appRegService.saveApplicantDetails(applicantModel);
				if(isApplicantSaved!=null && isApplicantSaved!=0) 
					model.addFlashAttribute("msg", "Applicant Registration success. Application ID-"+isApplicantSaved);
				else
					model.addFlashAttribute("msg", "Applicant Registration failed.");
			}else {
				model.addFlashAttribute("msg", "You can't apply for a plan in HIS. It's only available for NJ people.");
			}
		}
		return "redirect:/loadApplicantReg";
	}



}
