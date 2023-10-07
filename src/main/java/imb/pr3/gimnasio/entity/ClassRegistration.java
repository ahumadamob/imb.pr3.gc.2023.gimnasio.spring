package imb.pr3.gimnasio.entity;

import java.util.Date;

public class ClassRegistration {

	private int classRegistration_id;
	private float salary;
	private Date date_registration;
	private String attendance_status;

	private int member_id;
	private int class_id;

	public ClassRegistration() {
	}

	public ClassRegistration(int classRegistration_id, float salary, Date date_registration, String attendance_status,
			int member_id, int class_id) {
		this.classRegistration_id = classRegistration_id;
		this.salary = salary;
		this.date_registration = date_registration;
		this.attendance_status = attendance_status;
		this.member_id = member_id;
		this.class_id = class_id;
	}

	/**
	 * @return the classRegistration_id
	 */
	public int getClassRegistration_id() {
		return classRegistration_id;
	}

	/**
	 * @param classRegistration_id the classRegistration_id to set
	 */
	public void setClassRegistration_id(int classRegistration_id) {
		this.classRegistration_id = classRegistration_id;
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
	 * @return the class_id
	 */
	public int getClass_id() {
		return class_id;
	}

	/**
	 * @param class_id the class_id to set
	 */
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	@Override
	public String toString() {
		return "ClassRegistration [classRegistration_id=" + classRegistration_id + ", salary=" + salary
				+ ", date_registration=" + date_registration + ", attendance_status=" + attendance_status
				+ ", member_id=" + member_id + ", class_id=" + class_id + "]";
	}



}
