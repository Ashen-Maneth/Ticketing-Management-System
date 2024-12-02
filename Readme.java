public class Readme {

    public void readme() {

        System.out.println("Instructions for the run program:");
        System.out.println();
        System.out.println();

        System.out.println("Welcome to the Ticketing System!");
        System.out.println();
        System.out.println("When you first run the program, it checks if the system is configured. If not, you can either exit by pressing 0 or log in as an admin (enter 1) using a code (A1234). Once logged in, \nthe admin configures key ticketing parameters like total tickets, release rates, and maximum capacity, and then returns to the main menu.");
        System.out.println("In the main menu, users have four options: log in as a vendor, customer, admin, or observe both processes working simultaneously.");
        System.out.println();
        System.out.println("As a Vendor, you can add tickets to the pool, which are gradually added based on the configured ticket release rate in the background. By default, the ticket addition happens silently, \nbut if you want to see this process, you can uncomment lines 7 and 17 in the Ticketpool class to display the background progress of ticket additions.");
        System.out.println("Alternatively, you can also observe the ticket process in Option 4, which runs both vendors and customers concurrently.");
        System.out.println();
        System.out.println("The Customer can buy tickets from the available pool, but cannot exceed the configured purchase limit.");
        System.out.println("If you select Admin, you can re-login to reconfigure settings, stop, or restart the program.");
        System.out.println("Lastly, the fourth option allows for a demonstration of both vendors and customers working concurrently—this is achieved using multithreading to simulate real-time interaction between \nmultiple vendors adding tickets and customers purchasing them.");
        System.out.println("Each role's actions are synchronized to avoid conflicts, ensuring a smooth flow of ticket transactions in the system.");
        System.out.println();


        System.out.println("Thank you ....");

        System.out.println();

    }
    
}
