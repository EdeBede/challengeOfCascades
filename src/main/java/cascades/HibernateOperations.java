package cascades;

import cascades.domain.Author;
import cascades.domain.Book;

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

    public void saveAuthor() {
        EntityManager em = HibernateOperations.getEntityManager();
        em.getTransaction()
                .begin();

        Author author = new Author();
        Book book = new Book();
        book.setAuthors(Arrays.asList(author));
        book.setTitle("Rossum's Universal Robots");
        author.setName("K.Capek");
        author.setBooks(Arrays.asList(book));

        em.persist(author);
        em.getTransaction()
                .commit();

    }

 /*   public void saveMovie() {
        EntityManager em = HibernateOperations.getEntityManager();
        em.getTransaction()
                .begin();
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setMovieName("The Godfather");
        movie.setReleaseYear(1972);
        movie.setLanguage("English");
        em.persist(movie);
        em.getTransaction()
                .commit();
    }*/


  /*  public Movie queryForMovieById() {
        EntityManager em = HibernateOperations.getEntityManager();
        Movie movie = (Movie) em.createQuery("SELECT movie from Movie movie where movie.id = ?1")
                .setParameter(1, new Long(1L))
                .getSingleResult();
        return movie;
    }*/


    /*public List<?> queryForMovies() {
        EntityManager em = HibernateOperations.getEntityManager();
        List<?> movies = em.createQuery("SELECT movie from Movie movie where movie.language = ?1")
                .setParameter(1, "English")
                .getResultList();
        return movies;
    }*/


  /*  public void removeMovie() {
        EntityManager em = HibernateOperations.getEntityManager();
        em.getTransaction()
                .begin();
        Movie movie = em.find(Movie.class, new Long(1L));
        em.remove(movie);
        em.getTransaction()
                .commit();
    }*/

}