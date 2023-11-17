package imb.pr3.gimnasio.controller;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import imb.pr3.gimnasio.service.IMemberShipTransactionService;
import imb.pr3.gimnasio.util.ResponseUtil;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/membershiptransaction")
public class MemberShipTransactionController {

	@Autowired
	IMemberShipTransactionService memberShipTransactionService;

	/*método que busca todas las cMemberShipTransaction existentes
	 * Devuelve un objeto ResponseEntity<APIResponse<List<MemberShipTransaction>>>. 
	 * Es decir que el método responderá a una solicitud HTTP y devuelve una respuesta encapsulada en un objeto ResponseEntity 
	 * Retornará una lista de objetos MemberShipTransaction
	 * service.getAll genera una lista de todas las MemberShipTransaction existentes en la bbdd
	 * isEmpty() verifica si la tabla MemberShipTransaction de la DB esta vacía es decir si no existen MemberShipTransaction
	 * "?" es una operación ternaria, en este caso, si la lista está vacía se ejecutará ResponseUtil.notFound()
	 * Si la lista tiene al menos un elemento, se ejecutará ResponseUtil.success()
	 * ResponseUtil.notFound() es un método de la clase ResponseUtil que genera una respuesta HTTP del tipo 404, agregando además un mensaje personalizado
	 * ResponseUtil.success()  genera una respuesta de exito (HTTP 200) y devuelve la lista de MemberShipTransaction.
	 * */
	
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
	
	
	/*
	 * Crea entidad ('entity') "MemberShipTransaction" (Transaccion de Socio, abono de cuota).  
	 * 
	 * @param entity ResponseEntity devuelve una respuesta HTTP, indicando que la operacion se concretó.
	 * 
	 * @return ResponseEntity cuya respuesta está estandarizada y estructurada, dividida en 'estado', 'datos' y 'mensaje'.
	 * 		-Si la entidad se crea con éxito, persiste en la BBDD y obtiene como respuesta: '201', la nueva entidad y 'null'. 
	 * 		-Si ya existe una entidad con el mismo ID, obtiene como respuesta: '400', datos nulos y un mensaje indicando el error.
	 * 		- Se proporciona un mensaje de error con APIResponse.
	 * 
	 * @PostMapping Notacion de Spring que indica que el metodo va a manejar solicitudes HTTP del tipo POST que lleguen a la URL.
	 * 				Para las pruebas se utilizo Postman.
	 * 	
	 * @RequestBody 'MemberShipTransaction entity' toma un objeto 'MemberShipTransaction' como entrada en una solicitud HTTP. Se utiliza en este caso porque se trata de una creacion.
	 * 			
	 */
	@PostMapping("")
	public ResponseEntity<APIResponse<MemberShipTransaction>> saveMembershipPlan(@RequestBody MemberShipTransaction entity){
		/* el Operador Ternario verifica si ya existe un MemberShipTransaction con el mismo ID en la BBDD
		Si ya existe, se retorna con 'ResponseUtil' ResponseEntity del tipo BAD_REQUEST, pasando como parametro String al ResponseUtil un mensaje de error.*/
		return memberShipTransactionService.exists(entity.getTransaction_id()) ? ResponseUtil.badRequest("Ya existe una Transaccion con ese ID. Para editar un plan, use PUT.")
															: ResponseUtil.success(memberShipTransactionService.save(entity));
		/* Si no existe, se retorna con 'ResponseUtil' una 'ResponseEntity' del tipo CREATED, llamando al metodo save() del servicio,
		   y pasandole como parametro la entidad (entity) al 'ResponseUtil'	*/
	}
	
	@PutMapping("")
	public ResponseEntity<APIResponse<MemberShipTransaction>> editMembershipPlan(@RequestBody MemberShipTransaction entity){
		return memberShipTransactionService.exists(entity.getTransaction_id()) ? ResponseUtil.success(memberShipTransactionService.save(entity))
															: ResponseUtil.notFound("No se encontró ninguna Transaccion con ese ID.");
	}
	
	/* VER PORQUE NO FUNCIONA
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<MemberShipTransaction>> deleteMembershipTransactionById(@PathVariable Integer id){
		return memberShipTransactionService.exists(id) ? ResponseUtil.successDeleted("Transaccion eliminado correctamente", memberShipTransactionService.delete(memberShipTransactionService.getById(id).getTransaction_id()))
												: ResponseUtil.notFound("No se encontró ninguna Transaccion con ese ID.");
	}*/
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse<MemberShipTransaction>> handleException(Exception ex) {
		return ResponseUtil.badRequest(ex.getMessage());
	}
	
	/*@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<MemberShipTransaction>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}*/
	
	@GetMapping("/enabled")
	public ResponseEntity<APIResponse<List<MemberShipTransaction>>> findEnabled() {
		List<MemberShipTransaction> membershipsEnabled = memberShipTransactionService.findEnable(true);
		return membershipsEnabled.isEmpty() ? ResponseUtil.notFound("No hay Transacciones habilitadas ")
				: ResponseUtil.success(membershipsEnabled);
	}
	
	@GetMapping("/disabled")
	public ResponseEntity<APIResponse<List<MemberShipTransaction>>> findDisabled() {
		List<MemberShipTransaction> membershipsDisabled = memberShipTransactionService.findEnable(false);
		return membershipsDisabled.isEmpty() ? ResponseUtil.notFound("No hay Transacciones Deshabilitadas ")
				: ResponseUtil.success(membershipsDisabled);
	}
	
	@PutMapping("/enabled/{id}")
	public ResponseEntity<APIResponse<MemberShipTransaction>> enableTransaction(@PathVariable Integer id) throws Exception {
		MemberShipTransaction memberShipTransaction = 	memberShipTransactionService.getById(id);

		if (memberShipTransaction == null)
			return ResponseUtil.badRequest("El ID no existe");

		if (!memberShipTransaction.isEnable()) {
			memberShipTransaction.setEnable(true);
			return ResponseUtil.success(memberShipTransactionService.save(memberShipTransaction));
		} else {
			return ResponseUtil.badRequest("memberShipTransaction ya está habilitada.");
		}
	}
	
	@PutMapping("/disabled/{id}")
	public ResponseEntity<APIResponse<MemberShipTransaction>> disableTransaction(@PathVariable Integer id) throws Exception {
		MemberShipTransaction memberShipTransaction = memberShipTransactionService.getById(id);

		if (memberShipTransaction == null)
			return ResponseUtil.badRequest("El ID no existe");

		if (memberShipTransaction.isEnable()) {
			memberShipTransaction.setEnable(false);
			return ResponseUtil.success(memberShipTransactionService.save(memberShipTransaction));
		} else {
			return ResponseUtil.badRequest("memberShipTransaction ya está deshabilitada.");
		}
	}
	
	@DeleteMapping("/disabled/{id}")
	public ResponseEntity<APIResponse<Boolean>> deleteDisableTransaction(@PathVariable Integer id) throws Exception {
		MemberShipTransaction memberShipTransaction = memberShipTransactionService.getById(id);

		if (memberShipTransaction == null)
			return ResponseUtil.badRequest("El ID no existe");

		return !memberShipTransaction.isEnable() ?
				ResponseUtil.success(memberShipTransactionService.delete(id)) :
				ResponseUtil.badRequest("No se puede eliminar. memberShipTransaction no está deshabilitada.");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<Boolean>> deleteTransaction(@PathVariable Integer id) throws Exception {
		MemberShipTransaction memberShipTransaction = memberShipTransactionService.getById(id);

		if (memberShipTransaction == null)
			return ResponseUtil.badRequest("El ID no existe");

		return !memberShipTransaction.isEnable() ?
				ResponseUtil.success(memberShipTransactionService.delete(id)) :
				ResponseUtil.badRequest("No se puede eliminar. memberShipTransaction no está deshabilitada.");
	}
}
