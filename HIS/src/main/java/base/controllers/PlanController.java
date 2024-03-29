package base.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import base.constents.HisConstants;
import base.entity.PlanEntity;
import base.model.PlanModel;
import base.service.IPlanService;

@Controller
public class PlanController {

	@Autowired
	private IPlanService planService;

	@GetMapping("/loadPlanCreation")
	public String loadPlanCreation(Model model, HttpSession session) {
		System.out.println("SESSION AND EMAIL->");
		System.out.println(session);
		String loginEmail = (String) session.getAttribute(HisConstants.LOGIN_EMAIL.toString());
		System.out.println("loginEmail->"+loginEmail);
		if (loginEmail != null) {
			PlanModel planModel = new PlanModel();
			model.addAttribute("planModel", planModel);
			return "createPlan";
		} else {
			throw new RuntimeException("Please login first.");
		}
	}

	@PostMapping("/createPlan")
	public String createPlan(@ModelAttribute("planModel") PlanModel planModel, RedirectAttributes model) {
		PlanEntity savePlan = planService.savePlan(planModel);
		if (savePlan != null) {
			model.addFlashAttribute("msg", "PLAN ADDED TO DB.");
		} else {
			model.addFlashAttribute("msg", "PLAN COULDN'T ADD TO DB.");
		}
		return "redirect:/loadPlanCreation";
	}

	@GetMapping("/viewPlans")
	public String viewPlans(Model model) {
		List<PlanModel> plansList = planService.viewPlans();
		System.out.println("******************* PLANS -->" + plansList);
		if (plansList != null && !plansList.isEmpty()) {
			model.addAttribute("list", plansList);
		} else {
			model.addAttribute("msg", "No Plans Found.");
		}
		return "viewPlan";
	}

	@GetMapping("/loadplanedit")
	public String loadPlanEdit(@RequestParam("id") Integer planId, Model model) {
		PlanModel planModel = planService.findByPlanId(planId);
		if (planModel != null)
			model.addAttribute("planModel", planModel);
		return "createPlan";

	}

	@GetMapping("/updatePlan")
	public String editPLan(@ModelAttribute("planModel") PlanModel planModel, Model model) {
		Boolean status = planService.updatePlan(planModel);
		if (status) {
			List<PlanModel> planList = planService.viewPlans();
			if (planList != null && !planList.isEmpty()) {
				model.addAttribute("msg", "Plans detail updated successfully.");
				model.addAttribute("list", planList);
			} else
				model.addAttribute("msg", "No Records found");

		} else
			model.addAttribute("msg", "Plan update failed.");

		return "viewPlan";
	}

	@GetMapping("/changeplanstatus")
	public String changePlanStatus(@RequestParam("id") Integer planId, @RequestParam("status") String planStatus,
			Model model) {
		Boolean status = planService.changePlanStatus(planId, planStatus);

		if (status) {
			List<PlanModel> plans = planService.viewPlans();
			if (plans != null && !plans.isEmpty())
				model.addAttribute("list", plans);
			else
				model.addAttribute("msg", "No Records found.");
		} else
			model.addAttribute("msg", "Plan delete failed");

		return "viewPlan";
	}
}
