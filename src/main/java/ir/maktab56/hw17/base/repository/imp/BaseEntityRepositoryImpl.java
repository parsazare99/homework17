package ir.maktab56.hw17.base.repository.imp;

import ir.maktab56.hw17.base.domain.BaseEntity;
import ir.maktab56.hw17.base.repository.BaseEntityRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public abstract class BaseEntityRepositoryImpl<E extends BaseEntity<ID>, ID extends Serializable>
        implements BaseEntityRepository<E, ID> {

    protected final EntityManager entityManager;

    public BaseEntityRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Class<E> getEntityClass();


    @Override
    public E save(E e) {
        if (e.getId() == null) {
            entityManager.persist(e);
            return e;
        } else {
            return entityManager.merge(e);
        }
    }

    @Override
    public E findById(ID id) {
        E entity = entityManager.find(getEntityClass(), id);
        entityManager.clear();
        return entity;
    }

    @Override
    public List<E> findAll() {
        List<E> list = entityManager.createQuery(
                "from " + getEntityClass().getSimpleName(),
                getEntityClass()
        ).getResultList();
        entityManager.clear();
        return list;
    }

    @Override
    public void delete(E e) {
//        entityManager.merge(e);
//        entityManager.remove(e);
        if (entityManager.contains(e)){
            entityManager.remove(e);
        } else {
            BaseEntity baseEntity = entityManager.getReference(e.getClass(), e.getId());
            entityManager.remove(baseEntity);
        }
    }

    @Override
    public boolean existsById(ID id) {
        return entityManager.createQuery(
                "select count(id) from " + getEntityClass().getSimpleName() +
                        " where id = :id",
                Long.class
        ).setParameter("id", id)
                .getSingleResult() == 1L;
    }

    @Override
    public Long countAll() {
        return entityManager.createQuery(
                "select count(id) from " + getEntityClass().getSimpleName(),
                Long.class
        ).getSingleResult();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}