package cascades;

import cascades.domain.Author;
import cascades.domain.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DriverApp {

    public static void main(String[] args) {
        HibernateOperations ho = new HibernateOperations();
        ho.saveAuthor();

        Book rur = ho.findBookById(2);
        Author capek = ho.queryForAuthorByName("K.Capek");

    }

    private void setUpAuthorsAndBooks() {
    }
}
