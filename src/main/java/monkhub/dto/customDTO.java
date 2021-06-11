package monkhub.dto;

public class customDTO {

	private Long id;
	private String about;
	private String location;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public customDTO(Long id, String about, String location) {
		super();
		this.id = id;
		this.about = about;
		this.location = location;
	}
	
	public customDTO() {
		super();
	}
	
}
