package ir.maktab56.hw17.service.imp.sort;

import ir.maktab56.hw17.domain.*;

import java.util.Comparator;

public class CompanySortDescending implements Comparator<Ticket> {

    @Override
    public int compare(Ticket a, Ticket b) {
        String name1 = a.getCompany().getName();
        String name2 = b.getCompany().getName();

        return name2.compareTo(name1);
    }
}
