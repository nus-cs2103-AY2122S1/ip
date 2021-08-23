public class InputChecker {
    public static void checkMissingArguments(String[] sections, String errorMessage) throws MissingArgumentException {
        if (sections.length != 2 || sections[1].isEmpty()) {
            throw new MissingArgumentException(errorMessage);
        }
    }
}
