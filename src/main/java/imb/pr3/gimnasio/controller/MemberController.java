package imb.pr3.gimnasio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import imb.pr3.gimnasio.entity.Member;
import imb.pr3.gimnasio.service.IMemberService;
import imb.pr3.gimnasio.util.ResponseUtil;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping(path = "api/v1/member")
public class MemberController {
	@Autowired
	private IMemberService memberService;
	
	@GetMapping("")
	public ResponseEntity <APIResponse<List<Member>>> getAllMembers(){
		
		return memberService.getAll().isEmpty() ? ResponseUtil.notFound("No se encuentra ningún registro. Para utilizar esta función, primero debe crearlos.")
												: ResponseUtil.success(memberService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <APIResponse<Member>> getMemberById(@PathVariable Integer id){
		
		return memberService.exists(id) ? ResponseUtil.success(memberService.getById(id))
										: ResponseUtil.notFound("Ese miembro no existe.");
	}
	
	
	@PostMapping("")
	public ResponseEntity<APIResponse<Member>> saveMember(@RequestBody Member entity){
		
		return memberService.exists(entity.getId()) ? ResponseUtil.badRequest("Ya existe un miembro con ese ID. Para editar uno, use PUT.")
													: ResponseUtil.created(memberService.save(entity));
	}
	
	@PutMapping("")
	public ResponseEntity<APIResponse<Member>> editMember(@RequestBody Member entity){
		
		return memberService.exists(entity.getId()) ? ResponseUtil.success(memberService.save(entity))
													: ResponseUtil.badRequest("Ya existe un miembro con ese ID. Para editar uno, use PUT.");
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<Member>> deleteMemberById(@PathVariable Integer id){
		
		return memberService.exists(id) ? ResponseUtil.successDeleted("Miembro eliminado", memberService.delete(memberService.getById(id).getId()))
										: ResponseUtil.badRequest("Ese miembro no existe. Chequee el ID proporcionado e intente nuevamente.");
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse<Member>> handleException(Exception ex) {
		return ResponseUtil.badRequest(ex.getMessage());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Member>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}
	

}
