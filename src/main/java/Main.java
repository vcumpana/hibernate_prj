import dao.DaoImplementaion;
import entities.*;
import enums.Role;
import enums.SkillType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtils;

import java.util.*;

import static enums.Status.*;

public class Main {

    private static DaoImplementaion dao = new DaoImplementaion();

    public static void main(String[] args) {

        List<Country> countries = countryFactory();
        dao.toDatabase(countries);
        List<Skills> skills = skillsFactory();
        dao.toDatabase(skills);
        List<Address> addresses = addressesFactory(countries);
        dao.toDatabase(addresses);
        List<Employee> employees = employeesFactory(addresses, skills);
        dao.toDatabase(employees);
        List<Project> projects = projectsFactory(employees);
        dao.toDatabase(projects);
        List<Company> companies = companiesFactory(projects, employees);
        dao.toDatabase(companies);

        printSeparator();
        List<Employee> list= dao.getEmployeesByProjectCode("PRJ_9879");
        System.out.println("Employee on project PRJ_9879");
        for (Employee e : list){
            System.out.println(e);
        }
        printSeparator();
        List<Project> prList = dao.getProjectsByCompanyId(30);
        System.out.println("Projects of company with id = 30");
        for (Project e : prList)
            System.out.println(e.getName());

        printSeparator();
        List<Employee> empBySkill= dao.getEmployeesBySkillType(SkillType.SOFT);
        System.out.println("Employee with soft skills");
        for (Employee e : empBySkill){
            System.out.println(e);
        }

        printSeparator();
        List<Employee> empBySkillName= dao.getEmployeesBySkillName("presentation");
        System.out.println("Employee with presentation skills");
        for (Employee e : empBySkillName){
            System.out.println(e);
        }

        printSeparator();
        Employee employee = dao.getEmployeeById(19).get(0);
        System.out.println(employee);
        employee.setFirstName("Updated");
        employee.setLastName("Updated");
        dao.updateEmployee(employee);
        Employee updatedEmployee = dao.getEmployeeById(19).get(0);
        System.out.println(updatedEmployee);

        printSeparator();
        List<Project> prListBySkills = dao.getProjectsByJavaSkilledEmployees();
        System.out.println("Projects with 4 java skilled employees");
        for (Project e : prListBySkills)
            System.out.println(e.getName() + " " + e.getProjectCode());
        printSeparator();

        List<Employee> allEmployees = dao.allFomDatabase(new Employee());
        for (Employee e: allEmployees)
            System.out.println(e.getFirstName() + " " + e.getLastName() + " " + e.getStatus());
        for(int i = 0; i < 3; i++){
            dao.deleteObject(allEmployees.get(i));
        }

        List<Employee> allActiveAndInactiveEmployees = dao.allFomDatabase(new Employee());
        for (Employee e: allActiveAndInactiveEmployees)
            System.out.println(e.getFirstName() + " " + e.getLastName() + " " + e.getStatus());
        printSeparator();

        List<Project> allProjects = dao.allFomDatabase(new Project());
        for (Project e: allProjects)
            System.out.println(e.getName() + " " + e.getStatus());
        for(int i = 0; i < 2; i++){
            dao.deleteObject(allProjects.get(i));
        }

        List<Project> allActiveAndInactiveProjects = dao.allFomDatabase(new Project());
        for (Project e: allActiveAndInactiveProjects)
            System.out.println(e.getName() + " " + e.getStatus());
        printSeparator();

    }

    private static List<Company> companiesFactory(List<Project> projects, List<Employee> employees) {
        List<Company> list = new ArrayList<>();
        list.add(new Company("Endava", new HashSet<>(projects), new HashSet<>(employees)));
        return list;
    }

    private static List<Project> projectsFactory(List<Employee> employees) {
        List<Project> list = new ArrayList<>();
        list.add(new Project("PRJ_9879", "Online Shop", "Online book and gadgets shop"
                , new HashSet<Employee>(Arrays.asList(employees.get(0), employees.get(1))),ACTIVE ));
        list.add(new Project("PRJ_6578", "Travel App", "Mobile app for travelers"
                , new HashSet<Employee>(Arrays.asList(employees.get(1), employees.get(2), employees.get(3), employees.get(4))), ACTIVE ));
        list.add(new Project("PRJ_4546", "Video Channel", "Video channel for it enthusiasts"
                , new HashSet<Employee>(Arrays.asList(employees.get(5))), ACTIVE));
        list.add(new Project("PRJ_2654", "Antivirus", "Antivirus program"
                , new HashSet<Employee>(Arrays.asList(employees.get(6), employees.get(7))), ACTIVE));
        return list;

    }

    private static List<Employee> employeesFactory(List<Address> addresses, List<Skills> skills) {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("icarag","Ion","Caragiale"
                , new HashSet<>(Arrays.asList(skills.get(0), skills.get(3))), addresses.get(0),Role.DEVELOPER, ACTIVE));
        list.add(new Employee("mbatr","Mircea","Batrincea"
                , new HashSet<>(Arrays.asList(skills.get(1), skills.get(3), skills.get(4))), addresses.get(2),Role.TESTER, ACTIVE));
        list.add(new Employee("pmitru","Pavel","Mitru"
                , new HashSet<>(Arrays.asList(skills.get(2),skills.get(4))), addresses.get(3),Role.ANALYST, ACTIVE));
        list.add(new Employee("bmitri","Bogdan","Mitriuc"
                , new HashSet<>(Arrays.asList(skills.get(1), skills.get(3), skills.get(4))), addresses.get(3),Role.SCRUMMASTER, ACTIVE));
        list.add(new Employee("solesh","Svyatoslav","Oleshko"
                , new HashSet<>(Arrays.asList(skills.get(0), skills.get(2), skills.get(4))), addresses.get(4),Role.PROJECTMANAGER, ACTIVE));
        list.add(new Employee("rsribn","Roman","Sribnyy"
                , new HashSet<>(Arrays.asList(skills.get(0), skills.get(1))), addresses.get(5),Role.TESTER, ACTIVE));
        list.add(new Employee("aivan","Alexey","Ivanov"
                , new HashSet<>(Arrays.asList(skills.get(0))), addresses.get(6),Role.DEVELOPER, ACTIVE));
        list.add(new Employee("ianast","Ivan","Anastasiev"
                , new HashSet<>(Arrays.asList(skills.get(2), skills.get(3))), addresses.get(7),Role.ANALYST, ACTIVE));
        list.add(new Employee("bkagu","Boris","Kagudin"
                , new HashSet<>(Arrays.asList(skills.get(0), skills.get(3), skills.get(1), skills.get(2))), addresses.get(8),Role.LEAD, ACTIVE));
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

    public static void insertEmployee(Country employee){
        Session session  = HibernateUtils.getSessionFactory().openSession();
        Transaction t=session.beginTransaction();
        t.begin();
        session.save(employee);
        t.commit();
        session.close();
    }

    public static List<Country> countryFactory(){
        List<Country> list = new ArrayList<>();
        list.add(new Country("RO","Romania"));
        list.add(new Country("UA","Ukraine"));
        list.add(new Country("RU","Russia"));
        return list;
    }

    public static List<Skills> skillsFactory(){
        List<Skills> list = new ArrayList<>();
        list.add(new Skills(SkillType.SOFT, "presentation" ));
        list.add(new Skills(SkillType.SOFT, "english" ));
        list.add(new Skills(SkillType.TECHNICAL, "testing" ));
        list.add(new Skills(SkillType.TECHNICAL, "programming" ));
        list.add(new Skills(SkillType.TECHNICAL, "java" ));

        return list;
    }

    public static void printSeparator(){
        System.out.println("----------------------------------------------------------------");
    }
}
