import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class DateTimeParser extends Parser<LocalDate> {
	private final ArrayList<DateTimeFormatter> dateTimeFormatters = new ArrayList<>();
	
	public DateTimeParser(String[] dateFormats) {
		for (String format : dateFormats) {
			dateTimeFormatters.add(DateTimeFormatter.ofPattern(format));
		}
	}
	
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
