package dao;

import entities.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtils;

import java.util.List;

public interface  ToDatabase <E> {

    default void toDatabase(List<E> list){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session s = null;
        Transaction t = null;

        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            for (E c : list)
                s.save(c);
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }

    }
}
