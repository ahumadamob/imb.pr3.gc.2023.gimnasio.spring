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

import imb.pr3.gimnasio.entity.MemberShipTransaction;
import imb.pr3.gimnasio.entity.Trainer;
import imb.pr3.gimnasio.service.IMemberShipTransactionService;
import imb.pr3.gimnasio.util.ResponseUtil;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/membershiptransaction")
public class MemberShipTransactionController {

	@Autowired
	IMemberShipTransactionService memberShipTransactionService;

	@GetMapping("")
	public ResponseEntity<APIResponse<List<MemberShipTransaction>>> getAllMembershipPlans() {
		return memberShipTransactionService.getAll().isEmpty() ? ResponseUtil.notFound("No se encontró ninguna Transaccion.")
														: ResponseUtil.success(memberShipTransactionService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<MemberShipTransaction>> getMembershipPlanById(@PathVariable Integer id) {
		return memberShipTransactionService.exists(id) ? ResponseUtil.success(memberShipTransactionService.getById(id))
												: ResponseUtil.notFound("No se encontró ninguna Transaccion con ese ID.");
	}
	
	@PostMapping("")
	public ResponseEntity<APIResponse<MemberShipTransaction>> saveMembershipPlan(@RequestBody MemberShipTransaction entity){
		return memberShipTransactionService.exists(entity.getTransaction_id()) ? ResponseUtil.badRequest("Ya existe una Transaccion con ese ID. Para editar un plan, use PUT.")
															: ResponseUtil.success(memberShipTransactionService.save(entity));
	}
	
	@PutMapping("")
	public ResponseEntity<APIResponse<MemberShipTransaction>> editMembershipPlan(@RequestBody MemberShipTransaction entity){
		return memberShipTransactionService.exists(entity.getTransaction_id()) ? ResponseUtil.success(memberShipTransactionService.save(entity))
															: ResponseUtil.notFound("No se encontró ninguna Transaccion con ese ID.");
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<MemberShipTransaction>> deleteMembershipTransactionById(@PathVariable Integer id){
		return memberShipTransactionService.exists(id) ? ResponseUtil.successDeleted("Transaccion eliminado correctamente", memberShipTransactionService.delete(memberShipTransactionService.getById(id).getTransaction_id()))
												: ResponseUtil.notFound("No se encontró ninguna Transaccion con ese ID.");
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse<MemberShipTransaction>> handleException(Exception ex) {
		return ResponseUtil.badRequest(ex.getMessage());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Trainer>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}
}
