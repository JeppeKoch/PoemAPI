package dat.daos;

import dat.config.HibernateConfig;
import dat.dtos.PoemDTO;
import dat.entities.Poem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
public class PoemDAO {

    List<Poem> poems = new ArrayList<>();
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("poems");
    public void delete(PoemDTO poemDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Poem poemEntity = em.find(Poem.class, poemDTO.getId());

            if (poemEntity != null) {

                em.remove(poemEntity);
            } else {
                throw new IllegalArgumentException("Movie with ID " + poemDTO.getId() + " not found.");
            }

            em.getTransaction().commit();
        }
    }

    public PoemDTO create(PoemDTO poemDTO) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Create a new Movie entity from the DTO data
            Poem poemEntity = new Poem();
            poemEntity.setTitle(poemDTO.getTitle());
            poemEntity.setAuthor(poemDTO.getAuthor());
            poemEntity.setPoem(poemDTO.getPoem());
            poemEntity.setType(poemDTO.getType());

            // Persist the Movie entity into the database
            em.persist(poemEntity);
            em.getTransaction().commit();
            poems.add(poemEntity);

            // Set the generated ID back in the DTO
            poemDTO.setId(poemEntity.getId());
            return poemDTO;
        } catch (Exception e) {
            em.getTransaction().rollback(); // Rollback the transaction if there's an error
            throw new RuntimeException("Error creating poem", e); // Optionally rethrow the exception
        } finally {
            em.close();
        }
    }






    public PoemDTO findById(Long poemId) {
        EntityManager em = emf.createEntityManager();
        try {
            Poem poem = em.find(Poem.class, poemId);
            if (poem != null) {
                return new PoemDTO(poem);  // Convert entity to DTO
            } else {
                throw new IllegalArgumentException("Movie with ID " + poemId + " not found.");
            }
        } finally {
            em.close();
        }
    }

    public List<PoemDTO> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Poem> query =em.createQuery("SELECT m from Poem m",Poem.class);
            List<Poem> resultList = query.getResultList();
            List<PoemDTO> dtoList = new ArrayList<>();
            for (Poem poem : resultList) {
                dtoList.add(new PoemDTO(poem));

            }
            return dtoList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void update(Poem poem) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Query query = em.createQuery("UPDATE Poem m set m.type=:type where m.id=:id");
            query.setParameter("type", poem.getType());
            query.setParameter("id", poem.getId());
            query.executeUpdate();
            em.getTransaction().commit();

        }
    }
}
