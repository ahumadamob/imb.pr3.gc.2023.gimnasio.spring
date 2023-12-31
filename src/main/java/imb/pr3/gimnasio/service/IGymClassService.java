package imb.pr3.gimnasio.service;

import java.util.List;

import imb.pr3.gimnasio.entity.GymClass;

public interface IGymClassService {

	public List<GymClass> getAll();

	public GymClass getById (Integer id);

	public GymClass save (GymClass gymclass);

	public boolean delete (Integer id);
	
	boolean exists(Integer id);
}
