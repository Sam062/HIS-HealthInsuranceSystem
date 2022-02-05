package base.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import base.entity.PlanEntity;

public interface IPlanRepo extends JpaRepository<PlanEntity, Integer> {

}
