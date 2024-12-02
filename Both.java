public class Both {

    private Ticketpool ticketpool;
    private Database database;
    private Admin admin;

    public Both(Ticketpool ticketpool, Database database, Admin admin) {
        this.ticketpool = ticketpool;
        this.database = database;
        this.admin = admin;
    }

    public void testClass() {
        // Create multiple vendor and customer threads
        int numberOfVendors = 5; // Number of vendors to create
        int numberOfCustomers = 5; // Number of customers to create

        Thread[] vendorThreads = new Thread[numberOfVendors];
        Thread[] customerThreads = new Thread[numberOfCustomers];

        // Create and start vendor threads with constant ticket values for testing
        for (int i = 0; i < numberOfVendors; i++) {

            final int ticketsToAdd = database.getVariable("ticketReleaseRate"); // Each vendor adds the configured number of tickets

            vendorThreads[i] = new Thread(() -> {
                try {
                    while (true) {
                        synchronized (ticketpool) {
                            if ((database.getVariable("availableTickets") + ticketsToAdd) <= database.getVariable("totalTickets")) {
                                ticketpool.addTickets(ticketsToAdd);

                                System.out.println(Thread.currentThread().getName() + " added " + ticketsToAdd + " tickets.");

                            } else {

                                System.out.println(Thread.currentThread().getName() + " cannot add tickets, capacity exceeded.");
                            }
                        }

                        Thread.sleep(database.getVariable("perTicketReleaseTime")); // Sleep for configured time before adding tickets again
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Vendor-" + (i + 1));

            vendorThreads[i].start();
        }

        // Create and start customer threads with constant ticket purchase values for testing
        for (int i = 0; i < numberOfCustomers; i++) {

            final int totalTicketsToBuy = 300; // Each customer wants to buy 300 tickets
            final int ticketsPerBatch = database.getVariable("customerReleaseRate"); // Each customer buys 10 tickets per batch

            customerThreads[i] = new Thread(() -> {
                
                int ticketsBought = 0;

                try {
                    while (ticketsBought < totalTicketsToBuy) {
                        synchronized (ticketpool) {
                            if (ticketsPerBatch <= database.getVariable("availableTickets")) {

                                ticketpool.removeTickets(ticketsPerBatch);

                                ticketsBought += ticketsPerBatch;

                                System.out.println(Thread.currentThread().getName() + " bought " + ticketsPerBatch + " tickets. Total bought: " + ticketsBought);

                            } else {

                                System.out.println(Thread.currentThread().getName() + " cannot buy tickets, not enough available.");
                            }
                        }

                        Thread.sleep(3000); // Wait 3 seconds before buying the next batch
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Customer-" + (i + 1));
            customerThreads[i].start();
        }

        // Wait for all vendor threads to finish
        for (int i = 0; i < numberOfVendors; i++) {
            try {
                vendorThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Wait for all customer threads to finish
        for (int i = 0; i < numberOfCustomers; i++) {
            try {
                customerThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
