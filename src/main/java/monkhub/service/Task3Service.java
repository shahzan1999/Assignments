package monkhub.service;

import java.util.List;

import monkhub.dto.customDTO;
import monkhub.model.Post;

public interface Task3Service {

	String signUpUserWithUserNameAndPassword(String uname, String password);
	
	String addUserProfile(Long userId, String about, String location);
	
	String changePasswordUsingUsernameandLocation(String uname, String location, String password);
	
	String addPostToProfile(String content, Long profId);
	
	List<Post> getAllPostsById(Long id);
	
	List<customDTO> getCustomQuery();
}
