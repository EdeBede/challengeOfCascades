package cascades;

import cascades.domain.Author;
import cascades.domain.Book;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

public class DriverApp {

    public static void main(String[] args) {
        HibernateOperations ho = new HibernateOperations();
        setUpAuthorsAndBooks(ho);

        Book rur = ho.findBookById(2);
        Author kernighan = ho.queryForAuthorByName("Kernighan");
        ho.removeAuthor(kernighan);
       // ho.removeAuthorByName("Kernighan");
        //ho.bulkRemoval(kernighan);
       // ho.removeAuthorByName("Kernighan");
        List<Book> allBooks = ho.queryForAllBooks();

    }

    private static void setUpAuthorsAndBooks(HibernateOperations ho) {
        EntityManager em = HibernateOperations.getEntityManager();
        em.getTransaction()
                .begin();
        Author kCapek = new Author();
        Author kernighan = new Author();
        Author ritchie = new Author();

        Book rur = new Book();
        Book cProg = new Book();
        Book unixEnv = new Book();
        Book unixProg = new Book();

        kCapek.setName("K.Capek");
        kernighan.setName("Kernighan");
        ritchie.setName("Ritchie");

        rur.setTitle("Rossum's Universal Robots");
        cProg.setTitle("The C programming Language");
        unixEnv.setTitle("The unix environment");
        unixProg.setTitle("Unix for programmers");

        kCapek.setBooks(Arrays.asList(rur));
        kernighan.setBooks(Arrays.asList(cProg,unixEnv));
        ritchie.setBooks(Arrays.asList(cProg,unixProg));

        rur.setAuthors(Arrays.asList(kCapek));
        cProg.setAuthors(Arrays.asList(kernighan,ritchie));
        unixEnv.setAuthors(Arrays.asList(kernighan));
        unixProg.setAuthors(Arrays.asList(ritchie));

        em.persist(kCapek);
        em.persist(kernighan);
        em.persist(ritchie);
        em.getTransaction()
                .commit();
        em.close();


    }
}
