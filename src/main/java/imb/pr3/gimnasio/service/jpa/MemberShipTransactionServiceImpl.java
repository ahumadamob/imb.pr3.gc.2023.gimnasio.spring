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
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public MemberShipTransaction getById(Integer id) {
		// TODO Auto-generated method stub
		Optional<MemberShipTransaction> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}

	}

	@Override
	public MemberShipTransaction save(MemberShipTransaction memberShipTransaction) {
		// TODO Auto-generated method stub
		repo.save(memberShipTransaction);
		return memberShipTransaction;
	}

	@Override
	public MemberShipTransaction edit(Integer id, MemberShipTransaction memberShipTransaction) {
		// TODO Auto-generated method stub
		Optional<MemberShipTransaction> memberShipTransactionOptional = repo.findById(id);
		MemberShipTransaction memberShipTransactionMod = memberShipTransactionOptional.get();
		memberShipTransactionMod = repo.save(memberShipTransaction);
		return memberShipTransactionMod;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return (id != null)? repo.existsById(id) : false;
	}
	
	@Override
	public List<MemberShipTransaction> findEnable(boolean enable) {
		return repo.findByEnable(enable);
	}

}
