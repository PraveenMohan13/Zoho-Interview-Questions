import java.util.*;
class bus{
    String bustype;
    String seattype;
    int totalseats=12;
    int nooftick;
    int bookedseats;
    int[][] seatview=new int[4][3];
    Scanner sc=new Scanner(System.in);
    bus(String bustype,String seattype)
    {
        int number=1;
        this.bustype=bustype;
        this.seattype=seattype;
        for(int i=0;i<4;i++)
         for(int j=0;j<3;j++)
         {
             seatview[i][j]=number;
             number++;
         }
    }
    void viewSeats()
    {
        System.out.println("*->Driver\n");
        System.out.print("* ");
        for(int i=0;i<4;i++)
        {
         for(int j=0;j<3;j++)
         {
        	 if(seatview[i][j]>0)
        	 {
                 System.out.print(" "+seatview[i][j]+" ");
        	 }
        	 else if(seatview[i][j]==-1)
        	 {
        		 System.out.print(" M ");
        	 }
        	 else
        	 {
        		 System.out.print(" F ");
        	 }
         }
         System.out.println();
         if(i==1)
         {
             System.out.println("  ------>");
         }
         System.out.print("  ");
        }
    }
    public boolean checkAvail(int seatno)
    {
        boolean t=true;
        for(int i=0;i<4;i++)
            {
                for(int j=0;j<3;j++)
                {
                    if(seatview[i][j]==seatno)
                    {
                        if(seatview[i][j]==-1 || seatview[i][j]==-2)
                        {
                            t=true;
                        }
                        else
                        {
                            t=false;
                        }
                    }
                }
            }
            return t;
    }
    public int[] book()
    {
        System.out.println("Enter the no of tickets you want to book");
        nooftick=sc.nextInt();
        int[] seat=new int[nooftick];
        for(int i1=0;i1<nooftick;i1++)
        {
            System.out.print("Enter the seat number to be booked:");
            int seatno=sc.nextInt();
            System.out.print("Enter gender(M or F):");
            char gender=sc.next().charAt(0);
            if(seatno<1 || seatno>12)
            {
                System.out.println("Invalid seat number!!!\nPlease choose seat between 1-12");
                seatno=0;
                i1--;
            }
            else if(checkAvail(seatno))
            {
                System.out.println("Sorry!!! The entered seat number is already booked...\nPlease choose another seat");
                seatno=0;
                i1--;
            }
            int gend;
            if(gender=='F')
             gend=-2;
            else
             gend=-1;
            for(int i=0;i<4;i++)
            {
                for(int j=0;j<3;j++)
                {
                    if(seatview[i][j]==seatno)
                    {
                        if(gend==-2)
                        {
                            seatview[i][j]=gend;
                        }
                        else
                        {
                            if(((i+1)%2!=0 && seatview[i+1][j]==-2) || ((i+1)%2==0 && seatview[i-1][j]==-2))
                            {
                                System.out.println("Cannot book seat....Please choose male seat neighbour");
                                seatno=0;
                                i1--;
                            }
                            else
                            {
                                seatview[i][j]=gend;
                            }
                        }
                    }
                }
            }
            if(seatno!=0)
             seat[i1]=seatno;
        }
        bookedseats+=seat.length;
        return seat;
    }
    void deleteSeats(int[] bookedtickets)
    {
        for(int t:bookedtickets)
        {
            int seat=1,c=0;
            for(int i=0;i<4;i++)
            {
                for(int j=0;j<3;j++)
                {
                    if(seat==t)
                    {
                        seatview[i][j]=seat;
                        bookedseats--;
                        c+=1;
                        break;
                    }
                    seat++;
                }
                if(c>0)
                {
                	break;
                }
            }
        }
    }
    double calculateFare(int nooftickets)
    {
        double fare;
        if(bustype.equalsIgnoreCase("ac") && seattype.equalsIgnoreCase("sleeper"))
        {
            fare=nooftickets*1000.00;
        }
        else if(bustype.equalsIgnoreCase("ac") && seattype.equalsIgnoreCase("seater"))
        {
            fare=nooftickets*650.00;
        }
        else if(bustype.equalsIgnoreCase("Non-ac") && seattype.equalsIgnoreCase("sleeper"))
        {
            fare=nooftickets*750.00;
        }
        else
        {
            fare=nooftickets*500.00;
        }
        return fare;
    }
    
}
