public class Ticketpool {

    private int availableTickets;

    public synchronized void addTickets(int ticketsToAdd) {
        if (ticketsToAdd > 0) {
            availableTickets += ticketsToAdd;
            // System.out.println(ticketsToAdd + " tickets added to the pool. Total tickets available: " + availableTickets);
        } else {
            System.out.println("Invalid number of tickets to add.");
        }
    }

    public synchronized void removeTickets(int ticketsToRemove) {
        if (ticketsToRemove > 0) {
            availableTickets -= ticketsToRemove;
            // System.out.println(ticketsToRemove + " tickets removed from the pool. Total tickets available: " + availableTickets);
        } else {
            System.out.println("Invalid number of tickets to remove.");
        }
    }

    public synchronized int getAvailableTickets() {
        return availableTickets;
    }
}