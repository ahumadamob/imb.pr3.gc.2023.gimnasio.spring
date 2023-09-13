package imb.pr3.gimnasio.service;

import java.util.List;

import imb.pr3.gimnasio.entity.Member;


public interface IMemberService {

public List<Member> buscarTodos();
	
	public Member buscarPorId (Integer id);
	
	public Member guardar (Member member);
	
	public Member modificar (Integer id, Member member);
	
	public boolean borrar (Integer id);
}
