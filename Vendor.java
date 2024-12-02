import java.util.Scanner;

public class Vendor {

    private Admin admin;
    private Ticketpool ticketpool;
    private Database database;
    private Scanner scanner;

    public Vendor(Admin admin, Ticketpool ticketpool, Database database) {
        this.admin = admin;
        this.ticketpool = ticketpool;
        this.database = database;
        this.scanner = new Scanner(System.in);
    }

    public void addTickets() {
        System.out.print("Enter the number of tickets to add: ");
        int numberOfTicketsToAdd = scanner.nextInt();

        synchronized (ticketpool) {
            if (numberOfTicketsToAdd > 0 && (database.getVariable("availableTickets") + numberOfTicketsToAdd) <= database.getVariable("totalTickets")) {
                new Thread(() -> {
                    int ticketsLeftToAdd = numberOfTicketsToAdd;
                    int releaseRate = database.getVariable("ticketReleaseRate");

                    while (ticketsLeftToAdd > 0) {
                        int ticketsThisBatch = Math.min(releaseRate, ticketsLeftToAdd);
                        ticketpool.addTickets(ticketsThisBatch);
                        ticketsLeftToAdd -= ticketsThisBatch;

                        try {
                            Thread.sleep(database.getVariable("perTicketReleaseTime"));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                System.out.println("Tickets are being released at a rate of " + database.getVariable("ticketReleaseRate") + " per interval...");
            } else {
                System.out.println("Invalid number of tickets to add or capacity exceeded.");
            }
        }
    }
}