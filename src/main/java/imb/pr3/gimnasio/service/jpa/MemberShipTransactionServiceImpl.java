package imb.pr3.gimnasio.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.pr3.gimnasio.entity.MemberShipTransaction;
import imb.pr3.gimnasio.repository.MemberShipTransactionRepository;
import imb.pr3.gimnasio.service.IMemberShipTransactionService;


@Service
public class MemberShipTransactionServiceImpl implements IMemberShipTransactionService {

	@Autowired
	MemberShipTransactionRepository repo;

	@Override
	public List<MemberShipTransaction> getAll() {
		return repo.findAll();
	}

	@Override
	public MemberShipTransaction getById(Integer id) {
		Optional<MemberShipTransaction> optional = repo.findById(id);
		return optional.isPresent() ? optional.get() : null;

	}

	@Override
	public MemberShipTransaction save(MemberShipTransaction memberShipTransaction) {
		repo.save(memberShipTransaction);
		return memberShipTransaction;
	}

	@Override
	public MemberShipTransaction delete(Integer id) {
		repo.deleteById(id);
		return null;
	}

	@Override
	public boolean exists(Integer id) {
		return (id != null)? repo.existsById(id) : false;
	}

}
