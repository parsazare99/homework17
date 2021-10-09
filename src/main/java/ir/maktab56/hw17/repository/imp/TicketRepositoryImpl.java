package ir.maktab56.hw17.repository.imp;

import ir.maktab56.hw17.base.repository.imp.BaseEntityRepositoryImpl;
import ir.maktab56.hw17.domain.Ticket;
import ir.maktab56.hw17.repository.TicketRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class TicketRepositoryImpl extends BaseEntityRepositoryImpl<Ticket, Integer> implements TicketRepository {


    public TicketRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Ticket> getEntityClass() {
        return Ticket.class;
    }


    @Override
    public List<Ticket> findByOriginAndDestination(String s1, String s2) {

        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Ticket> criteriaQuery = criteriaBuilder.createQuery(Ticket.class);

        Root<Ticket> root = criteriaQuery.from(Ticket.class);

        Predicate predicate1 = criteriaBuilder.equal(root.get("origin"), s1);
        Predicate predicate2 = criteriaBuilder.equal(root.get("destination"), s2);

        criteriaQuery.select(root).where(predicate1, predicate2);

        TypedQuery<Ticket> query = getEntityManager().createQuery(criteriaQuery);
        List<Ticket> userList = query.getResultList();
        return userList;

    }
}
