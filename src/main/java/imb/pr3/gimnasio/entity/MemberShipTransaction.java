package imb.pr3.gimnasio.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MemberShipTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transaction_id;

	private Date date_transaction;

	private int member_id;

	private int memberShipPlan_id;

	public MemberShipTransaction() {
	}

	public MemberShipTransaction(int transaction_id, Date date_transaction, int member_id, int memberShipPlan_id) {
		this.transaction_id = transaction_id;
		this.date_transaction = date_transaction;
		this.member_id = member_id;
		this.memberShipPlan_id = memberShipPlan_id;
	}

	/**
	 * @return the transaction_id
	 */
	public int getTransaction_id() {
		return transaction_id;
	}

	/**
	 * @param transaction_id the transaction_id to set
	 */
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	/**
	 * @return the date_transaction
	 */
	public Date getDate_transaction() {
		return date_transaction;
	}

	/**
	 * @param date_transaction the date_transaction to set
	 */
	public void setDate_transaction(Date date_transaction) {
		this.date_transaction = date_transaction;
	}

	/**
	 * @return the member_id
	 */
	public int getMember_id() {
		return member_id;
	}

	/**
	 * @param member_id the member_id to set
	 */
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	/**
	 * @return the memberShipPlan_id
	 */
	public int getMemberShipPlan_id() {
		return memberShipPlan_id;
	}

	/**
	 * @param memberShipPlan_id the memberShipPlan_id to set
	 */
	public void setMemberShipPlan_id(int memberShipPlan_id) {
		this.memberShipPlan_id = memberShipPlan_id;
	}

	@Override
	public String toString() {
		return "MemberShipTransaction [transaction_id=" + transaction_id + ", date_transaction=" + date_transaction
				+ ", member_id=" + member_id + ", memberShipPlan_id=" + memberShipPlan_id + "]";
	}




}
