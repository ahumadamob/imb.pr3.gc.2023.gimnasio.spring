package imb.pr3.gimnasio.service;

import java.util.List;

import imb.pr3.gimnasio.entity.GymClass;

public interface iGymClassService {
	public List<GymClass> getAllGymClass() throws Exception;

	public GymClass getGymClassById (Integer id) throws Exception;

	public GymClass createGymClass (GymClass gymclass) throws Exception;

	public GymClass editGymClass (Integer id, GymClass gymclass) throws Exception;

	public boolean deleteGymClass (Integer id) throws Exception;
}
