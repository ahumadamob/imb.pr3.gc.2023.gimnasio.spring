package imb.pr3.gimnasio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.pr3.gimnasio.entity.Payment;
import imb.pr3.gimnasio.service.IPaymentService;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

	@Autowired
	IPaymentService service;
	
	@GetMapping("")
	public APIResponse<List<Payment>> getAll(){
		List<Payment> paymentList = service.getAllPayments();
		List<String> messages = new ArrayList<>();
		messages.add("Listado de pagos mostrado con éxito.");
		APIResponse<List<Payment>>response = new APIResponse<>(200, messages, paymentList);
		
		return response;
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Payment>> getById(@PathVariable Integer id) {
		List<String> messages = new ArrayList<>();
		Integer status;
		Payment paymentById = service.getPaymentById(id);
		
		if (service.exists(id)) {
			status = HttpStatus.OK.value();
			messages.add("Entrenador encontrado.");
		} else {
			status = HttpStatus.BAD_REQUEST.value();
			messages.add("No se encuentra un entrenador con ese ID");
		}
		
		APIResponse<Payment> response = new APIResponse<>(status, messages, paymentById);
		return ResponseEntity.status(status).body(response);
	}
	
	@PostMapping("")
	public ResponseEntity<APIResponse<Payment>> save(@RequestBody Payment entity){
		List<String> messages = new ArrayList<>();
		Integer status;
		APIResponse<Payment> response;

		if (service.exists(entity.getId())) {
			status = HttpStatus.BAD_REQUEST.value();
			messages.add("Ese ID ya existe. Para editar, use PUT.");
			response = new APIResponse<>(status, messages, service.getPaymentById(entity.getId()));
		} else {
			status = HttpStatus.OK.value();
			messages.add("Pago registrado con éxito.");
			response = new APIResponse<>(status,messages, service.savePayment(entity));
			
		}
		
		return ResponseEntity.status(status).body(response);
	}
	
	@PutMapping("")
	public ResponseEntity<APIResponse<Payment>> edit(@RequestBody Payment entity){
		List<String> messages = new ArrayList<>();
		Integer status;
		APIResponse<Payment> response;
		if (service.exists(entity.getId())) {
			status = HttpStatus.OK.value();
			messages.add("Pago editado correctamente.");
			response = new APIResponse<>(status, messages, service.savePayment(entity));
		} else {
			status = HttpStatus.BAD_REQUEST.value();
			messages.add("Ese registro no existe.");
			response = new APIResponse<>(status, messages, null);
		}
		return ResponseEntity.status(status).body(response);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<Payment>> delete(@PathVariable Integer id){
		Payment payment = service.getPaymentById(id);
		List<String> messages = new ArrayList<>();
		Integer status;
		
		if (service.exists(id)) {
			status = HttpStatus.OK.value();
			messages.add("Pago eliminado.");
			service.deletePayment(id);
		} else {
			status = HttpStatus.BAD_REQUEST.value();
			messages.add("Ese registro no existe.");
			
		}
		APIResponse<Payment> response = new APIResponse<>(status, messages, payment);
		return ResponseEntity.status(status).body(response);
	}
}
	
