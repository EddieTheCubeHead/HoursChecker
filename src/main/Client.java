import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * A simple class functioning as the main class of the application. Contains a small command line based UI to input
 * hours for validation
 *
 * @author Eetu Asikainen
 */
public class Client {

    /**
     * The main function of the program. Simple command line UI
     *
     * @param args String array containing the arguments the program was run with. No functionality yet
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HoursChecker checker;

        while (true) {
            System.out.print("Welcome to the EddieCorp working hours calculator!\n" +
                             "Please input your hours in the format \"hh:mm hh:mm\" to validate them and calculate " +
                             "the length of your shift. Input \"0\" to exit.\n");

            String input = scanner.nextLine();

            if (input.equals("0")) {
                System.out.println("Thank you for using EddieCorp working hours calculator!");
                break;

            } else if (!input.matches("^[0-2][0-9]:[0-5][0-9] [0-2][0-9]:[0-5][0-9]$")) {
                System.out.println("Invalid formatting. Try again.");
                System.out.println("");
                continue;
            }

            String[] times = input.split(" ", 2);

            try {
                checker = new HoursChecker(LocalDate.now(), times[0], times[1]);
            } catch (DateTimeParseException e) {
                System.out.println("Something went wrong. Make sure your input is correct and between 00:00 and 23:59");
                System.out.println("");
                continue;
            }

            if (checker.isValid()) {
                Duration shift = checker.getShift();
                // Choosing to utilize cleaner formatting via duration in the client instead of getting the hours as a
                // decimal. I hope this doesn't break the assignment rules, as the requested class still has all the
                // functionalities required, I just chose to add one getter and use that getter in the client instead of
                // the requested hours as a decimal method.
                System.out.println(String.format("Your shift lasted %d hours and %d minutes.",
                                                 shift.toHours(),
                                                 shift.toMinutes() % 60));

            } else if (checker.getHours() > 16) {
                System.out.println("Your shift is too long. (max 16 hours)");

            } else {
                System.out.println("Please ensure your end time is later than your start time. Time travel during " +
                                   "work is stricly forbidden under company rules.");
            }

            System.out.println("");
        }
    }
}
