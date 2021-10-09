package ir.maktab56.hw17.service.imp.sort;

import ir.maktab56.hw17.domain.*;

import java.util.Comparator;

public class PriceSortAscending implements Comparator<Ticket> {

    @Override
    public int compare(Ticket o1, Ticket o2) {
        Integer price1 = o1.getPrice();
        Integer price2 = o2.getPrice();


        return price1.compareTo(price2);
    }
}
