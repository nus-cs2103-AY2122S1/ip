import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class Parser {


    public static Map<String, String> parseCommand(String text) {
        HashMap<String, String> inputMap = new HashMap<>();
        String[] splitted = text.split("\\s");

        String key = splitted[0];
        StringBuilder arguments = new StringBuilder();
        for (int i = 1; i < splitted.length; i++) {
            if (splitted[i].startsWith("/")) {
                if (arguments.length() > 0) {
                    arguments.deleteCharAt(arguments.length() - 1);
                    inputMap.put(key, arguments.toString());
                    arguments.setLength(0);
                    key = splitted[i];
                } else {
                    inputMap.put(key, null);
                    key = splitted[i];
                }
            } else {
                arguments.append(String.format("%s ", splitted[i]));
            }
        }
        if (arguments.length() > 0) {
            arguments.deleteCharAt(arguments.length() - 1);
            inputMap.put(key, arguments.toString());
        } else {
            if (!inputMap.containsKey(key)) {
                inputMap.put(key, null);
            }
        }

//        for (String name: inputMap.keySet()) {
//            String value = inputMap.get(name);
//            System.out.println("key: " + name + " value: " + value);
//        }
        return inputMap;
    }

    public static Optional<Integer> parseInt(String text) {
        try {
            return Optional.of(Integer.parseInt(text));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
