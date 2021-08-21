public class Parser {
    private static String findDatetime(String input, String argument) {
        int argumentIndex = input.lastIndexOf(argument);
        String output = input.substring(argumentIndex + argument.length());

        if (output.replaceAll("\\s", "").length() < 1) {
            return null;
        }

        return output.stripLeading().stripTrailing();
    }

    public static String findDeadlineDatetime(String input) {
        return findDatetime(input, "/by");
    }

    public static String[] findEventDatetime(String input) {
        String output = input;

        if (input.contains("/at")) {
            output = findDatetime(input, "/at");
        }
        if (output != null) {
            String[] outputArr = output.trim().split("/to");

            for (int i = 0; i < outputArr.length; i++) {
                outputArr[i] = outputArr[i].trim();
            }
            return outputArr;
        }

        return null;
    }

    public static String findDescription(String input) {
        String cmd = input.split("\\s+", 2)[1];

        if (cmd.contains("/by"))
            return cmd.split("/by")[0];
        else if (cmd.contains("/at"))
            return cmd.split("/at")[0];
        else
            return null;
    }
}
