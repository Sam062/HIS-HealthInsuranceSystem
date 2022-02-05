package base.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.entity.PlanEntity;
import base.model.PlanModel;
import base.repository.IPlanRepo;
import base.service.IPlanService;

@Service
public class PlanServiceImpl implements IPlanService{
	private static final String ACTIVE = "ACTIVE";
	private static final String INACTIVE = "INACTIVE";

	@Autowired
	private IPlanRepo planDao;

	@Override
	public PlanEntity savePlan(PlanModel planModel) {
		PlanEntity planEntity = new PlanEntity();
		BeanUtils.copyProperties(planModel, planEntity);
		planEntity.setDelStatus(ACTIVE);
		return planDao.save(planEntity);
	}

	@Override
	public List<PlanModel> viewPlans() {
		List<PlanEntity> planEntityList = planDao.findAll();

		List<PlanModel> planModelList = new ArrayList<>();
		if(planEntityList!=null && !planEntityList.isEmpty()) {
			planModelList = planEntityList.stream().map(planEntity->{
				PlanModel planModel = new PlanModel();
				BeanUtils.copyProperties(planEntity, planModel);
				return planModel;
			}).collect(Collectors.toList());
			return planModelList;
		}else
			return null;
	}

	@Override
	public PlanModel findByPlanId(Integer planId) {
		Optional<PlanEntity> findById = planDao.findById(planId);
		PlanEntity planEntity=null;
		if(findById.isPresent()) 
			planEntity = findById.get();

		PlanModel planModel = new PlanModel();
		BeanUtils.copyProperties(planEntity, planModel);
		return planModel;
	}

	@Override
	public Boolean updatePlan(PlanModel planModel) {
		PlanEntity planEntity = new PlanEntity();
		BeanUtils.copyProperties(planModel, planEntity);
		PlanEntity save = planDao.save(planEntity);
		if(save!=null)
			return true;
		else
			return false;
	}

	@Override
	public Boolean changePlanStatus(Integer planId, String planStatus) {
		Optional<PlanEntity> findById = planDao.findById(planId);
		PlanEntity planEntity=null;
		if(findById.isPresent()) {
			planEntity=findById.get();
			planEntity.setDelStatus((planStatus.equalsIgnoreCase(ACTIVE)?ACTIVE:INACTIVE));
			PlanEntity save = planDao.save(planEntity);
			if(save!=null)
				return true;
			else
				return false;
		}
		return false;
	}
}
