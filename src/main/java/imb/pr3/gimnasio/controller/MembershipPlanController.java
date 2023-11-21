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
import imb.pr3.gimnasio.entity.MembershipPlan;
import imb.pr3.gimnasio.service.IMembershipPlan;
import imb.pr3.gimnasio.util.ResponseUtil;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/membership-plan")
public class MembershipPlanController {

	@Autowired
	IMembershipPlan MembershipPlanService;
	
	@GetMapping("")
	public ResponseEntity<APIResponse<List<MembershipPlan>>> getAllMembershipPlans() {
		return MembershipPlanService.getAll().isEmpty() ? ResponseUtil.notFound("No se encontró ningún registro. Para utilizar esta función, primero debe crearlos.")
														: ResponseUtil.success(MembershipPlanService.getAll());
	}
	
	@GetMapping("/enabled")
	public ResponseEntity<APIResponse<List<MembershipPlan>>> getAllEnabledMembershipPlans() {
		return MembershipPlanService.findByEnabled(true).isEmpty() ? ResponseUtil.notFound("No se encontró ningún plan habilitado.")
														: ResponseUtil.success(MembershipPlanService.findByEnabled(true));
	}
	
	@GetMapping("/disabled")
	public ResponseEntity<APIResponse<List<MembershipPlan>>> getAllDisabledMembershipPlans() {
		return MembershipPlanService.findByEnabled(false).isEmpty() ? ResponseUtil.notFound("No se encontró ningún plan deshabilitado.")
														: ResponseUtil.success(MembershipPlanService.findByEnabled(false));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<MembershipPlan>> getMembershipPlanById(@PathVariable Integer id) {
		return MembershipPlanService.exists(id) ? ResponseUtil.success(MembershipPlanService.getById(id))
												: ResponseUtil.notFound("No se encontró ningún plan con ese ID.");
	}
	
	@PostMapping("")
	public ResponseEntity<APIResponse<MembershipPlan>> saveMembershipPlan(@RequestBody MembershipPlan entity){
		return MembershipPlanService.exists(entity.getId()) ? ResponseUtil.badRequest("Ya existe un plan con ese ID. Para editar un plan, use PUT.")
															: ResponseUtil.success(MembershipPlanService.save(entity));
	}
	
	@PutMapping("")
	public ResponseEntity<APIResponse<MembershipPlan>> editMembershipPlan(@RequestBody MembershipPlan entity){
		return MembershipPlanService.exists(entity.getId()) ? ResponseUtil.success(MembershipPlanService.save(entity))
															: ResponseUtil.notFound("No se encontró ningún plan con ese ID.");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<MembershipPlan>> deleteMembershipPlanById(@PathVariable Integer id){
		return MembershipPlanService.exists(id) ? ResponseUtil.successDeleted("Plan eliminado correctamente", MembershipPlanService.delete(MembershipPlanService.getById(id).getId()))
												: ResponseUtil.notFound("No se encontró ningún plan con ese ID.");
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse<MembershipPlan>> handleException(Exception ex) {
		return ResponseUtil.badRequest(ex.getMessage());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<MembershipPlan>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}
	
}
