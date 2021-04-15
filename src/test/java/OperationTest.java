import cascades.HibernateUtil;
import cascades.domain.Author;
import cascades.domain.Book;
import org.hibernate.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class OperationTest {

    private static SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    @BeforeClass
    public static void beforeTests() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Before
    public void setUp() {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    @After
    public void tearDown() {
        transaction.rollback();
        session.close();
    }

    @Test
    public void whenParentSavedThenChildSaved() {
        //int authorId;
        Author author = new Author();
        Book book = new Book();
        book.setAuthors(Arrays.asList(author));
        book.setTitle("Rossum's Universal Robots");
        author.setName("K.Capek");
        author.setBooks(Arrays.asList(book));
        session.persist(author);
        int authorId=author.getId();
        int bookId= book.getId();
        Author savedAuthor=session.find(Author.class,authorId);
        Book savedBook= session.find(Book.class,bookId);
        assertEquals(author.getName(),savedAuthor.getName());
        assertEquals(book.getTitle(),savedBook.getTitle());
        session.flush();
        session.clear();

    }
}
