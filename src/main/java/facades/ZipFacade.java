package facades;

import dtos.ZipDTO;
import entities.Zip;
import facades.inter.ZipFacadeInterface;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class ZipFacade implements ZipFacadeInterface {

    private static ZipFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private ZipFacade() {
    }

    public static ZipFacade getZipFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ZipFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public ZipDTO create(ZipDTO zip) {
        EntityManager em = emf.createEntityManager();
        try {
            Zip zipEntity = new Zip(zip.getZip(), zip.getCity());
            em.getTransaction().begin();
            em.persist(zipEntity);
            em.getTransaction().commit();
            return new ZipDTO(zipEntity);
        } finally {
            em.close();
        }
    }

    @Override
    public ZipDTO edit(ZipDTO Zip) {
        return null;
    }

    @Override
    public boolean delete(int zip) {
        return false;
    }

    @Override
    public ZipDTO getByZip(int zip) {
        EntityManager em = emf.createEntityManager();
        try {
            return new ZipDTO(em.find(Zip.class, zip));
        } finally {
            em.close();
        }
    }

    @Override
    public List<ZipDTO> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Zip> query = em.createQuery("SELECT z FROM Zip z", Zip.class);
            List<Zip> zips = query.getResultList();
            return ZipDTO.getDtos(zips);
        } finally {
            em.close();
        }
    }

    @Override
    public long getZipCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long zipCount = (long) em.createQuery("SELECT COUNT(z) FROM Zip z").getSingleResult();
            return zipCount;
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        ZipFacade zf = getZipFacade(emf);
        zf.getAll().forEach(dto -> System.out.println(dto));
    }
}
