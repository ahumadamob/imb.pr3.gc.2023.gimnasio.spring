package imb.pr3.gimnasio.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore
	@OneToMany
	@JoinColumn(name = "member_id")
	private List<Payment> payments;
	
	@JsonIgnore
	@OneToMany
	@JoinColumn(name = "member_id")
	private List<ClassRegistration> classregistrations;
	
	@JsonIgnore
	@OneToMany
	@JoinColumn(name= "member_id")
	private List<MemberShipTransaction> members;
	
	private boolean enabled;
	
	public Member() {
	}


	public Member(Integer member_id, String address, Date registration_date, Person person, List<Payment> payments,
			List<ClassRegistration> classregistrations, List<MemberShipTransaction> members, boolean enabled) {
		super();
		this.member_id = member_id;
		this.address = address;
		this.registration_date = registration_date;
		this.person = person;
		this.payments = payments;
		this.classregistrations = classregistrations;
		this.members = members;
		this.enabled = enabled;
	}


	public int getId() {
		return member_id;
	}

	
	public void setId(Integer id) {
		this.member_id = id;
	}

	

	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Date getRegistration_date() {
		return registration_date;
	}


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


	public List<ClassRegistration> getClassregistrations() {
		return classregistrations;
	}


	public void setClassregistrations(List<ClassRegistration> classregistrations) {
		this.classregistrations = classregistrations;
	}


	public List<MemberShipTransaction> getMembers() {
		return members;
	}


	public void setMembers(List<MemberShipTransaction> members) {
		this.members = members;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
