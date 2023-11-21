package imb.pr3.gimnasio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr3.gimnasio.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

	public List<Member> findByEnabled(boolean enabled);
}
