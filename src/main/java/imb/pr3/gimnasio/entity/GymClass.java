package imb.pr3.gimnasio.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class GymClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer day;
	private Integer time;
	private Integer maxCapacity;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;
	
	@JsonIgnore
	@OneToMany
	@JoinColumn(name = "gymclass_id")
	private List<ClassRegistration> classRegistrations;

	public GymClass() {

	}

	public GymClass(Integer id, String name, Integer day, Integer time, Integer maxCapacity, Trainer trainer, List<ClassRegistration> classRegistrations) {
		this.id = id;
		this.name = name;
		this.day = day;
		this.time = time;
		this.maxCapacity = maxCapacity;
		this.trainer = trainer;
		this.classRegistrations = classRegistrations;
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

	public List<ClassRegistration> getClassRegistrations() {
		return classRegistrations;
	}

	public void setClassRegistrations(List<ClassRegistration> classRegistrations) {
		this.classRegistrations = classRegistrations;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

}
