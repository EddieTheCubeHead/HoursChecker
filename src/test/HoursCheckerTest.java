import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class HoursCheckerTest {

    // The constructor does all the heavy lifting, thus I chose to implement all tests for the constructor.
    @Test
    void HoursChecker() {

        // Validity logic tests
        // Basic shift
        assertTrue(new HoursChecker(LocalDate.now(), "08:00", "16:00").isValid());

        // Long shift with weird minutes
        assertTrue(new HoursChecker(LocalDate.now(), "07:35", "18:27").isValid());

        // Exactly 16 hours (longest valid)
        assertTrue(new HoursChecker(LocalDate.now(), "07:00", "23:00").isValid());

        // Too long shift
        assertFalse(new HoursChecker(LocalDate.now(), "06:00", "22:01").isValid());

        // Negative time shift
        assertFalse(new HoursChecker(LocalDate.now(), "12:00", "11:00").isValid());

        // Zero length shift
        assertFalse(new HoursChecker(LocalDate.now(), "11:11", "11:11").isValid());


        // Working hours calculation tests
        // Basic shift
        assertEquals(8, new HoursChecker(LocalDate.now(), "08:00", "16:00").getHours());

        // Long shift with weird minutes
        assertEquals(652/60.0, new HoursChecker(LocalDate.now(), "07:35", "18:27").getHours());

        // Exactly 16 hours (longest valid)
        assertEquals(16, new HoursChecker(LocalDate.now(), "07:00", "23:00").getHours());


        // Error handling tests
        // Invalid input (24:00)
        assertThrows(DateTimeParseException.class, () -> new HoursChecker(LocalDate.now(), "18:00", "24:00"));

        // Invalid input (weird hours)
        assertThrows(DateTimeParseException.class, () -> new HoursChecker(LocalDate.now(), "25:00", "28:00"));
    }
}