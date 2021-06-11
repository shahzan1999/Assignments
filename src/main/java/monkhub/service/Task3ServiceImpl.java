package monkhub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import monkhub.dto.customDTO;
import monkhub.model.Post;
import monkhub.model.User;
import monkhub.model.UserProfile;
import monkhub.repository.PostRepo;
import monkhub.repository.PostRepoCustom;
import monkhub.repository.UserProfileRepo;
import monkhub.repository.UserRepo;

@Service
public class Task3ServiceImpl implements Task3Service {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserProfileRepo userProfileRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private PostRepoCustom postRepoCustom;
	
	@Override
	public String signUpUserWithUserNameAndPassword(String uname, String password) {
		//creating a user object
		User user = new User();
		// adding user fields its value
		user.setUserName(uname);
		user.setPassword(password);
		// saving user object in database using jpa repo of user
		userRepo.save(user);
		return "ok";
	}

	@Override
	public String addUserProfile(Long id, String about, String location) {
		
		  //first check whether user exists
				if(userRepo.existsById(id)) {
					
					//creating a userProfile object
					UserProfile userProfile = new UserProfile();
					// adding userProfile fields its value
					userProfile.setAbout(about);
					userProfile.setLocation(location);
					// saving userProfile object in database using jparepo of userProfile
					userProfileRepo.save(userProfile);
					
					// add the  newly created profile to user 
					User user = userRepo.findById(id).orElse(null);
					user.setUserProfile(userProfile);
					userRepo.save(user);
					
					return "user profile added";
				}
		
		return "user does not exist";
	}

	@Override
	public String changePasswordUsingUsernameandLocation(String uname, String location, String password) {
		
		User user = userRepo.findByUserName(uname);
		if(location.equals(user.getUserProfile().getLocation())) {
			user.setPassword(password);
			userRepo.save(user);
			return "password changed";
		} 
		return "invalid request";
	}

	@Override
	public String addPostToProfile(String content, Long id) {
		//first check whether userprofile exists
		if(userProfileRepo.existsById(id)) {
			
			//creating a Post object
			Post post = new Post();
			// adding Post fields its value
			post.setContent(content);
			post.setUserProfile(userProfileRepo.findById(id).orElse(null));
			// saving post object in database using postrepo 
			postRepo.save(post);
			
			return "user post added";
		}
    return "userprofile does not exist";
    
	}

	@Override
	public List<Post> getAllPostsById(Long id) {
//		UserProfile up = userProfileRepo.findById(id).orElse(null);
//		return postRepo.findByUserProfileOrderByDesc(up);
		return postRepo.findByUserProfId(id);
		
	}

	@Override
	public List<customDTO> getCustomQuery() {
		 return postRepoCustom.findByCustom();
	}

}
