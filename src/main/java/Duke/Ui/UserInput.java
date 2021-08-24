package Duke.Ui;

import java.util.Scanner;

public class UserInput {
    private static final String INPUT_PROMPT = "> ";
    private static final Scanner scanner = new Scanner(System.in);

    private String raw, keyword, args;

    public void readAndParse() {
        System.out.print(INPUT_PROMPT);
        this.raw = UserInput.scanner.nextLine();
        String[] splitInput = this.raw.trim().split("\\s+", 2);
        this.keyword = splitInput[0];
        this.args = splitInput.length > 1 ? splitInput[1] : "";
    }

    public String getKeyword() {
        return this.keyword;
    }

    public String getArgs() {
        return this.args;
    }

    public String getRaw() {
        return this.raw;
    }
}
