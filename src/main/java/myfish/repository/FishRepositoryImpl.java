package myfish.repository;

import myfish.domain.entities.Fish;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class FishRepositoryImpl implements FishRepository {

    private final EntityManager entityManager;

    @Inject
    public FishRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Fish save(Fish fish) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(fish);
        this.entityManager.getTransaction().commit();

        return fish;
    }

    @Override
    public List<Fish> findAll() {
        this.entityManager.getTransaction().begin();
        List<Fish> allTubes = this.entityManager
                .createQuery("SELECT t FROM Fish t", Fish.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return allTubes;
    }

    @Override
    public Fish findById(String id) {
        Fish fish = this.entityManager
                .createQuery("SELECT f FROM Fish f WHERE f.id = :id", Fish.class)
                .setParameter("id", id)
                .getSingleResult();
        return fish;
    }

    @Override
    public Fish findByName(String name) {
        Fish fish = this.entityManager
                .createQuery("SELECT f FROM Fish f WHERE f.naem = :name", Fish.class)
                .setParameter("name", name)
                .getSingleResult();
        return fish;
    }

    @Override
    public long size() {
        this.entityManager.getTransaction().begin();
        long size = this.entityManager
                .createQuery("SELECT count(f) FROM Fish f", long.class)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return size;
    }

    @Override
    public Fish update(Fish fish) {
        this.entityManager.merge(fish);
        return fish;
    }
}
