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
		return repo.findAll();
	}
	
	@Override
	public GymClass getById(Integer id){
		Optional<GymClass> optional = repo.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}


	@Override
	public GymClass save(GymClass gymclass){
		repo.save(gymclass);
		return gymclass;
	}

	@Override
	public GymClass delete(Integer id){
		repo.deleteById(id);
		return null;
	}
	
	@Override
	public boolean exists(Integer id) {
		
		return (id != null)? repo.existsById(id) : false;
	}


}
