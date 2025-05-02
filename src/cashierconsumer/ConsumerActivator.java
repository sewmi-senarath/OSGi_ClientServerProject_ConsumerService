package cashierconsumer;

import com.mtit.serviceSewmi.BillingService;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;


public class ConsumerActivator implements BundleActivator {

    private ServiceReference<BillingService> serviceReference;
    private BillingService billingService;
    private Scanner input;

    // used to store user credentials
    private Map<String, String> userCredentials = new HashMap<>();

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Starting Cashier Consumer service...");

        serviceReference = context.getServiceReference(BillingService.class);

        billingService = context.getService(serviceReference);
        input = new Scanner(System.in);

        // Main menu
        while (true) {
            System.out.println("------------------- MAIN MENU !!!!!!!!!-----------------");
            System.out.println("1. Register a user");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println();
            System.out.print("Your choice: ");
            System.out.println();

            try {
                int choice = input.nextInt();
                input.nextLine(); 

                switch (choice) {
                    case 1:
                        if (registerUser() && loginUser() ) {
                        	showPaymentMenu();  //going back to login page
                        }
                        break;
                    case 2:
                        if (loginUser()) {
                            showPaymentMenu();
                        }
                        break;
                    case 3:
                        System.out.println("Goodbye!!!!!!");
                        return; // Exit the program
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                input.nextLine(); // Clear invalid input
            }
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping Consumer Service...");

            context.ungetService(serviceReference);

    }

    // registering a new user
    private boolean registerUser() {
        System.out.print("Enter a username: ");
        String username = input.nextLine();

        System.out.print("Enter a password: ");
        String password = input.nextLine();

        // Storing a new user
        userCredentials.put(username, password);
        System.out.println("Registration successful! YOU CAN LOGIN NOW !!!!!!!!!!!");
        System.out.println("\n");
        return true;
    }

    // checking the user
    private boolean loginUser() {
        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
            System.out.println("Welcome, " + username);
            System.out.println("\n");
            return true;
        } else {
            System.out.println("Invalid username or password!");
            return false;
        }
    }

    // Method to show the payment menu
    private void showPaymentMenu() {
        while (true) {
            System.out.println("----------------- PAYMENT MENU ----------------");
            System.out.println("1. View Payment Methods");
            System.out.println("2. Calculate Total");
            System.out.println("3. Handle Payment Details");
            System.out.println("4. Exit");
            System.out.print("\nYour choice: ");

            try {
                int choice = input.nextInt();
                input.nextLine(); 

                switch (choice) {
                    case 1:
                        billingService.ViewPaymentMethods();
                        System.out.println();
                        break;
                    case 2:
                        billingService.CalcTotal();
                        System.out.println();
                        break;
                    case 3:
                        billingService.PaymentDetails();
                        System.out.println();
                        break;
                    case 4:
                        System.out.println("Thank you!!!!!!!");
                        System.out.println();
                        return;
                    default:
                        System.out.println("######### Invalid choice. Choose from available options #######");
                        System.out.println();
                }
            } catch (InputMismatchException e) {
                System.out.println("###### Invalid input type !! Enter a valid number from the main menu ######");
                input.nextLine(); 
            }
        }
    }
}
