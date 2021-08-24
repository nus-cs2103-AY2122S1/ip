import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Parser {

    public static Map<String, String> parseTextFromInput(String input) throws DukeException {
        input = input.trim();
        String type = "";
        String description = "";
        String time = "";

        try {
            if (input.contains("todo")) {
                type = "T";
                description = input.split(" ", 2)[1];
            } else if (input.contains("deadline")) {
                try {
                    type = "D";
                    description = input.split(" ", 2)[1].split(" /by ", 2)[0];
                    time = input.split(" ", 2)[1].split(" /by ", 2)[1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("Use the format --deadline  xx /by dd-MM-yyy HH:mm--");
                }
            } else if (input.contains("event")) {
                try {
                    type = "E";
                    description = input.split(" ", 2)[1].split(" /at ", 2)[0];
                    time = input.split(" ", 2)[1].split(" /at ", 2)[1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("Use the format --event xx /at dd-MM-yyy HH:mm--");
                }
            }
            return Map.of("type", type, "description", description, "time", time);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The description of a task cannot be empty.");
        }

    }

    public static Map<String, String> parseTextFromFile(String input) {
        String type = input.substring(1, 2);
        String status = input.substring(4, 5);
        String description = input.split("\\(")[0].substring(7).trim();
        String time = "";
        String finalText = "";

        if (type.equals("D") || type.equals("E")) {
            String temp = input.split(":", 2)[1];
            time = temp.substring(1, temp.length() - 1);
            if (time.length() > 11) {
                String date = LocalDate.parse(time.substring(0, 11), DateTimeFormatter.ofPattern("dd MMM yyyy")).toString();
                time = date + " " + time.substring(12);
            } else if (time.contains(":")) {
            } else {
                time = LocalDate.parse(time, DateTimeFormatter.ofPattern("dd MMM yyyy")).toString();
            }
        }

        switch (type) {
        case "T": finalText = "todo " + description;
            break;
        case "D": finalText = "deadline " + description + " /by " + time;
            break;
        case "E": finalText = "event " + description + " /at " + time;
            break;
        }

        return Map.of("finalText", finalText, "status", status);
   }
}
