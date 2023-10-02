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
import imb.pr3.gimnasio.entity.Payment;
import imb.pr3.gimnasio.service.IPaymentService;
import imb.pr3.gimnasio.util.ResponseUtil;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

	@Autowired
	IPaymentService paymentService;
	
	@GetMapping("")
	public ResponseEntity<APIResponse<List<Payment>>> getAllPayments(){
		return paymentService.getAll().isEmpty() ? ResponseUtil.notFound("No se encuentra ningún registro. Para utilizar esta función, primero debe crearlos.")
									             : ResponseUtil.success(paymentService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Payment>> getPaymentById(@PathVariable Integer id) {
		return paymentService.exists(id) ? ResponseUtil.success(paymentService.getById(id))
										 : ResponseUtil.notFound("No es encontró ningún pago con ese ID");
	}
	
	@PostMapping("")
	public ResponseEntity<APIResponse<Payment>> savePayment(@RequestBody Payment entity){
		return paymentService.exists(entity.getId()) ? ResponseUtil.badRequest("Ese ID de pago ya existe. Si quieres modificarlo, usa PUT.")
													 : ResponseUtil.created(paymentService.save(entity));
	}
	
	@PutMapping("")
	public ResponseEntity<APIResponse<Payment>> editPayment(@RequestBody Payment entity){
		return paymentService.exists(entity.getId()) ? ResponseUtil.success(paymentService.save(entity))
													 : ResponseUtil.badRequest("Ese ID de pago no existe, por lo que no puede editarse.");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<Payment>> deletePayment(@PathVariable Integer id){
		return paymentService.exists(id) ? ResponseUtil.success(paymentService.delete(paymentService.getById(id).getId()))
										 : ResponseUtil.badRequest("Ese ID de pago no existe, por lo que no puede eliminarse.");
	}
	
	//manejador de excepciones para cualquier tipo de error que pueda ser ajeno al programa.
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse<Payment>> handleException(Exception ex) {
		return ResponseUtil.badRequest(ex.getMessage());
		}
		
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Payment>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
		}
}
	
