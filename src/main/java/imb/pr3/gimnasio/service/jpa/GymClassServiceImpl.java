package imb.pr3.gimnasio.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb.pr3.gimnasio.entity.GymClass;
import imb.pr3.gimnasio.repository.GymClassRepository;
import imb.pr3.gimnasio.service.IGymClassService;

import java.util.Optional;

@Service
@Primary
public class GymClassServiceImpl implements IGymClassService{

	@Autowired
	GymClassRepository repo;

	@Override
	public List<GymClass>getAll() {
		
		List<GymClass> gymClasses = repo.findAll();
		return gymClasses;
	}

	@Override
	public GymClass getById(Integer id){
		
		Optional<GymClass> optional = repo.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public GymClass save(GymClass gymclass){
		
		return repo.save(gymclass);
	}

	@Override
	public boolean delete(Integer id){
		
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean exists(Integer id) {
		
		return (id == null) ? false: repo.existsById(id);
	} 

}
