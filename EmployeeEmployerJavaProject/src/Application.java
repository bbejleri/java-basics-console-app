import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Application {

	public static void main(String[] args) throws IOException {
		String answer;
		String password;
		Scanner inputAdmin = new Scanner(System.in);
		System.out.println("Are you an employee / employer / admin?");
		answer = inputAdmin.nextLine();
		// Admin
		if (answer.equals("admin")) {
			System.out.println("Enter password for admin: ");
			password = inputAdmin.nextLine();
			if (password.equals("admin")) {
				String adminMenuAnswer;
				System.out.println("1. Add a new employee in file \"employee.txt\"");
				System.out.println("2. Add a new employer in file \"employer.txt\"");
				System.out.println("3. Display the company name which has added more job vacancies!");
				System.out.println("4. Enter the name of a company and display the names of all employees of this company!");
				while (inputAdmin.hasNext()) {
					adminMenuAnswer = inputAdmin.nextLine();
					if (Integer.parseInt(adminMenuAnswer) >= 1 && Integer.parseInt(adminMenuAnswer) <= 4) {
						if (Integer.parseInt(adminMenuAnswer) == 1) {
							final File file = readFile("employee.txt");
							PrintWriter output = new PrintWriter(new FileWriter(file, true));
							System.out.println("Writing on \"employee.txt\"...");
							System.out.println("Give the employee ID: ");
							String id = inputAdmin.next();
							System.out.println("Give the employee's name: ");
							String name = inputAdmin.next();
							System.out.println("Give the employee's surname: ");
							String surname = inputAdmin.next();
							System.out.println("Give the employee's birthday in dd/MM/yyyy form: ");
							String birthday = inputAdmin.next();
							System.out.println("Give the employee's password: ");
							String pw = inputAdmin.next();
							System.out.println("Give the employee's years of experience: ");
							String yearsOfExperience = inputAdmin.next();
							System.out.println("Is the employee employed (true/false): ");
							String isEmployed = inputAdmin.next();
							System.out.println("Give the employees the employment request id or null: ");
							String employmentRequestId = inputAdmin.next();
							System.out.println("Give the employer's company ID or null: ");
							String companyId = inputAdmin.next();
							output.println();
							output.print(id + " ");
							output.print(name + " ");
							output.print(surname + " ");
							output.print(birthday + " ");
							output.print(pw + " ");
							output.print(yearsOfExperience + " ");
							output.print(isEmployed + " ");
							output.print(employmentRequestId + " ");
							output.print(companyId + " ");
							output.close();
							System.out.println("Success.");
							System.exit(0);
						}
						if (Integer.parseInt(adminMenuAnswer) == 2) {
							final File file = readFile("employer.txt");
							PrintWriter output = new PrintWriter(new FileWriter(file, true));
							System.out.println("Writing on \"employer.txt\"...");
							System.out.println("Give the employer ID: ");
							String id = inputAdmin.next();
							System.out.println("Give the employer's name: ");
							String name = inputAdmin.next();
							System.out.println("Give the employer's surname: ");
							String surname = inputAdmin.next();
							System.out.println("Give the employer's birthday in dd/MM/yyyy form: ");
							String birthday = inputAdmin.next();
							System.out.println("Give the employer's password (Password should start with a number!): ");
							String pw = inputAdmin.next();
							System.out.println("Give the employer's company ID: ");
							String companyId = inputAdmin.next();
							System.out.println("Give the employer's company name: ");
							String companyName = inputAdmin.next();
							System.out.println("Give the employer's nr. of employees: ");
							String nrOfEmployees = inputAdmin.next();
							System.out.println("Give the industry: ");
							String industry = inputAdmin.next();
							if (Character.isDigit((pw.charAt(0)))) {
								output.println();
								output.print(id + " ");
								output.print(name + " ");
								output.print(surname + " ");
								output.print(birthday + " ");
								output.print(pw + " ");
								output.print(companyId + " ");
								output.print(companyName + " ");
								output.print(nrOfEmployees + " ");
								output.print(industry);
								output.close();
								System.out.println("Success.");
								System.exit(0);
							} else {
								System.out.println("Failed to write on file - password should start with a digit! ");
								System.exit(0);
							}
							output.close();
						} else if (Integer.parseInt(adminMenuAnswer) == 3) {
							System.out.println("Not Implemented");
						} else if (Integer.parseInt(adminMenuAnswer) == 4) {
							final List<Employer> employers = getListOfEmployersFromFile("employer.txt", inputAdmin);
							final List<Employee> employees = getListOfEmployeesFromFile("employee.txt", inputAdmin);
							inputAdmin = new Scanner(System.in);
							System.out.println("Give the company Name: ");
							String companyName = inputAdmin.nextLine();
							final List<Employee> employeesByCompanyName = getEmployeesByCompnayName(employees, employers, companyName);
							for (Employee e : employeesByCompanyName) {
								System.out.println(e.getName() + " " + e.getSurname());
							}
							System.exit(0);
						}
					} else {
						System.out.println("No option for the given input: " + adminMenuAnswer);
					}
				}
			} else {
				System.exit(0);
			}
			inputAdmin.close();
			// END Admin
			// Start Employer
		} else if (answer.equals("employer")) {
			Scanner inputEmployer = new Scanner(System.in);
			String employerPassowrd;
			int employerId;
			System.out.println("Enter password for employer: ");
			while (inputEmployer.hasNext()) {
				employerPassowrd = inputEmployer.nextLine();
				System.out.println("Enter ID for employer: ");
				employerId = inputEmployer.nextInt();
				final File file = readFile("employer.txt");
				inputEmployer = new Scanner(file);
				final List<Employer> employers = getListOfEmployersFromFile("employer.txt", inputEmployer);
				inputEmployer.close();
				Employer requestedEmployer = getEmployerById(employerId, employers);
				if (requestedEmployer != null) {
					if (requestedEmployer.getPassword().equals(employerPassowrd)) {
						inputEmployer = new Scanner(System.in);
						String employerMenuAnswer;
						System.out.println("1. Add a new vacancy in file \"vacancy.txt\" (the companyId should be the same as the logged in employer companyId).");
						System.out.println("2. View the list of all employees who are not employed.");
						System.out.println("3. View the list of all employees who are employed in his/her company.");
						System.out.println("4. View the list of all employees who have applied for a vacancy from his/her company.");
						System.out.println("5. Accept the application of an employee (the vacancy’s isVacancyOpen should be set as false,\r\n"
								+ "employee’s isEmployed should be set to true, employmentRequestId should be set to null and\r\n"
								+ "companyId should be set to the same value as the value of companyId in the accepted vacancy).");
						System.out.println("6. Terminate the program.");
						while (inputEmployer.hasNext()) {
							employerMenuAnswer = inputEmployer.next();
							if ((Integer.parseInt(employerMenuAnswer) >= 1) && (Integer.parseInt(employerMenuAnswer) <= 6)) {
								final File fileEmployees = readFile("employee.txt");
								inputEmployer = new Scanner(fileEmployees);
								final List<Employee> employees = getListOfEmployeesFromFile("employee.txt", inputEmployer);
								if (Integer.parseInt(employerMenuAnswer) == 1) {
									final File file2 = readFile("vacancies.txt");
									PrintWriter output = new PrintWriter(new FileWriter(file2, true));
									System.out.println("Writing on \"vacancies.txt\"...");
									inputEmployer = new Scanner(System.in);
									System.out.println("Give the vacancy ID: ");
									String vacancyId = inputEmployer.next();
									System.out.println("Give job description (Without blank spaces): ");
									String description = inputEmployer.next();
									System.out.println("Is vacancy open (true/false): ");
									String isVacancyOpen = inputEmployer.next();
									output.println();
									output.print(vacancyId + " ");
									output.print(requestedEmployer.getCompanyId() + " ");
									output.print(description + " ");
									output.print(isVacancyOpen);
									System.out.println("Success.");
									output.close();
								}
								if (Integer.parseInt(employerMenuAnswer) == 2) {
									final List<Employee> unemployeed = getUnemployeedEmployees(employees);
									for (Employee e : unemployeed) {
										System.out.println(e.getName() + " " + e.getSurname());
									}
									System.exit(0);
								}
								if (Integer.parseInt(employerMenuAnswer) == 3) {
									final List<Employee> sameCompany = getEmployeesByCompanyId(employees, requestedEmployer.getCompanyId());
									for (Employee e : sameCompany) {
										System.out.println(e.getName() + " " + e.getSurname());
									}
									System.exit(0);
								}
								if (Integer.parseInt(employerMenuAnswer) == 4) {
									final List<Employee> employeesWhoApplied = getEmployeesWhoAppliedForVacancy(employees);
									for (Employee e : employeesWhoApplied) {
										System.out.println(e.getName() + " " + e.getSurname());
									}
									System.exit(0);
								}
								if (Integer.parseInt(employerMenuAnswer) == 5) {
									System.out.println("Not implemented.");
								}
								if (Integer.parseInt(employerMenuAnswer) == 6) {
									System.exit(0);
								}
							} else {
								System.out.println("No option for the given input: " + employerMenuAnswer);
							}
						}
					} else {
						System.out.println("Incorrect password for employer with ID: " + employerId);
						System.exit(0);
					}
				} else {
					System.out.println("No employer with ID " + employerId + " found.");
					System.exit(0);
				}
			}
			inputEmployer.close();
			// END Employer
			// Start Employee
		} else if (answer.equals("employee")) {
			Scanner inputEmployee = new Scanner(System.in);
			String employeePassowrd;
			int employeeId;
			System.out.println("Enter password for employee: ");
			while (inputEmployee.hasNext()) {
				employeePassowrd = inputEmployee.nextLine();
				System.out.println("Enter ID for employee: ");
				employeeId = inputEmployee.nextInt();
				final File file = readFile("employee.txt");
				inputEmployee = new Scanner(file);
				final List<Employee> employees = getListOfEmployeesFromFile("employee.txt", inputEmployee);
				Employee requestedEmployee = getEmployeeById(employeeId, employees);
				if (requestedEmployee != null) {
					if (requestedEmployee.getPassword().equals(employeePassowrd)) {
						inputEmployee = new Scanner(System.in);
						String answer2;
						System.out.println("1. View the list of all job vacancies (only vacancies which are open).");
						System.out.println("2. Apply for a job vacancy (employmentRequestId should be set as the vacancyId).");
						while (inputEmployee.hasNext()) {
							answer2 = inputEmployee.next();
							if ((Integer.parseInt(answer2) == 1) || (Integer.parseInt(answer2) == 2)) {
								final List<Vacancy> vacancies = getListOfVacanciesFromFile("vacancies.txt", inputEmployee);
								if (Integer.parseInt(answer2) == 1) {
									for (Vacancy v : vacancies) {
										System.out.println(v.getVacancyId() + " " + v.getJobDescription());
									}
								} else if (Integer.parseInt(answer2) == 2) {
									inputEmployee = new Scanner(System.in);
									System.out.println("Enter vacancy id: ");
									String inputVacancyId = inputEmployee.next();
									final File file3 = readFile("employee.txt");
									PrintWriter output = new PrintWriter(file3);
									output.print(requestedEmployee.getId() + " ");
									output.print(requestedEmployee.getName() + " ");
									output.print(requestedEmployee.getSurname() + " ");
									output.print(convertDateToString(requestedEmployee.getBirthday()) + " ");
									output.print(requestedEmployee.getPassword() + " ");
									output.print(requestedEmployee.getYearsOfExperience() + " ");
									output.print(requestedEmployee.isEmployed() + " ");
									output.print(inputVacancyId + " ");
									output.print(requestedEmployee.getCompanyId() + " ");
									output.close();
									System.out.println("Success.");
								}
							} else {
								System.out.println("No option for the given input: " + answer2);
							}
						}
					} else {
						System.out.println("Incorrect password for employee with ID: " + employeeId);
						System.exit(0);
					}
				} else {
					System.out.println("No employee with ID " + employeeId + " found.");
					System.exit(0);
				}
			}
		}
		// END Employee
	}

	public static File readFile(final String fileName) {
		try {
			final File file = new File("src/", fileName);
			if (file.exists()) {
				return file;
			}
		} catch (Exception e) {
			System.out.println("File does not exist");
		}
		return null;
	}

	public static Date convertStringToDate(final String stringDate) {
		Date date;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(stringDate);
			return date;
		} catch (ParseException e) {
			System.out.println("Could not convert string to date.");
		}
		return null;
	}

	public static String convertDateToString(final Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String stringDate = formatter.format(date);
		return stringDate;
	}

	public static boolean isEmployeed(final String answer) {
		boolean isEmployeed = Boolean.FALSE;
		if (answer.equals("true")) {
			isEmployeed = Boolean.TRUE;
		}
		return isEmployeed;
	}

	public static boolean isVacancyOpen(final String answer) {
		boolean isVacancyOpen = Boolean.FALSE;
		if (answer.equals("true")) {
			isVacancyOpen = Boolean.TRUE;
		}
		return isVacancyOpen;
	}

	public static boolean employeeIdExists(final List<Employee> employees, final int inputId) {
		boolean exists = Boolean.FALSE;
		for (Employee e : employees) {
			if (e.getId() == inputId) {
				exists = Boolean.TRUE;
			}
		}
		return exists;
	}

	public static Employer getEmployerById(final int id, final List<Employer> employers) {
		Employer employer = null;
		for (Employer e : employers) {
			if (e.getId() == id) {
				return e;
			}
		}
		return employer;
	}

	public static Employee getEmployeeById(final int id, final List<Employee> employees) {
		Employee employee = null;
		for (Employee e : employees) {
			if (e.getId() == id) {
				return e;
			}
		}
		return employee;
	}

	public static List<Employee> getUnemployeedEmployees(final List<Employee> allEmployees) {
		final List<Employee> unemployeed = new ArrayList<Employee>();
		for (Employee e : allEmployees) {
			if (!e.isEmployed()) {
				unemployeed.add(e);
			}
		}
		return unemployeed;
	}

	public static Integer checkIntOrNull(final String input) {
		if (input.equals("null")) {
			return 0;
		} else {
			return Integer.parseInt(input);
		}
	}

	public static List<Employee> getEmployeesWhoAppliedForVacancy(final List<Employee> allEmployees) {
		final List<Employee> filteredEmployees = new ArrayList<Employee>();
		for (Employee e : allEmployees) {
			if (!e.isEmployed()) {
				if (e.getEmploymentRequestId() != 0 && e.getCompanyId() != 0) {
					if (e.getEmploymentRequestId() == e.getCompanyId()) {
						filteredEmployees.add(e);
					}
				}
			}
		}
		return filteredEmployees;
	}

	public static List<Employee> getEmployeesByCompanyId(final List<Employee> allEmployees, final int employerCompanyId) {
		final List<Employee> list = new ArrayList<Employee>();
		for (Employee e : allEmployees) {
			if (e.getCompanyId() == employerCompanyId) {
				list.add(e);
			}
		}
		return list;
	}

	public static List<Employee> getEmployeesByCompnayName(final List<Employee> allEmployees, final List<Employer> allEmployers, final String companyName) {
		final List<Employee> list = new ArrayList<Employee>();
		int companyId = 0;
		for (Employer employer : allEmployers) {
			if (employer.getCompanyName().equals(companyName)) {
				companyId = employer.getCompanyId();
			}
		}
		for (Employee employee : allEmployees) {
			if (employee.getCompanyId() == companyId) {
				list.add(employee);
			}
		}
		return list;
	}

	public static List<Employee> getListOfEmployeesFromFile(final String fileName, Scanner scanner) throws FileNotFoundException {
		final File file = readFile(fileName);
		scanner = new Scanner(file);
		final List<Employee> employees = new ArrayList<Employee>();
		while (scanner.hasNext()) {
			String id = scanner.next();
			String name = scanner.next();
			String surname = scanner.next();
			String birthday = scanner.next();
			String pw = scanner.next();
			String yearsOfExperience = scanner.next();
			String isEmployeed = scanner.next();
			String employmentRequestId = scanner.next();
			String companyId = scanner.next();
			Employee employee = new Employee(Integer.parseInt(id), name, surname, convertStringToDate(birthday), pw, Integer.parseInt(yearsOfExperience), isEmployeed(isEmployeed),
					checkIntOrNull(employmentRequestId), checkIntOrNull(companyId));
			employees.add(employee);
		}
		scanner.close();
		return employees;
	}

	public static List<Employer> getListOfEmployersFromFile(final String fileName, Scanner scanner) throws FileNotFoundException {
		final File file = readFile(fileName);
		scanner = new Scanner(file);
		final List<Employer> employers = new ArrayList<Employer>();
		while (scanner.hasNext()) {
			String id = scanner.next();
			String name = scanner.next();
			String surname = scanner.next();
			String birthday = scanner.next();
			String pw = scanner.next();
			String companyId = scanner.next();
			String companyName = scanner.next();
			String nrOfEmployees = scanner.next();
			String industry = scanner.next();
			Employer employer = new Employer(Integer.parseInt(id), name, surname, convertStringToDate(birthday), pw, Integer.parseInt(companyId), companyName,
					Integer.parseInt(nrOfEmployees), industry);
			employers.add(employer);
		}
		scanner.close();
		return employers;
	}

	public static List<Vacancy> getListOfVacanciesFromFile(final String fileName, Scanner scanner) throws FileNotFoundException {
		final File file = readFile(fileName);
		scanner = new Scanner(file);
		final List<Vacancy> vacancies = new ArrayList<Vacancy>();
		while (scanner.hasNext()) {
			String vacancyId = scanner.next();
			String companyId = scanner.next();
			String jobDescription = scanner.next();
			String isVacancyOpen = scanner.next();
			Vacancy vacancy = new Vacancy(Integer.parseInt(vacancyId), Integer.parseInt(companyId), jobDescription, isVacancyOpen(isVacancyOpen));
			vacancies.add(vacancy);
		}
		scanner.close();
		return vacancies;
	}
}
