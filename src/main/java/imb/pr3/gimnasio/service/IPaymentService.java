package imb.pr3.gimnasio.service;

import java.util.List;

import imb.pr3.gimnasio.entity.Payment;

public interface IPaymentService {
	
	List<Payment> getAllPayments();
	Payment getPaymentById(Integer id);
	Payment savePayment(Payment payment);
	Payment deletePayment(Integer id);
	boolean exists(Integer id);

}
