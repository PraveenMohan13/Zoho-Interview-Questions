import java.util.*;
class ticket
{
    int id;
    int[] bookedtickets;
    bus object;
    int nooftick;
    double fare;
    int customer_id;
    ticket(int id,int[] bookedtickets,bus object,int nooftick,double fare,int customer_id)
    {
        this.id=id;
        this.bookedtickets=bookedtickets;
        this.object=object;
        this.nooftick=nooftick;
        this.fare=fare;
        this.customer_id=customer_id;
    }
    bus ticketDetails()
    {
    	System.out.println("-----------------------");
        System.out.println("Ticket details are:\n");
        System.out.println("Bus type:"+object.bustype);
        System.out.println("Seat type:"+object.seattype);
        System.out.println("Booked by the customer id:"+customer_id);
        System.out.println("No. of tickets:"+nooftick);
        System.out.println("Booked seates:"+Arrays.toString(bookedtickets));
        System.out.println("------------------------");
        return object;
    }
}
