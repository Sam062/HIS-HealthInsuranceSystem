package base.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import base.model.PlanModel;

@Controller
public class PlanController {
	
	@GetMapping("/loadPlanCreation")
	public String loadPlanCreation(Model model) {
		PlanModel planModel = new PlanModel();
		model.addAttribute("planModel", planModel);
		return "createPlan";
	}
}
