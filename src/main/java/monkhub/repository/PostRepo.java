package monkhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import monkhub.model.Post;
import monkhub.model.UserProfile;

public interface PostRepo extends JpaRepository<Post, Long> {

    @Query(value="select * from post where user_prof_id = ?1 order by id desc ", nativeQuery=true)	
	List<Post> findByUserProfId(Long id);
    
//    @Query(value="select p from Post p where p.UserProfile = ?1")	
//    List<Post> findAllPostByUserProfile(UserProfile userProfile);
    
//    @Query(value="select * from Post  where user_prof_id = ?1")	
//    List<Post> findAllPostByUserProfile(Long id);
    
    
    List<Post> findByUserProfile(UserProfile userProfile);
    

    
//    @Query(value="select user_prof_id  from post "
//    		   + "group by user_prof_id "
//    		   + "having count(user_prof_id) > 2")
//    List<Long> findCustom(); 
}
