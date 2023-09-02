package imb.pr3.gimnasio.service;

import java.util.List;

import imb.pr3.gimnasio.entity.Member;


public interface IMemberService {

public List<Member> buscarTodos() throws Exception;
	
	public Member buscarPorId (Integer id)throws Exception;
	
	public Member guardar (Member tarea)throws Exception;
	
	public Member modificar (Integer id, Member tarea) throws Exception;
	
	public boolean borrar (Integer id) throws Exception;
}
