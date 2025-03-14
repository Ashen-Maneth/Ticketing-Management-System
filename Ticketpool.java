public class Ticketpool {

    private Database database;
    private int availableTicket1;

    public Ticketpool(Database database) {
        this.database = database;
    }


    public synchronized void addTickets(int ticketsToAdd) {
        if (ticketsToAdd > 0) {
            availableTicket1 += ticketsToAdd;
            database.setVariable("availableTickets", availableTicket1);
             System.out.println(ticketsToAdd + " tickets added to the pool. Total tickets available: " + database.getVariable("availableTickets"));
        } else {
            System.out.println("Invalid number of tickets to add.");
        }
    }

    public synchronized void removeTickets(int ticketsToRemove) {
        if (ticketsToRemove > 0) {
            availableTicket1 -= ticketsToRemove;
            database.setVariable("availableTickets", availableTicket1);
             System.out.println(ticketsToRemove + " tickets removed from the pool. Total tickets available: " + database.getVariable("availableTickets"));
        } else {
            System.out.println("Invalid number of tickets to remove.");
        }
    }

}