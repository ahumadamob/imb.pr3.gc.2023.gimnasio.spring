package imb.pr3.gimnasio.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Payment {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	private double amount;
	private Date payment_date;
	private Integer memberId;
	
	/*@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	*/
	
	
	public Payment() {
	}
	
	
	public Payment(Integer id, double amount, Date payment_date, Integer memberId) {
		super();
		this.id = id;
		this.amount = amount;
		this.payment_date = payment_date;
		this.memberId = memberId;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	
	
}
