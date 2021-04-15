package cascades;

import cascades.domain.Author;
import cascades.domain.Book;
//import com.sun.org.apache.xpath.internal.operations.String;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;

public class HibernateOperations {

    private static final EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("cascades.domain.bookshelf");
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void saveAuthor(Author author) {
        EntityManager em = HibernateOperations.getEntityManager();
        em.getTransaction()
                .begin();
        em.persist(author);
        em.getTransaction()
                .commit();

    }

    public Book findBookById(int id){
        EntityManager em = HibernateOperations.getEntityManager();
        Book book =  em.find(Book.class,id);
        return book;
    }

    public Author queryForAuthorByName(java.lang.String queriedName) {
        EntityManager em = HibernateOperations.getEntityManager();
        Author author = (Author) em.createQuery("SELECT author FROM Author author where author.name=?1")
                .setParameter(1, queriedName)
                .getSingleResult();
        return author;
    }
}
