package imb.pr3.gimnasio.service;

import java.util.List;

import imb.pr3.gimnasio.entity.MemberShipTransaction;

public interface IMemberShipTransactionService {

	public List<MemberShipTransaction> getAll ();

	public MemberShipTransaction getById (Integer id);

	public MemberShipTransaction save (MemberShipTransaction member);

	public MemberShipTransaction edit (Integer id, MemberShipTransaction memberShipTransaction);

	public boolean delete (Integer id);

	public boolean exists(Integer id);
}
