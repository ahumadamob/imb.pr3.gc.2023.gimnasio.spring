package imb.pr3.gimnasio.entity;

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
public class Trainer {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	private String description;
	private boolean enabled;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "person_id")
    private Person person;
	
	@JsonIgnore
	@OneToMany
	@JoinColumn(name = "trainer_id")
	private List<GymClass> gymClasses;

	public Trainer() {
	}
	


	public Trainer(Integer id, String description, boolean enabled, Person person, List<GymClass> gymClasses) {
		super();
		this.id = id;
		this.description = description;
		this.enabled = enabled;
		this.person = person;
		this.gymClasses = gymClasses;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public boolean isEnabled() {
		return enabled;
	}



	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



	public List<GymClass> getGymClasses() {
		return gymClasses;
	}



	public void setGymClasses(List<GymClass> gymClasses) {
		this.gymClasses = gymClasses;
	}


	public Person getPerson() {
		return person;
	}


	public void setPerson(Person person) {
		this.person = person;
	}

	
	
}
