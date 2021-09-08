package bot.assembly.function;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class that handles the conversion of datetime input in the format of ISO_LOCAL_DATE_TIME
 * from String to LocalDateTime
 */
public class BotTemporalUnit {

    /**
     * Constructor
     */
    public BotTemporalUnit() {}

    /**
     * A method that converts datetime input from string to LocalDateTime
     * @param data datetime in ISO_LOCAL_DATE_TIME format
     * @return LocalDateTime
     */
    public LocalDateTime convertStringToTemporalData(String data) {
        return LocalDateTime.parse(data, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

}
