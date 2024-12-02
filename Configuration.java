import java.util.Scanner;

public class Configuration {

    private Database database;
    private Scanner scanner;

    public Configuration(Database database) {
        this.database = database;
        this.scanner = new Scanner(System.in);
    }

    public void startProgram() {
        System.out.print("[Configuration] Enter the total number of tickets: ");
        database.setVariable("totalTickets", scanner.nextInt());
    
        System.out.print("[Configuration] Enter the ticket release rate (tickets per interval): ");
        database.setVariable("ticketReleaseRate", scanner.nextInt());
    
        System.out.print("[Configuration] Enter the customer release rate: ");
        database.setVariable("customerReleaseRate", scanner.nextInt());
    
        System.out.print("[Configuration] Enter the maximum ticket capacity: ");
        database.setVariable("maxTicketCapacity", scanner.nextInt());
    
        System.out.print("[Configuration] Enter the per Ticket release time (milliseconds): ");
        database.setVariable("perTicketReleaseTime", scanner.nextInt());
    
        System.out.println();
        System.out.println("*** Please Confirm the Details Below ***");
        System.out.println("Total Tickets: " + database.getVariable("totalTickets"));
        System.out.println("Ticket Release Rate: " + database.getVariable("ticketReleaseRate"));
        System.out.println("Customer Release Rate: " + database.getVariable("customerReleaseRate"));
        System.out.println("Maximum Ticket Capacity: " + database.getVariable("maxTicketCapacity"));
        System.out.println("Ticket Release Time Duration: " + database.getVariable("perTicketReleaseTime"));
    
        System.out.print("Will start the program? Yes (Y) or No (N): ");
        scanner.nextLine();
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("Y")) {
            System.out.println("Details confirmed. Program will start.");
        } else {
            database.reset();
            System.out.println("Details not confirmed. Program will stop.");
            System.exit(0);
        }
    }
}