package edu.ithaca.bhamula1.hotel;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public abstract class CompareReservationByDate implements Comparator<Reservation> {

    @Override
    public int compare(Reservation r1, Reservation r2) {
        Date date1 = r1.getCheckInDate().getTime();
        Date date2 = r2.getCheckInDate().getTime();
        if(date1.before(date2)){
            return -1;
        }
        else if(date1.after(date2)){
            return 1;
        }
        else{
            return 0;
        }
    }
}
