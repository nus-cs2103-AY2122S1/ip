import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class BotTemporalUnit {

    BotTemporalUnit(){}

    public LocalDateTime convertStringToTemporalData(String data) {
        return LocalDateTime.parse(
                data,
                DateTimeFormatter.ISO_DATE_TIME
                );
    }

    void decipherTemproalDataInput() {

    }


}
