package cascades;

import cascades.domain.Author;
import cascades.domain.Book;
//import com.sun.org.apache.xpath.internal.operations.String;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

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

    public Book findBookById(int id) {
        EntityManager em = HibernateOperations.getEntityManager();
        Book book = em.find(Book.class, id);
        return book;
    }

    public Author queryForAuthorByName(String queriedName) {
        EntityManager em = HibernateOperations.getEntityManager();
        Author author = (Author) em.createQuery("SELECT author FROM Author author where author.name=?1")
                .setParameter(1, queriedName)
                .getSingleResult();

        return author;
    }

    public void removeAuthorByName(String queriedName) {
        EntityManager em = HibernateOperations.getEntityManager();
        em.getTransaction()
                .begin();
        Author a = queryForAuthorByName(queriedName);
        for (Book b : a.getBooks()) {
            if (b.getAuthors().size() == 1) {
                em.remove(b);
            } else {
                b.getAuthors().remove(a);
            }
        }
        em.remove(a);
        em.getTransaction()
                .commit();
    }

    public void removeAuthor(Author a) {
        EntityManager em = HibernateOperations.getEntityManager();
        em.getTransaction()
                .begin();
        for (Book b : a.getBooks()) {
            if (b.getAuthors().size() == 1) {
                em.remove(b);
            } else {
                b.getAuthors().remove(a);
            }
        }
        em.remove(a);
        em.getTransaction()
                .commit();
    }

    public List<Book> queryForAllBooks() {
        EntityManager em = HibernateOperations.getEntityManager();
        List<Book> books = em.createQuery("SELECT book FROM Book book").getResultList();
        return books;
    }

}
