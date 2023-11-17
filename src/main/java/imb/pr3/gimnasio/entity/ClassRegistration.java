package imb.pr3.gimnasio.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ClassRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private float salary;
	private Date date_registration;
	private String attendance_status;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "gymclass_id")
	private GymClass gymclass;;

	public ClassRegistration() {
	}

	
	public ClassRegistration(Integer id, float salary, Date date_registration, String attendance_status,
			Member member, GymClass gymclass) {
		super();
		this.id = id;
		this.salary = salary;
		this.date_registration = date_registration;
		this.attendance_status = attendance_status;
		this.member = member;
		this.gymclass = gymclass;
	}

	/**
	 * @return the classRegistration_id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param classRegistration_id the classRegistration_id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the salary
	 */
	public float getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(float salary) {
		this.salary = salary;
	}

	/**
	 * @return the date_registration
	 */
	public Date getDate_registration() {
		return date_registration;
	}

	/**
	 * @param date_registration the date_registration to set
	 */
	public void setDate_registration(Date date_registration) {
		this.date_registration = date_registration;
	}

	/**
	 * @return the attendance_status
	 */
	public String getAttendance_status() {
		return attendance_status;
	}

	/**
	 * @param attendance_status the attendance_status to set
	 */
	public void setAttendance_status(String attendance_status) {
		this.attendance_status = attendance_status;
	}

	

	public Member getMember() {
		return member;
	}


	public void setMember(Member member) {
		this.member = member;
	}


	public GymClass getGymclass() {
		return gymclass;
	}


	public void setGymclass(GymClass gymclass) {
		this.gymclass = gymclass;
	}


	@Override
	public String toString() {
		return "ClassRegistration [classRegistration_id=" + id + ", salary=" + salary
				+ ", date_registration=" + date_registration + ", attendance_status=" + attendance_status
				+ ", member_id=" + member + ", class_id=" + gymclass+ "]";
	}



}
