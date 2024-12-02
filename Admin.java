import java.util.Scanner;

public class Admin {

    private Configuration configuration;
    private Database database;
    private Ticketpool ticketpool;
    private Scanner scanner;

    public Admin(Database database, Configuration configuration , Ticketpool ticketpool) {
        this.configuration = configuration;
        this.database = database;
        this.ticketpool = ticketpool;
        this.scanner = new Scanner(System.in);
    }

    public void adminMenu() {
        System.out.println(" *** Welcome to the admin menu *** ");
        System.out.println(" 1) Configure and start the program.");
        System.out.println(" 2) Stop the program.");
        System.out.println(" 3) Check the current status.");
        System.out.println(" 4) Exit the program.");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                configuration.startProgram();
                break;
            case "2":
                stopProgram();
                break;
            case "3":
                checkStatus();
                break;

            case "4":
                System.exit(0);
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void stopProgram() {
        System.out.print("Do you want to stop the program? Yes (Y) or No (N): ");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("Y")) {
            database.reset();
            System.out.println("Program stopped and redirected to main menu...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Program will continue.");
        }
    }

    public void checkStatus() {
        System.out.println("Press 'Q' and Enter to stop real-time monitoring...");

        
        // Start a separate thread to listen for user input
        Thread stopThread = new Thread(() -> {
            Scanner stopScanner = new Scanner(System.in);
            while (true) {
                String input = stopScanner.nextLine();
                if (input.equalsIgnoreCase("Q")) {
                    System.exit(0); 
                }
            }
        });
    
        // stopThread.setDaemon(true); 
        stopThread.start();
    
    
        // Real-time status monitoring

        System.out.println();
        System.out.println(" *** Real-Time Status *** ");
        System.out.println();
        System.out.println("Total Tickets: " + database.getVariable("totalTickets"));
        System.out.println("Ticket Release Rate: " + database.getVariable("ticketReleaseRate"));
        System.out.println("Customer Release Rate: " + database.getVariable("customerReleaseRate"));
        System.out.println("Maximum Ticket Capacity: " + database.getVariable("maxTicketCapacity"));
        System.out.println("Ticket Release Time Duration: " + database.getVariable("perTicketReleaseTime"));
        System.out.println();
        while (true) {
            try {
                // Clear the console
                System.out.print("\033[H\033[2J");
                System.out.flush();


                System.out.println("Available Tickets: " + ticketpool.getAvailableTickets());
                System.out.println();
                
                
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
    
}