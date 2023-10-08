package imb.pr3.gimnasio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Trainer /*extends Person*/ {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	private String description;
	private Integer personId;
	
	/*@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
	*/

	public Trainer() {
	}
	
	

	public Trainer(Integer id, String description, Integer personId) {
		this.id = id;
		this.description = description;
		this.personId = personId;
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



	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	
	
}
