import java.util.Date;

public class Employer {

	private int id;

	private String name;

	private String surname;

	private Date birthday;

	private String password;

	private int companyId;

	private String companyName;

	private int nrOfEmployees;

	private String industry;

	public Employer(int id, String name, String surname, Date birthday, String password, int companyId, String companyName, int nrOfEmployees, String industry) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.password = password;
		this.companyId = companyId;
		this.companyName = companyName;
		this.nrOfEmployees = nrOfEmployees;
		this.industry = industry;
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

	public int getCompanyId() {
		return companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public int getNrOfEmployees() {
		return nrOfEmployees;
	}

	public String getIndustry() {
		return industry;
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

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setNrOfEmployees(int nrOfEmployees) {
		this.nrOfEmployees = nrOfEmployees;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	@Override
	public String toString() {
		return "Employer [id=" + id + ", name=" + name + ", surname=" + surname + ", birthday=" + birthday + ", password=" + password + ", companyId=" + companyId + ", companyName="
				+ companyName + ", nrOfEmployees=" + nrOfEmployees + ", industry=" + industry + "]";
	}
}
