package imb.pr3.gimnasio.service;

import java.util.List;

import imb.pr3.gimnasio.entity.Member;


public interface IMemberService {

	public List<Member> getAll();
	
	public Member getById(Integer id);
	
	public Member save(Member member);
	
	public Member edit(Integer id, Member member);
	
	public Member delete(Integer id);
	
	public boolean exists(Integer id);
}
