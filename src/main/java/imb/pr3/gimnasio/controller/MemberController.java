package imb.pr3.gimnasio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.pr3.gimnasio.entity.Member;
import imb.pr3.gimnasio.service.IMemberService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/member")
public class MemberController {
	@Autowired
	private IMemberService memberService;
	
	@GetMapping("")
	public ResponseEntity <APIResponse<List<Member>>> getAllMembers(){
		List<Member> member = memberService.getAll();
		if(member.isEmpty()) {
			APIResponse<List<Member>> response = new APIResponse<List<Member>> (200, null, memberService.getAll());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			APIResponse<List<Member>> response = new APIResponse<List<Member>> (200, null, member);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <?> getOne(@PathVariable Integer id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(memberService.getById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"La Tarea no existe.\"}");
		}
	}
	
	@PostMapping("")
	public ResponseEntity <?> save(@RequestBody Member entity){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(memberService.save(entity));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, Por Favor Intente mas tarde.\"}");
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity <?> edit(@PathVariable Integer id, @RequestBody Member entity){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(memberService.edit(id, entity));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error al editar, Por Favor Intente mas tarde.\"}");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity <?> delete(@PathVariable Integer id){
		try {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(memberService.delete(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error al borrar, La tarea no existe.\"}");
		}
	}
	

}
