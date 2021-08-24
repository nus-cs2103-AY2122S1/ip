import java.util.Arrays;

class Parser {
    static String getCommand(String input) {
        if (input.length() > 0) {
            return input.split(" ")[0];
        } else {
            return "";
        }
    }

    static String[] getArguments(String input) {
        if (input.length() > 0) {
            String[] split = input.split(" ");
            if (split.length >= 2)
                return Arrays.copyOfRange(split, 1, split.length);
        }
        return new String[]{};
    }
}
