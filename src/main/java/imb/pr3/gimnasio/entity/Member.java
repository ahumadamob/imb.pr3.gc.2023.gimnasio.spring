package imb.pr3.gimnasio.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int member_id;
	private String address;
	private Date registration_date;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "person_id")
    private Person person;
	
	@OneToMany
	@JoinColumn(name = "member_id")
	private List<Payment> payments;
	
	@OneToMany
	@JoinColumn(name = "member_id")
	private List<ClassRegistration> classregistrations;
	
	@OneToMany
	@JoinColumn(name= "member_id")
	private List<MemberShipTransaction> members;
	
	public Member() {
	}


	public Member(int member_id, String address, Date registration_date, Person person) {
		super();
		this.member_id = member_id;
		this.address = address;
		this.registration_date = registration_date;
		this.person = person;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the registration_date
	 */
	public Date getRegistration_date() {
		return registration_date;
	}

	/**
	 * @param registration_date the registration_date to set
	 */
	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}

	@Override
	public String toString() {
		return "Member [member_id=" + member_id + ", first_name=" + person.getFirstName() + ", last_name=" + person.getLastName()
				+ ", gender=" + ", date_of_birth=" + person.getBirthDate() + ", email=" + person.getEmail() + ", phone_number="
				+ person.getPhone() + ", address=" + address + ", registration_date=" + registration_date + "]";
	}


	public Person getPerson() {
		return person;
	}


	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Payment> getPayments() {
		return payments;
	}
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	

}
