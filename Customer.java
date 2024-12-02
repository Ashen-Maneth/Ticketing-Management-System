import java.util.Scanner;

public class Customer implements Runnable {

    private Ticketpool ticketpool;
    private Database database;
    private Scanner scanner;

    public Customer(Ticketpool ticketpool, Database database) {
        this.ticketpool = ticketpool;
        this.database = database;
        this.scanner = new Scanner(System.in);
    }
    @Override
    public void run() {
        System.out.print("Enter the number of tickets you want to buy: ");
        int buyTicketCount = scanner.nextInt();

        if (buyTicketCount > database.getVariable("customerReleaseRate")) {
            System.out.println("You can only buy a maximum of " + database.getVariable("customerReleaseRate") + " tickets at a time!");
            return;
        }

        synchronized (ticketpool) {
            if (buyTicketCount <= database.getVariable("availableTickets")) {
                ticketpool.removeTickets(buyTicketCount);
                System.out.println("You have successfully bought " + buyTicketCount + " tickets.");
            } else {
                System.out.println("Not enough tickets to buy!");
            }
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted.");
        }
    }
}