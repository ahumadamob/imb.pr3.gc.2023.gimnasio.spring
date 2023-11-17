package imb.pr3.gimnasio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr3.gimnasio.entity.MemberShipTransaction;

public interface MemberShipTransactionRepository extends JpaRepository<MemberShipTransaction, Integer> {

	List<MemberShipTransaction> findByEnable(boolean enable);
}
