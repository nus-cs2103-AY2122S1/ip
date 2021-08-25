package bot.assembly.function;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BotTemporalUnit {

    public BotTemporalUnit(){}

    public LocalDateTime convertStringToTemporalData(String data) {
        return LocalDateTime.parse(
                data,
                DateTimeFormatter.ISO_LOCAL_DATE_TIME
                );
    }

}
