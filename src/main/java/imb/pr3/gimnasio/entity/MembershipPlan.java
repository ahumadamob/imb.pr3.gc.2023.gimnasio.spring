package imb.pr3.gimnasio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MembershipPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer duration_months;
	private double price;
	
	/*@OneToMany(mappedBy = "membershipPlan")
	private List<MemberShipTransaction> mshipTransactions;
	*/
	
	public MembershipPlan() {
	}



	public MembershipPlan(Integer id, String name, Integer duration_months, double price) {
		this.id = id;
		this.name = name;
		this.duration_months = duration_months;
		this.price = price;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Integer getDuration_months() {
		return duration_months;
	}



	public void setDuration_months(Integer duration_months) {
		this.duration_months = duration_months;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
