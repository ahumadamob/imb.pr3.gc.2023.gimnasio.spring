package imb.pr3.gimnasio.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.pr3.gimnasio.entity.Payment;
import imb.pr3.gimnasio.repository.PaymentRepository;
import imb.pr3.gimnasio.service.IPaymentService;

@Service
public class PaymentServiceImpl implements IPaymentService{
	
	@Autowired
	PaymentRepository repo;

	@Override
	public List<Payment> getAllPayments() {
		return repo.findAll();
	}

	@Override
	public Payment getPaymentById(Integer id) {
		Optional<Payment> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	@Override
	public Payment savePayment(Payment payment) {
		repo.save(payment);
		return payment;
	}

	@Override
	public Payment deletePayment(Integer id) {
		repo.deleteById(id);
		return null;
	}

	@Override
	public boolean exists(Integer id) {
		if(id == null) {
			return false;
		} else {
			Payment payment = getPaymentById(id);
			if(payment == null) {
				return false;
			} else {
				return true;
			}
		}
	}

	
	
	
	

}
