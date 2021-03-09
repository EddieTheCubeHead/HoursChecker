import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class HoursChecker {

    // Formatter and max shift are the same for all times
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private static Duration maxShift = Duration.ofHours(16);

    private boolean valid;
    private Duration shift;
    private double shiftHours; // Would love to work with just durations, but spec calls for this to be a float

    /**
     * A class to validate working hours and extract shift length for a given day
     *
     * @param date The date the work happened on. Instance of {@link LocalDate}. In spec but not needed for functionality
     * @param start A {@link String} representing the start of the shift, formatted as "hh:mm" (24-hours)
     * @param end A {@link String} representing the end of the shift, formatted as "hh:mm" (24-hours)
     * @throws DateTimeException if the given strings are in invalid format
     */
    public HoursChecker(LocalDate date, String start, String end) throws DateTimeParseException {
        // Datetime can parse "24:00", but spec doesn't allow it. Thus manual throwing is necessary.
        // The generated exception uses dummy data, as parsing exception logic is handled naively without data from
        // the exception in the client
        if (start.equals("24:00") || end.equals("24:00")) {

            // TODO if more robust error handling gets implemented, replace dummy value with actual data
            throw new DateTimeParseException("", "", 0);
        }

        LocalTime startTime = LocalTime.parse(start, timeFormatter);
        LocalTime endTime = LocalTime.parse(end, timeFormatter);

        shift = Duration.between(startTime, endTime);

        // 0-length shifts weren't mentioned, so I assumed they are invalid
        if (shift.isNegative() || shift.isZero()) {
            valid = false;
        } else {
            valid = shift.compareTo(maxShift) < 1;
        }

        /* As this is just a demonstration I wanted to include a code snippet enabling overnight shifts.
         * I did not implement this as the given parameters in the requirements (one date, two times) are not enough
         * to distinguish between negative time shifts and overnight shifts and negative time shifts were to be strictly
         * forbidden. This snippet would replace the above if/else -clause, but would require some distinction between
         * negative time shifts and overnight shifts (so a second date, one date for start of shift and one date for
         * end of shift). Could be rather easily implemented with different parser (and new regex in client) and
         * LocalDateTime objects. Boolean variable overnightShift represents this logic

        if ((shift.isNegative() || shift.isZero()) && overnightShift) {
            shift = Duration.ofHours(24).plus(shift)
        }

        valid = shift.compareTo(maxShift) < 1;

         */

        // Converting to decimal hours via minutes, as second precision input with current implementation is impossible
        shiftHours = shift.toMinutes()/60.0;
    }

    public boolean isValid() {return valid;}

    public Duration getShift() {return shift;}

    public double getHours() {return shiftHours;}
}
