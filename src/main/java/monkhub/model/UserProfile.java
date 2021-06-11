package monkhub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserProfile {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String userAbout;
	
	@Column
	private String userLocation;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAbout() {
		return userAbout;
	}

	public void setAbout(String about) {
		this.userAbout = about;
	}

	public String getLocation() {
		return userLocation;
	}

	public void setLocation(String location) {
		this.userLocation = location;
	}

	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", userAbout=" + userAbout + ", userLocation=" + userLocation + "]";
	}
	
	
}
