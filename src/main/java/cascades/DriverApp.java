package cascades;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DriverApp {

    public static void main(String[] args) {

     /*   SessionFactory sessionFactory;
        Session session;
        Transaction transaction;

        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();*/

        HibernateOperations ho = new HibernateOperations();
        ho.saveAuthor();


    }

    private void setUpAuthorsAndBooks() {
    }
}
