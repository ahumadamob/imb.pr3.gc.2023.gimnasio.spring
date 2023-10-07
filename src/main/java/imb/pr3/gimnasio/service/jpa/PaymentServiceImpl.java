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
	public List<Payment> getAll() {
		return repo.findAll();
	}

	@Override
	public Payment getById(Integer id) {
		Optional<Payment> optional = repo.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public Payment save(Payment payment) {
		repo.save(payment);
		return payment;
	}

	@Override
	public Payment delete(Integer id) {
		repo.deleteById(id);
		return null;
	}

	@Override
	public boolean exists(Integer id) {
		return (id == null) ? false : repo.existsById(id);
	}

	
	
	
	

}
