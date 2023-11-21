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
	@JoinColumn(name = "membership_plan_id")
	private MembershipPlan membership_plans;

	public MemberShipTransaction() {
	}
	
	public MemberShipTransaction(int transaction_id, Date date_transaction, Member member,
			MembershipPlan membership_plans) {
		super();
		this.transaction_id = transaction_id;
		this.date_transaction = date_transaction;
		this.member = member;
		this.membership_plans = membership_plans;
	}

	
	public int getTransaction_id() {
		return transaction_id;
	}


	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}


	public Date getDate_transaction() {
		return date_transaction;
	}


	public void setDate_transaction(Date date_transaction) {
		this.date_transaction = date_transaction;
	}


	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public MembershipPlan getMembership_plans() {
		return membership_plans;
	}

	public void setMembership_plans(MembershipPlan membership_plans) {
		this.membership_plans = membership_plans;
	}

	@Override
	public String toString() {
		return "MemberShipTransaction [transaction_id=" + transaction_id + ", date_transaction=" + date_transaction
				+ ", member_id=" + member + ", memberShipPlan_id=" + membership_plans+ "]";
	}

	




}
