package dao;

import entities.Country;
import entities.Skills;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class DaoImplementaion implements ToDatabase {

    private static SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

}
