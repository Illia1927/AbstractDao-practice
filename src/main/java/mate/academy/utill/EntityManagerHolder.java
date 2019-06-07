package mate.academy.utill;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerHolder {
    protected static EntityManager em;

    private EntityManagerHolder() {
    }

    static {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("Train");
        em = emf.createEntityManager();
    }

    public static EntityManager getEntityManager() {
        return em;
    }
}
