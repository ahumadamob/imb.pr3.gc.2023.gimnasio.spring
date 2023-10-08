package imb.pr3.gimnasio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr3.gimnasio.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
