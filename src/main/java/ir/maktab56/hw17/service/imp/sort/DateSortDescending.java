package ir.maktab56.hw17.service.imp.sort;


import ir.maktab56.hw17.domain.Ticket;

import java.sql.Date;
import java.util.Comparator;

public class DateSortDescending implements Comparator<Ticket> {

    @Override
    public int compare(Ticket a, Ticket b) {
        Date date1 = a.getDepartureDate();
        Date date2 = b.getDepartureDate();
        return date2.compareTo(date1);
    }
}
