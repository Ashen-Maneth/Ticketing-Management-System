import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String answerForFirstLogin;
        String adminLoginCode = "A1234";
        String answerForLogin;


        Readme readme = new Readme();
        Database database = new Database();
        Ticketpool ticketpool = new Ticketpool(database);
        Configuration configuration = new Configuration(database);
        Admin admin = new Admin(database, configuration);
        Vendor vendor = new Vendor(admin, ticketpool, database);
        Both both = new Both(ticketpool, database, admin);
        Customer customer = new Customer(ticketpool, database);


        Scanner scanner = new Scanner(System.in);

        while (true) {


            System.out.println();

            if (database.getVariable("totalTickets") == 0 &&  database.getVariable("ticketReleaseRate") == 0 && database.getVariable("customerReleaseRate") == 0 && database.getVariable("maxTicketCapacity") == 0) {

                System.out.println("*** Program is not configured yet! ***");

                System.out.println();

                System.out.println("To exit the program, please enter 0:");
                System.out.println("If you are the admin, please enter 1 to configure the program:");
                System.out.println("[For leactures] To read the program flow, please enter 2:");
                System.out.println();
                System.out.print("Enter your choice: ");
                answerForFirstLogin = scanner.nextLine();
                System.out.println();

                if (answerForFirstLogin.equals("0")) {
                    System.exit(0);
                } else if (answerForFirstLogin.equals("1")) {
                    System.out.print("Enter the admin login code [ hint: A1234 ]: ");
                    if (adminLoginCode.equals(scanner.nextLine())) {
                        System.out.println("Login code confirmed.");
                        System.out.println("Logging in... please wait.");
                        System.out.println();

                        // Wait for 2 seconds
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        configuration.startProgram();
                    } else {
                        System.out.println("Invalid login code. Please try again.");
                    }

                } else if (answerForFirstLogin.equals("2")) {

                    readme.readme();

                    System.out.println();
                    System.out.println();

                    System.out.print("Press Enter to continue...");
                    scanner.nextLine();

                }
                 else {
                    System.out.println("Invalid choice. Please try again.");
                }

            } else {

                System.out.println();
                System.out.println("       ***************************************");
                System.out.println("       *** Welcome to the Ticketing System ***");
                System.out.println("       ***************************************");

                System.out.println();

                System.out.println("Are you:\n1) Vendor\n2) Customer\n3) Admin\n4) Check both vendors and customers at the same time.");
                System.out.println();

                System.out.print("Enter your choice: ");
                answerForLogin = scanner.nextLine();
                System.out.println();

                if (answerForLogin.equals("1")) {
                    vendor.addTickets();

                } else if (answerForLogin.equals("2")) {
                    customer.run();

                } else if (answerForLogin.equals("3")) {

                    System.out.print("Enter the admin login code: ");

                    if (adminLoginCode.equals(scanner.nextLine())) {
                        System.out.println("Login code confirmed.");
                        System.out.println("Logging in... please wait.");
                        System.out.println();

                        // Wait for 2 seconds
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        admin.adminMenu();
                    } else {
                        System.out.println("Invalid login code. Please try again.");
                    }
                } else if (answerForLogin.equals("4")) {

                    both.testClass();

                    break;

                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}