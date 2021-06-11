package monkhub.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import monkhub.dto.customDTO;
//addscaler
@Repository
public class PostRepoCustomImpl implements PostRepoCustom {

	@PersistenceContext
	EntityManager entityManager;
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<customDTO> findByCustom() {
		//Query query = entityManager.createNativeQuery("select user_prof_id  from post group by user_prof_id having count(user_prof_id) > 2");
		Query query = entityManager.createNativeQuery("select user_profile.id, user_about, user_location from"
			     + " user_profile left join post on user_profile.id = post.user_prof_id group by "
			     + "user_prof_id having count(user_prof_id) > 2");
//		query.unwrap(SQLQuery.class).addScalar("user_profile.id",LongType.INSTANCE)
//		                            .addScalar("user_about", StringType.INSTANCE)
//		                            .addScalar("user_location", StringType.INSTANCE);
		List<customDTO> resultList = query.getResultList();
		return resultList;
	}


}
