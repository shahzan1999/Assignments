package monkhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import monkhub.model.UserProfile;

public interface UserProfileRepo extends JpaRepository<UserProfile, Long>{

}
