import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String input;
    private String[] wordArray;
    private String command;
    private String description;
    private LocalDateTime time;

    public Parser(String input) {
        this.input = input;
        this.wordArray = input.split(" ");

        if (wordArray.length == 0) {
            return;
        } else {
            this.command = wordArray[0];
        }

        String newDescription = "";
        String newTime = "";
        int pointer = 1;
        while (pointer < wordArray.length
                && !wordArray[pointer].equals("/by")
                && !wordArray[pointer].equals("/at")) {
            newDescription += wordArray[pointer] + " ";
            pointer++;
        }
        pointer++;
        newDescription = newDescription.trim();   // delete the last space

        while (pointer < wordArray.length) {
            newTime += wordArray[pointer] + " ";
            pointer++;
        }
        newTime = newTime.trim();   // delete the last space

        this.description = newDescription;
        this.time = LocalDateTime.parse(newTime, FORMATTER);
    }

    public String getCommand() {
        return this.command;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return this.input;
    }
}
