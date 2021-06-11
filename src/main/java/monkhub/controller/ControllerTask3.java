package monkhub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import monkhub.dto.customDTO;
import monkhub.model.Post;
import monkhub.service.Task3Service;

@RestController
public class ControllerTask3 {

	@Autowired
	private Task3Service t3Service;
	
	//signup
	@PostMapping("/user/signup")
	public String userSignUp(@RequestParam("username") String userName, 
			                 @RequestParam("password") String password ) {
		
		return t3Service.signUpUserWithUserNameAndPassword(userName, password);
		
	}
	
	//signin
	
	// creating userprofile using userId
	@PostMapping("user/addprofile/{userId}")
	public String addUserProfile(@RequestParam("userabout") String userAbout,
			                     @RequestParam("userlocation") String userLocation,
			                     @PathVariable Long userId) {
		
		return t3Service.addUserProfile(userId, userAbout, userLocation);
	}
	
	
	//update passsword
    //taking username as input and user location for validatiing
	@PostMapping("user/changepassword")
	public String changeUserPassword(@RequestParam("username") String userName,
			                          @RequestParam("userlocation") String userLocation,
			                          @RequestParam("password") String newPassword) {
		
		
		return t3Service.changePasswordUsingUsernameandLocation(userName, userLocation, newPassword) ;
	}
	
	//update profile
	
	//add post to user profile
	@PostMapping("/user/addpost/{profileId}")
	public String addPost(@RequestParam("content") String content, @PathVariable Long profileId) {
		return t3Service.addPostToProfile(content, profileId);
	}
	
	//find all post of a user order by id desc
	@GetMapping("/user/allpost/{userProfileId}")
	public List<Post> getAllPostOfUserProfile(@PathVariable Long userProfileId) {
		return t3Service.getAllPostsById(userProfileId);
	}
	
	@GetMapping("/user/custom")
	public List<customDTO> getCustom() {
		return t3Service.getCustomQuery();
	}
	
	
}
