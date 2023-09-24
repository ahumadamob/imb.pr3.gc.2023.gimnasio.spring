package imb.pr3.gimnasio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GymClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer day;
	private Integer time;
	private int maxCapacity;
	private int trainerId;

	public GymClass() {

	}

	public GymClass(Integer id, String name, Integer day, Integer time, int maxCapacity, int trainerId) {
		this.id = id;
		this.name = name;
		this.day = day;
		this.time = time;
		this.maxCapacity = maxCapacity;
		this.trainerId = trainerId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public int getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}


	@Override
	public String toString() {
		return "GymClass [id=" + id + ", name=" + name + ", day=" + day + ", time=" + time + ", maxCapacity=" + maxCapacity + ", trainerId=" + trainerId + "]";
	}
}
