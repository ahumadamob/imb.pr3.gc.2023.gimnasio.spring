package imb.pr3.gimnasio.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb.pr3.gimnasio.entity.GymClass;
import imb.pr3.gimnasio.repository.GymClassRepository;
import imb.pr3.gimnasio.service.iGymClassService;

import java.util.Optional;

@Service
@Primary
public class GymClassServiceImpl implements iGymClassService{

	@Autowired
	GymClassRepository repo;

	@Override
	public List<GymClass>getAllGymClasses() {
		List<GymClass> gymClasses = repo.findAll();
		return gymClasses;
	}

	@Override
	public GymClass getGymClassById(Integer id){
		Optional<GymClass> optional = repo.findById(id);

		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			return null;
		}
	}

	@Override
	public GymClass createGymClass(GymClass gymclass){
		gymclass = repo.save(gymclass);
		return gymclass;
	}

	@Override
	public GymClass editGymClass(Integer id, GymClass gymclass){
		Optional<GymClass> gymclassOptional = repo.findById(id);
		GymClass gymclassMod = gymclassOptional.get();
		gymclassMod = repo.save(gymclass);
		return gymclassMod;
	}

	@Override
	public boolean deleteGymClass(Integer id){
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		else {
			return false;
		}

	}

}
