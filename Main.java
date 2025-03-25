import java.util.*;

public class Main
{
    Scanner sc=new Scanner(System.in);
    ArrayList<customer> customerobj=new ArrayList<customer>();
    ArrayList<bus> busobj=new ArrayList<bus>();
    ArrayList<ticket> ticketobj=new ArrayList<ticket>();
    void customerSignUp()
    {
        System.out.println("Enter Customer id, Customer name, Password, Age, Gender");
        int id=sc.nextInt();
        String name=sc.next();
        String pass=sc.next();
        int age=sc.nextInt();
        char gender=sc.next().charAt(0);
        int f=0;
        for(customer c:customerobj)
        {
            if(c.id==(id))
            {
                f++;
            }
        }
        if(f==0)
        {
            customerobj.add(new customer(id,name,pass,age,gender));
            System.out.println("Back to home page");
        }
        else
        {
            System.out.println("User id already exists");
        }
    }
    void customerLogin()
    {
        System.out.println("Enter Customer id and Password to Login");
        int id=sc.nextInt();
        String pass=sc.next();
        int f=0;
        customer currcustomer = null;
        for(customer c:customerobj)
        {
            if(c.id==(id) && c.password.equals(pass))
            {
                currcustomer=c;
                f++;
                break;
            }
        }
        if(f==0)
        {
            System.out.println("Invalid Login or Password");
        }
        else
        {
            int t=0;
            System.out.println("Logged in Successfully...\nWelcome "+currcustomer.name);
            while(t==0)
            {
            System.out.println("\n1)BookTickects\n2)ViewTickets\n3)CancelTickets\n4)Summary\n5)Logout");
            int choiceafterlogin=sc.nextInt();
            switch(choiceafterlogin)
            {
                case 1:System.out.println("Choose bustype,seattype");
                String btype=sc.next();
                String stype=sc.next();
                for(bus b:busobj)
                {
                    if(b.bustype.equalsIgnoreCase(btype) && b.seattype.equalsIgnoreCase(stype))
                    {
                        int[] bookedseat=b.book();
                        int nooftick=b.nooftick;
                        double fare=b.calculateFare(nooftick);
                        int tickid=ticketobj.size();
                        System.out.println("-------------------------------");
                        ticketobj.add(new ticket(tickid,bookedseat,b,nooftick,fare,currcustomer.id));
                        System.out.println("Your Ticket id is:"+tickid+"\nYou have to pay Rs."+fare+"\nYour Tickets were booked\n!!!!Happy Journey!!!!");
                        System.out.println("-------------------------------");
                    }
                }
                break;
                case 2:System.out.println("Enter bustype,seattype");
                btype=sc.next();
                stype=sc.next();
                for(bus b:busobj)
                {
                    if(b.bustype.equalsIgnoreCase(btype) && b.seattype.equalsIgnoreCase(stype))
                    {
                        b.viewSeats();
                    }
                }
                break;
                case 3:System.out.println("Enter booking id");
                int bid=sc.nextInt();
                for(ticket ti:ticketobj)
                {
                	if(ti.id==bid && ti.customer_id!=currcustomer.id)
                	{
                		System.out.println("Ticket not belong to you!!!!\nTicket cancellation cannot be possible");
                		break;
                	}
                	else if(ti.id==bid)
                    {
                        bus bookedbus=ti.ticketDetails();
                        int[] bookedseats=ti.bookedtickets;
                        bookedbus.deleteSeats(bookedseats);
                        ticketobj.remove(ti);
                        System.out.println("Your ticket has been deleted successfully!!!\nYour amount "+ti.fare+" will return back soon...\nThank you");
                        break;
                    }
                }
                break;
                case 4:System.out.println("Bus summary:");
                for(bus b:busobj)
                {
                    System.out.println("Bus type: "+b.bustype);
                    System.out.println("Seat type: "+b.seattype);
                    System.out.println("Booked seats: "+b.bookedseats);
                    System.out.println("------------------------");
                }
                break;
                case 5:System.out.println("You have successfully loggedout...come back again!!!");
                       t++;
                       break;
            }
        }
    }
}
    public void createObj()
    {
            customerobj.add(new customer(101,"Ram","Ram101",24,'M'));
            customerobj.add(new customer(102,"Meera","Meera102",35,'F'));
            customerobj.add(new customer(103,"Raghu","Raghu103",22,'M'));
            customerobj.add(new customer(104,"Dhanya","Dhanya104",30,'F'));
            busobj.add(new bus("AC","seater"));
            busobj.add(new bus("AC","sleeper"));
            busobj.add(new bus("Non-AC","seater"));
            busobj.add(new bus("Non-AC","sleeper"));
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int choice;
        Main m=new Main();
        m.createObj();
        while(true)
        {
            
        System.out.println("1)Sign up\n2)Login\n3)Exit");
        choice=sc.nextInt();
        switch(choice)
        {
            case 1:m.customerSignUp();
                   break;
            case 2:m.customerLogin();
                   break;
            case 3:System.exit(0);
        }
       }
    }
}
