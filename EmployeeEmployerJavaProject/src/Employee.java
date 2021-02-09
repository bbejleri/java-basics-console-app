import java.util.Date;

public class Employee {

	private int id;

	private String name;

	private String surname;

	private Date birthday;

	private String password;

	private int yearsOfExperience;

	private boolean isEmployed;

	private int employmentRequestId;

	private int companyId;

	public Employee(int id, String name, String surname, Date birthday, String password, int yearsOfExperience, boolean isEmployed, int employmentRequestId, int companyId) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.password = password;
		this.yearsOfExperience = yearsOfExperience;
		this.isEmployed = isEmployed;
		this.employmentRequestId = employmentRequestId;
		this.companyId = companyId;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getPassword() {
		return password;
	}

	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public boolean isEmployed() {
		return isEmployed;
	}

	public int getEmploymentRequestId() {
		return employmentRequestId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public void setEmployed(boolean isEmployed) {
		this.isEmployed = isEmployed;
	}

	public void setEmploymentRequestId(int employmentRequestId) {
		this.employmentRequestId = employmentRequestId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
}
