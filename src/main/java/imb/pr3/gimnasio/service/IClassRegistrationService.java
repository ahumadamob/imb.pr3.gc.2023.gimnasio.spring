package imb.pr3.gimnasio.service;

import java.util.List;

import imb.pr3.gimnasio.entity.ClassRegistration;

public interface IClassRegistrationService {

	List<ClassRegistration> getAll();
	ClassRegistration getById(Integer id);
	ClassRegistration save(ClassRegistration clasregistration);
	ClassRegistration delete(Integer id);
	boolean exists(Integer id);
}
