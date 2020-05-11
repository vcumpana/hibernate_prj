package com.hibernate;

import static com.hibernate.enums.Status.ACTIVE;

import com.hibernate.dao.AddressRepository;
import com.hibernate.dao.CompanyRepository;
import com.hibernate.dao.CountryRepository;
import com.hibernate.dao.EmployeeRepository;
import com.hibernate.dao.ProjectRepository;
import com.hibernate.dao.SkillsRepository;
import com.hibernate.entities.Address;
import com.hibernate.entities.Company;
import com.hibernate.entities.Country;
import com.hibernate.entities.Employee;
import com.hibernate.entities.Project;
import com.hibernate.entities.Skills;
import com.hibernate.enums.Role;
import com.hibernate.enums.SkillType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication(scanBasePackages = "com.hibernate.dao")
@RequiredArgsConstructor
public class Main {

  @Autowired
  private EntityManager entityManager;
  @Autowired
  private AddressRepository addressDao;
  @Autowired
  private CompanyRepository companyDao;
  @Autowired
  private CountryRepository countryDao;
  @Autowired
  private EmployeeRepository employeeDao;
  @Autowired
  private ProjectRepository projectDao;
  @Autowired
  private SkillsRepository skillsDao;


  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @EventListener(ApplicationStartedEvent.class)
  @Transactional
  public void runOnAppStart() {


    Query query = entityManager.createNativeQuery("select e.* from employee e join employee_projects ep on e.id = ep.employee_id" +
        " join project p on ep.project_id=p.id where p.project_code =:code")
        .setParameter("code", "PRJ145");

    entityManager.createQuery("from Employee e join fetch e.projects p where p.projectCode =:code", Employee.class)
        .setParameter("code", "PRJ145");


    List<Object[]> array = query.getResultList();


    List<Country> countries = countryFactory();
    countryDao.saveAll(countries);
    List<Skills> skills = skillsFactory();
    skillsDao.saveAll(skills);
    List<Address> addresses = addressesFactory(countries);
    //addressDao.saveAll(addresses);
    List<Employee> employees = employeesFactory(addresses, skills);
    employeeDao.saveAll(employees);
    List<Project> projects = projectsFactory(employees);
    projectDao.saveAll(projects);
    List<Company> companies = companiesFactory(projects, employees);
    companyDao.saveAll(companies);

    printSeparator();
    List<Employee> list = projectDao.findEmployeesWithProjectCode("PRJ_9879");
    System.out.println("Employee on project PRJ_9879");
    for (Employee e : list) {
      System.out.println(e);
    }
    printSeparator();
    List<Project> prList = companyDao.getProjectsByCompanyId(31L);
    System.out.println("Projects of company with id = 31");
    for (Project e : prList) {
      System.out.println(e.getName());
    }

    printSeparator();
    List<Employee> empBySkill = employeeDao.getEmployeesBySkillType(SkillType.SOFT);
    System.out.println("Employee with soft skills");
    for (Employee e : empBySkill) {
      System.out.println(e);
    }

    printSeparator();
    List<Employee> empBySkillName = employeeDao.getEmployeesBySkillName("presentation");
    System.out.println("Employee with presentation skills");
    for (Employee e : empBySkillName) {
      System.out.println(e);
    }

    printSeparator();
    Employee employee = employeeDao.findById(8888L).get();
    System.out.println(employee);
    employee.setFirstName("Updated");
    employee.setLastName("Updated");
    employeeDao.save(employee);
    Employee updatedEmployee = employeeDao.findById(9L).get();
    System.out.println(updatedEmployee);

    printSeparator();
    List<Project> prListBySkills = projectDao.getProjectsByJavaSkilledEmployees();
    System.out.println("Projects with 4 java skilled employees");
    for (Project e : prListBySkills) {
      System.out.println(e.getName() + " " + e.getProjectCode());
    }
    printSeparator();
//
//        List<Employee> allEmployees = employeeDao.findAll();
//        for (Employee e : allEmployees)
//            System.out.println(e.getFirstName() + " " + e.getLastName() + " " + e.getStatus());
//        for (int i = 0; i < 3; i++) {
//            employeeDao.delete(allEmployees.get(i));
//        }

    List<Employee> allActiveAndInactiveEmployees = employeeDao.findAll();
    for (Employee e : allActiveAndInactiveEmployees) {
      System.out.println(e.getFirstName() + " " + e.getLastName() + " " + e.getStatus());
    }
    printSeparator();

    List<Project> allProjects = projectDao.findAll();
    for (Project e : allProjects) {
      System.out.println(e.getName() + " " + e.getStatus());
    }
    for (int i = 0; i < 2; i++) {
      projectDao.delete(allProjects.get(i));
    }

    List<Project> allActiveAndInactiveProjects = projectDao.findAll();
    for (Project e : allActiveAndInactiveProjects) {
      System.out.println(e.getName() + " " + e.getStatus());
    }
    printSeparator();

//        System.out.println(dao.countCompanyEmployees("Endava"));
//
//        System.out.println(dao.getEmployeeById(25));
    //HibernateUtils.getSessionFactory().close();
  }

  private static List<Company> companiesFactory(List<Project> projects, List<Employee> employees) {
    List<Company> list = new ArrayList<>();
    list.add(new Company("Endava", new HashSet<>(projects), new HashSet<>(employees)));
    return list;
  }

  private static List<Project> projectsFactory(List<Employee> employees) {
    List<Project> list = new ArrayList<>();
    list.add(new Project("PRJ_9879", "Online Shop", "Online book and gadgets shop"
        , new HashSet<Employee>(Arrays.asList(employees.get(0), employees.get(1))), ACTIVE));
    list.add(new Project("PRJ_6578", "Travel App", "Mobile app for travelers"
        , new HashSet<Employee>(Arrays.asList(employees.get(1), employees.get(2), employees.get(3), employees.get(4))), ACTIVE));
    list.add(new Project("PRJ_4546", "Video Channel", "Video channel for it enthusiasts"
        , new HashSet<Employee>(Arrays.asList(employees.get(5))), ACTIVE));
    list.add(new Project("PRJ_2654", "Antivirus", "Antivirus program"
        , new HashSet<Employee>(Arrays.asList(employees.get(6), employees.get(7))), ACTIVE));
    return list;

  }

  private static List<Employee> employeesFactory(List<Address> addresses, List<Skills> skills) {
    List<Employee> list = new ArrayList<>();
    list.add(new Employee("icarag", "Ion", "Caragiale"
        , new HashSet<>(Arrays.asList(skills.get(0), skills.get(3))), addresses.get(0), Role.DEVELOPER, ACTIVE));
    list.add(new Employee("mbatr", "Mircea", "Batrincea"
        , new HashSet<>(Arrays.asList(skills.get(1), skills.get(3), skills.get(4))), addresses.get(2), Role.TESTER, ACTIVE));
    list.add(new Employee("pmitru", "Pavel", "Mitru"
        , new HashSet<>(Arrays.asList(skills.get(2), skills.get(4))), addresses.get(3), Role.ANALYST, ACTIVE));
    list.add(new Employee("bmitri", "Bogdan", "Mitriuc"
        , new HashSet<>(Arrays.asList(skills.get(1), skills.get(3), skills.get(4))), addresses.get(3), Role.SCRUMMASTER, ACTIVE));
    list.add(new Employee("solesh", "Svyatoslav", "Oleshko"
        , new HashSet<>(Arrays.asList(skills.get(0), skills.get(2), skills.get(4))), addresses.get(4), Role.PROJECTMANAGER, ACTIVE));
    list.add(new Employee("rsribn", "Roman", "Sribnyy"
        , new HashSet<>(Arrays.asList(skills.get(0), skills.get(1))), addresses.get(5), Role.TESTER, ACTIVE));
    list.add(new Employee("aivan", "Alexey", "Ivanov"
        , new HashSet<>(Arrays.asList(skills.get(0))), addresses.get(6), Role.DEVELOPER, ACTIVE));
    list.add(new Employee("ianast", "Ivan", "Anastasiev"
        , new HashSet<>(Arrays.asList(skills.get(2), skills.get(3))), addresses.get(7), Role.ANALYST, ACTIVE));
    list.add(new Employee("bkagu", "Boris", "Kagudin"
        , new HashSet<>(Arrays.asList(skills.get(0), skills.get(3), skills.get(1), skills.get(2))), addresses.get(8), Role.LEAD, ACTIVE));
    return list;
  }

  private static List<Address> addressesFactory(List<Country> countries) {
    List<Address> list = new ArrayList<>();
    list.add(new Address(countries.get(0), "Iasi", "Bvd Carol I", "45"));
    list.add(new Address(countries.get(0), "Iasi", "Sararie", "105"));
    list.add(new Address(countries.get(0), "Bucuresti", "Stefan cel Mare", "12"));
    list.add(new Address(countries.get(1), "Kiev", "Bogdan Mazepa", "56"));
    list.add(new Address(countries.get(1), "Kiev", "Obolonskoe shosse", "19"));
    list.add(new Address(countries.get(1), "Lviv", "Bogdan Mazepa", "56"));
    list.add(new Address(countries.get(2), "Moskva", "Sadovoe Koltso", "6"));
    list.add(new Address(countries.get(2), "Sankt-Petersburg", "Vasilievskii Spusk", "78"));
    list.add(new Address(countries.get(2), "Moskva", "Stroiteley", "46"));
    return list;
  }

//    public static void insertEmployee(Country employee) {
//        Session session = HibernateUtils.getSessionFactory().openSession();
//        Transaction t = session.beginTransaction();
//        t.begin();
//        session.save(employee);
//        t.commit();
//        session.close();
//    }

  public static List<Country> countryFactory() {
    List<Country> list = new ArrayList<>();
    list.add(new Country("RO", "Romania"));
    list.add(new Country("UA", "Ukraine"));
    list.add(new Country("RU", "Russia"));
    return list;
  }

  public static List<Skills> skillsFactory() {
    List<Skills> list = new ArrayList<>();
    list.add(new Skills(SkillType.SOFT, "presentation"));
    list.add(new Skills(SkillType.SOFT, "english"));
    list.add(new Skills(SkillType.TECHNICAL, "testing"));
    list.add(new Skills(SkillType.TECHNICAL, "programming"));
    list.add(new Skills(SkillType.TECHNICAL, "java"));

    return list;
  }

  public static void printSeparator() {
    System.out.println("----------------------------------------------------------------");
  }

//    public static void main(String[] args) {
//
//        SpringApplication.run(Main.class, args);
//
//        List<Country> countries = countryFactory();
//        dao.toDatabase(countries);
//        List<Skills> skills = skillsFactory();
//        dao.toDatabase(skills);
//        List<Address> addresses = addressesFactory(countries);
//        addressDao.saveAll(addresses);
//        List<Employee> employees = employeesFactory(addresses, skills);
//        dao.toDatabase(employees);
//        List<Project> projects = projectsFactory(employees);
//        dao.toDatabase(projects);
//        List<Company> companies = companiesFactory(projects, employees);
//        dao.toDatabase(companies);
//
//        printSeparator();
//        List<Employee> list= dao.getEmployeesByProjectCode("PRJ_9879");
//        System.out.println("Employee on project PRJ_9879");
//        for (Employee e : list){
//            System.out.println(e);
//        }
//        printSeparator();
//        List<Project> prList = dao.getProjectsByCompanyId(31);
//        System.out.println("Projects of company with id = 31");
//        for (Project e : prList)
//            System.out.println(e.getName());
//
//        printSeparator();
//        List<Employee> empBySkill= dao.getEmployeesBySkillType(SkillType.SOFT);
//        System.out.println("Employee with soft skills");
//        for (Employee e : empBySkill){
//            System.out.println(e);
//        }
//
//        printSeparator();
//        List<Employee> empBySkillName= dao.getEmployeesBySkillName("presentation");
//        System.out.println("Employee with presentation skills");
//        for (Employee e : empBySkillName){
//            System.out.println(e);
//        }
//
//        printSeparator();
//        Employee employee = dao.getEmployeeById(19).get(0);
//        System.out.println(employee);
//        employee.setFirstName("Updated");
//        employee.setLastName("Updated");
//        dao.updateEmployee(employee);
//        Employee updatedEmployee = dao.getEmployeeById(19).get(0);
//        System.out.println(updatedEmployee);
//
//        printSeparator();
//        List<Project> prListBySkills = dao.getProjectsByJavaSkilledEmployees();
//        System.out.println("Projects with 4 java skilled employees");
//        for (Project e : prListBySkills)
//            System.out.println(e.getName() + " " + e.getProjectCode());
//        printSeparator();
//
//        List<Employee> allEmployees = dao.allFomDatabase(new Employee());
//        for (Employee e: allEmployees)
//            System.out.println(e.getFirstName() + " " + e.getLastName() + " " + e.getStatus());
//        for(int i = 0; i < 3; i++){
//            dao.deleteObject(allEmployees.get(i));
//        }
//
//        List<Employee> allActiveAndInactiveEmployees = dao.allFomDatabase(new Employee());
//        for (Employee e: allActiveAndInactiveEmployees)
//            System.out.println(e.getFirstName() + " " + e.getLastName() + " " + e.getStatus());
//        printSeparator();
//
//        List<Project> allProjects = dao.allFomDatabase(new Project());
//        for (Project e: allProjects)
//            System.out.println(e.getName() + " " + e.getStatus());
//        for(int i = 0; i < 2; i++){
//            dao.deleteObject(allProjects.get(i));
//        }
//
//        List<Project> allActiveAndInactiveProjects = dao.allFomDatabase(new Project());
//        for (Project e: allActiveAndInactiveProjects)
//            System.out.println(e.getName() + " " + e.getStatus());
//        printSeparator();
//
////        System.out.println(dao.countCompanyEmployees("Endava"));
////
////        System.out.println(dao.getEmployeeById(25));
//        //HibernateUtils.getSessionFactory().close();
//    }
}
