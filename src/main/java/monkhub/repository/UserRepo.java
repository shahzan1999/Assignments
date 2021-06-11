package monkhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import monkhub.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
 
	public User findByUserName(String uname);
	
}
