package imb.pr3.gimnasio.service;

import java.util.List;

import imb.pr3.gimnasio.entity.Payment;

public interface IPaymentService {
	
	List<Payment> getAll();
	Payment getById(Integer id);
	Payment save(Payment payment);
	Payment delete(Integer id);
	boolean exists(Integer id);

}
