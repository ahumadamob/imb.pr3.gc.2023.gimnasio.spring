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
	public List<Member> buscarTodos() throws Exception {
		try {
			List<Member> members = repo.findAll();
			return members;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Member buscarPorId(Integer id) throws Exception {
		try {
			Optional<Member> memberOptional = repo.findById(id);
			return memberOptional.get();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Member guardar(Member member) throws Exception {
		repo.save(member);
		try {
			member = repo.save(member);
			return member;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Member modificar(Integer id, Member member) throws Exception {
		try {
			Optional<Member> memberOptional = repo.findById(id);
			Member memberMod = memberOptional.get();
			memberMod = repo.save(member);
			return memberMod;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public boolean borrar(Integer id) throws Exception {
		try {
			if(repo.existsById(id)) {
				repo.deleteById(id);
				return true;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
	}
	}
	




}
