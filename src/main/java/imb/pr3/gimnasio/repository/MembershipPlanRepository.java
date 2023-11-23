package imb.pr3.gimnasio.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr3.gimnasio.entity.MembershipPlan;

public interface MembershipPlanRepository extends JpaRepository<MembershipPlan, Integer>{
}
