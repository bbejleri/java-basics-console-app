public class Vacancy {

	private int vacancyId;

	private int companyId;

	private String jobDescription;

	private boolean isVacancyOpen;

	public Vacancy(int vacancyId, int companyId, String jobDescription, boolean isVacancyOpen) {
		super();
		this.vacancyId = vacancyId;
		this.companyId = companyId;
		this.jobDescription = jobDescription;
		this.isVacancyOpen = isVacancyOpen;
	}

	public int getVacancyId() {
		return vacancyId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public boolean isVacancyOpen() {
		return isVacancyOpen;
	}

	public void setVacancyId(int vacancyId) {
		this.vacancyId = vacancyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public void setVacancyOpen(boolean isVacancyOpen) {
		this.isVacancyOpen = isVacancyOpen;
	}

	@Override
	public String toString() {
		return "Vacancy [vacancyId=" + vacancyId + ", companyId=" + companyId + ", jobDescription=" + jobDescription + ", isVacancyOpen=" + isVacancyOpen + "]";
	}
}
