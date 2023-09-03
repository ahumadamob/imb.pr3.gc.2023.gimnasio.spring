package imb.pr3.gimnasio.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import imb.pr3.gimnasio.entity.GymClass;
import imb.pr3.gimnasio.repository.GymClassRepository;
import imb.pr3.gimnasio.service.iGymClassService;
import java.util.Optional;

public class GymClassServiceImpl implements iGymClassService{

	@Autowired
	GymClassRepository repo;

	@Override
	public List<GymClass>getAllGymClass() throws Exception {
		try {
			List<GymClass> gymClasses = repo.findAll();
			return gymClasses;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public GymClass getGymClassById(Integer id) throws Exception {
		try {
			Optional<GymClass> gymClassOptional = repo.findById(id);
			return gymClassOptional.get();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public GymClass createGymClass(GymClass gymclass) throws Exception {
		repo.save(gymclass);
		try {
			gymclass = repo.save(gymclass);
			return gymclass;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public GymClass editGymClass(Integer id, GymClass gymclass) throws Exception {
		try {
			Optional<GymClass> gymclassOptional = repo.findById(id);
			GymClass gymclassMod = gymclassOptional.get();
			gymclassMod = repo.save(gymclass);
			return gymclassMod;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public boolean deleteGymClass(Integer id) throws Exception {
		try {
			if(repo.existsById(id)) {
				repo.deleteById(id);
				return true;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
	}
	}

}
