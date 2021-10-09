package ir.maktab56.hw17.repository;

import ir.maktab56.hw17.base.repository.BaseEntityRepository;
import ir.maktab56.hw17.domain.Ticket;

import java.util.List;

public interface TicketRepository extends BaseEntityRepository<Ticket, Integer> {

List<Ticket> findByOriginAndDestination(String s1, String s2);
}
