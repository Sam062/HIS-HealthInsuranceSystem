package base.service;

import java.util.List;

import base.entity.PlanEntity;
import base.model.PlanModel;

public interface IPlanService {
	
	PlanEntity savePlan(PlanModel plan);
	List<PlanModel> viewPlans();
	
	PlanModel findByPlanId(Integer planId);
	Boolean updatePlan(PlanModel planModel);
	Boolean changePlanStatus(Integer planId, String planStatus);
	
	

}
