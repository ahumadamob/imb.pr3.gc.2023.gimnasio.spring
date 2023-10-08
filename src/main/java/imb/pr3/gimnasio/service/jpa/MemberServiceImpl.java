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
		/*
		 * try { List<Member> members = repo.findAll(); return members; } catch
		 * (Exception e) { throw new Exception(e.getMessage()); }
		 */
	}

	@Override
	public Member getById(Integer id) {
		Optional<Member> optional = repo.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			return null;
		}
		/*
		 * try { Optional<Member> memberOptional = repo.findById(id); return
		 * memberOptional.get(); } catch (Exception e) { throw new
		 * Exception(e.getMessage()); }
		 */
	}

	@Override
	public Member save(Member member){
		member = repo.save(member);
		return member;
		/*
		 * try { member = repo.save(member); return member; } catch (Exception e) {
		 * throw new Exception(e.getMessage()); }
		 */
	}

	@Override
	public Member edit(Integer id, Member member) {
		Optional<Member> memberOptional = repo.findById(id);
		Member memberMod = memberOptional.get();
		memberMod = repo.save(member);
		return memberMod; 
		
		/*
		 * try { Optional<Member> memberOptional = repo.findById(id); Member memberMod =
		 * memberOptional.get(); memberMod = repo.save(member); return memberMod; }
		 * catch (Exception e) { throw new Exception(e.getMessage()); }
		 */
	}

	@Override
	public Member delete(Integer id) {
		repo.deleteById(id);
		return null;
		/*
		 * try { if(repo.existsById(id)) { repo.deleteById(id); return true; } else {
		 * throw new Exception(); } } catch (Exception e) { throw new
		 * Exception(e.getMessage()); }
		 */
	}

	@Override
	public boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return (id != null)? repo.existsById(id) : false;
	}
	




}
