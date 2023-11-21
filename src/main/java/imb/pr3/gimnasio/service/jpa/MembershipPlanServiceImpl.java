package imb.pr3.gimnasio.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.pr3.gimnasio.entity.MembershipPlan;
import imb.pr3.gimnasio.repository.MembershipPlanRepository;
import imb.pr3.gimnasio.service.IMembershipPlan;

@Service
public class MembershipPlanServiceImpl implements IMembershipPlan{
	
	@Autowired
	MembershipPlanRepository repo;

	@Override
	public List<MembershipPlan> getAll() {
		return repo.findAll();
	}

	@Override
	public List<MembershipPlan> findByEnabled(boolean enabled) {
		return repo.findByEnabled(enabled);
	}
	
	
	@Override
	public MembershipPlan getById(Integer id) {
		Optional<MembershipPlan> optional = repo.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public MembershipPlan save(MembershipPlan mPlan) {
		repo.save(mPlan);
		return mPlan;
	}

	@Override
	public MembershipPlan delete(Integer id) {
		repo.deleteById(id);
		return null;
	}

	@Override
	public boolean exists(Integer id) {
		return (id == null) ? false : repo.existsById(id);
	}

	

	
}
