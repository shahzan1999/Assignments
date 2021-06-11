package monkhub.repository;

import java.util.List;

import monkhub.dto.customDTO;

public interface PostRepoCustom {

	List<customDTO> findByCustom();
	
}
