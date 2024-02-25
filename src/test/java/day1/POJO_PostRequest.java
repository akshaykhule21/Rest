package day1;

//Plain old java object class
public class POJO_PostRequest {

	String name;
	String location;
	String phone;
	String courses[];
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String[] getcourses() {
		return courses;
	}
	public void setcourses(String[] courses) {
		this.courses = courses;
	}
}
