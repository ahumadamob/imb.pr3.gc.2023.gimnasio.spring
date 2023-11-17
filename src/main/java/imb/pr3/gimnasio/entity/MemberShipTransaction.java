package imb.pr3.gimnasio.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MemberShipTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transaction_id;

	private Date date_transaction;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "memberShipPlan_id")
	private MembershipPlan membershipPlan;

	public MemberShipTransaction() {
	}

	public MemberShipTransaction(int transaction_id, Date date_transaction, Member member, MembershipPlan membershipPlan) {
		this.transaction_id = transaction_id;
		this.date_transaction = date_transaction;
		this.member = member;
		this.membershipPlan = membershipPlan;
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
	 * @return the member
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * @param member the member to set
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * @return the membershipPlan
	 */
	public MembershipPlan getMembershipPlan() {
		return membershipPlan;
	}

	/**
	 * @param membershipPlan the membershipPlan to set
	 */
	public void setMembershipPlan(MembershipPlan membershipPlan) {
		this.membershipPlan = membershipPlan;
	}

	@Override
	public String toString() {
		return "MemberShipTransaction [transaction_id=" + transaction_id + ", date_transaction=" + date_transaction
				+ ", member=" + member + ", membershipPlan=" + membershipPlan + "]";
	}


}
