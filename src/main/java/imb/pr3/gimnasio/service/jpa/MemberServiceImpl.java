package imb.pr3.gimnasio.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.pr3.gimnasio.entity.Member;
import imb.pr3.gimnasio.repository.MemberRepository;
import imb.pr3.gimnasio.service.IMemberService;

@Service
public class MemberServiceImpl implements IMemberService {
	
	@Autowired
	MemberRepository repo;
	
	@Override
	public List<Member> getAll(){
		
		return repo.findAll();
	}

	@Override
	public Member getById(Integer id) {
		Optional<Member> optional = repo.findById(id);
		
		return optional.isPresent() ? optional.get() : null;
	
	}

	@Override
	public Member save(Member member){
		repo.save(member);
		return member;
	}


	@Override
	public Member delete(Integer id) {
		repo.deleteById(id);
		return null;
	}

	@Override
	public boolean exists(Integer id) {
		return (id != null)? repo.existsById(id) : false;
	}

	@Override
	public List<Member> findByEnabled(boolean enabled) {
		return repo.findByEnabled(enabled);
	}
	




}
