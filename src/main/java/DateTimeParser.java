import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser extends Parser<LocalDate> {
	public static final DateTimeFormatter[] dateTimeFormatters = new DateTimeFormatter[] {
			DateTimeFormatter.ofPattern("yyyy-MM-dd"),
			DateTimeFormatter.ofPattern("d/M/yyyy"),
	};
	
	public LocalDate parse(String s) {
		for (DateTimeFormatter formatter : dateTimeFormatters) {
			try {
				return LocalDate.parse(s, formatter);
			} catch (DateTimeParseException e) {
				// Don't need to do anything here
			}
		}
		return null;
	}
}
