package dao;

import entities.Country;
import entities.Employee;
import entities.Project;
import entities.Skills;
import enums.SkillType;
import org.hibernate.*;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class DaoImplementaion implements ToDatabase {

    private static SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public List<Employee> getEmployeesByProjectCode(String projectCode){
        Session s = null;
        Transaction t = null;
        List<Employee> list = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            Query query  = s.createQuery("select p.employees from Project p WHERE p.projectCode=:projectCode");
            query.setString("projectCode", projectCode);
            list = query.list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
        return list;
    }

    public List<Project> getProjectsByCompanyId(int id){
        Session s = null;
        Transaction t = null;
        List<Project> list = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            Query query  = s.createQuery("select c.projects from Company  c where c.id =" + id);
            list = query.list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
        return list;
    }

    public List<Employee> getEmployeesBySkillType(SkillType skillType){
        Session s = null;
        Transaction t = null;
        List<Employee> list = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            Query query  = s.createQuery("from Employee e join fetch e.skills s where s.type =:skillType");
            query.setString("skillType", skillType.name());
            list = query.list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
        return list;
    }

    public List<Employee> getEmployeesBySkillName(String skillName){
        Session s = null;
        Transaction t = null;
        List<Employee> list = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            Query query  = s.createQuery("from Employee e join fetch e.skills s where s.skill =:skillName");
            query.setString("skillName", skillName);
            list = query.list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
        return list;
    }

    public List<Employee> getEmployeeById(int id){
        Session s = null;
        Transaction t = null;
        List<Employee> list = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            Query query  = s.createQuery("from Employee where id =:id");
            query.setInteger("id", id);
            list = query.list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
        return list;
    }

    public void updateEmployee(Employee employee){
        Session s = null;
        Transaction t = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
          s.update(employee);
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
    }

    public List<Project> getProjectsByJavaSkilledEmployees(){
        Session s = null;
        Transaction t = null;
        List<Project> list = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            Query query  = s.createQuery("select p from Project p join p.employees e join e.skills s where s.skill = 'java' group by p.id having count (p.id) =4");
            list = query.list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
        return list;
    }




}
