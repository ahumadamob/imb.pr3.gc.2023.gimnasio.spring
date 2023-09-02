package imb.pr3.gimnasio.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int member_id;
	private String first_name;
	private String last_name;
	private String gender;
	private Date date_of_birth;
	private String email;
	private String phone_number;
	private String address;
	private Date registration_date;
	
	public Member() {
	}

	public Member(int member_id, String first_name, String last_name, String gender, Date date_of_birth, String email,
			String phone_number, String address, Date registration_date) {
		this.member_id = member_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.date_of_birth = date_of_birth;
		this.email = email;
		this.phone_number = phone_number;
		this.address = address;
		this.registration_date = registration_date;
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
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the date_of_birth
	 */
	public Date getDate_of_birth() {
		return date_of_birth;
	}

	/**
	 * @param date_of_birth the date_of_birth to set
	 */
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone_number
	 */
	public String getPhone_number() {
		return phone_number;
	}

	/**
	 * @param phone_number the phone_number to set
	 */
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
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
		return "Member [member_id=" + member_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", gender=" + gender + ", date_of_birth=" + date_of_birth + ", email=" + email + ", phone_number="
				+ phone_number + ", address=" + address + ", registration_date=" + registration_date + "]";
	}
	
	
	

}
