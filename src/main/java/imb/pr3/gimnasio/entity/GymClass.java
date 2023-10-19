package imb.pr3.gimnasio.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class GymClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer day;
	private Integer time;
	private int maxCapacity;
	
	@OneToOne
	@JoinColumn(name = "trainer_id")
	private Trainer trainer;
	
	@OneToMany
	@JoinColumn(name="classReg_id")
	private List <ClassRegistration> classRegistration = new ArrayList<ClassRegistration>();

	public GymClass() { 

	}

	public GymClass(Integer id, String name, Integer day, Integer time, int maxCapacity, Trainer trainer) {
		this.id = id;
		this.name = name;
		this.day = day;
		this.time = time;
		this.maxCapacity = maxCapacity;
		this.trainer = trainer;
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

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public List<ClassRegistration> getClassRegistration() {
		return classRegistration;
	}

	public void setClassRegistration(List<ClassRegistration> classRegistration) {
		this.classRegistration = classRegistration;
	}

	@Override
	public String toString() {
		return "GymClass [id=" + id + ", name=" + name + ", day=" + day + ", time=" + time + ", maxCapacity=" + maxCapacity + ", trainerId=" + trainer + "]";
	}

	
}
