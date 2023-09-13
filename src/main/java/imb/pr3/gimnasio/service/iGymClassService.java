package imb.pr3.gimnasio.service;

import java.util.List;

import imb.pr3.gimnasio.entity.GymClass;

public interface iGymClassService {

	public List<GymClass> getAllGymClasses();

	public GymClass getGymClassById (Integer id);

	public GymClass createGymClass (GymClass gymclass);

	public GymClass editGymClass (Integer id, GymClass gymclass);

	public boolean deleteGymClass (Integer id);
}
