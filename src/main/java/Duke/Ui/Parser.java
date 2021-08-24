package Duke.Ui;

public class Parser {
    public static UserInput parse(String raw) {
        String[] splitInput = raw.trim().split("\\s+", 2);
        String keyword = splitInput[0], args = splitInput.length > 1 ? splitInput[1] : "";
        return new UserInput(raw, keyword, args);
    }
}
