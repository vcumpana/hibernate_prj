import entities.Address;
import entities.Country;
import entities.Employee;
import entities.Skills;
import enums.Role;
import enums.SkillType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class Main {
  // public static SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public static void main(String[] args) {

       // Session session = HibernateUtils.getSessionFactory().openSession();
        Employee employee = new Employee();
        employee.setUserid("oiouo6546");
        employee.setFirstName("Michael");
        employee.setLastName("Jakcson");
        Skills skill1 = new Skills(SkillType.SOFT, "good english");
        Skills skill2 = new Skills(SkillType.SOFT, "good english");
        employee.setSkills(new HashSet<Skills>(Arrays.asList(skill1, skill2)));
        Country country = new Country("MD", "Moldova");
        Address address = new Address(country,"Chisinau", "Florilor", "40");
        employee.setAddress(address);
        employee.setRole(Role.ANALYST);

        insertEmployee(country);


     //   session.close();

    }

    public static void insertEmployee(Country employee){
        Session session  = HibernateUtils.getSessionFactory().openSession();
        Transaction t=session.beginTransaction();
        t.begin();
        session.save(employee);
        t.commit();
        session.close();
    }
}
