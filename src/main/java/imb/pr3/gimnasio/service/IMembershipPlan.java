package imb.pr3.gimnasio.service;

import java.util.List;

import imb.pr3.gimnasio.entity.MembershipPlan;

public interface IMembershipPlan {

	List<MembershipPlan> getAll();
	MembershipPlan getById(Integer id);
	MembershipPlan save(MembershipPlan mPlan);
	MembershipPlan delete(Integer id);
	boolean exists(Integer id);
}
