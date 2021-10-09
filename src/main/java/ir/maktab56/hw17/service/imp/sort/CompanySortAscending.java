package ir.maktab56.hw17.service.imp.sort;

import ir.maktab56.hw17.domain.Ticket;

import java.util.Comparator;

public class CompanySortAscending implements Comparator<Ticket> {


    @Override
    public int compare(Ticket a, Ticket b) {
        String name1 = a.getCompany().getName();
        String name2 = b.getCompany().getName();

        return name1.compareTo(name2);
    }
}

