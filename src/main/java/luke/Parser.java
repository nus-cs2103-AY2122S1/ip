package luke;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class that implements method to parse user inputs.
 */
public class Parser {

    /**
     * Constructor for Parser.
     */
    Parser() {}

    /**
     * Returns parsed input in an Arraylist.
     *
     * @param input String input from user.
     * @return ArrayList of command, entry, timing (if present).
     * @throws LukeException Error is thrown if Command is invalid.
     */
    public ArrayList<String> parseInput(String input) throws LukeException, AssertionError {
        ArrayList<String> terms = new ArrayList<>();
        this.parseString(input, terms);
        String command = "";
        if (terms.isEmpty()) {
            throw new LukeException("Luke can't find any commands :((");
        } else {
            command = terms.remove(0);
        }
        assert isValidCommand(command) : "Invalid Command! Luke can't understand what you mean :((";
        this.parseEntry(terms);
        String entry = terms.isEmpty() ? "" : terms.remove(0);
        String timing = terms.isEmpty() ? "" : this.parseTiming(terms);
        return new ArrayList<>(Arrays.asList(command, entry, timing));
    }

    private boolean isValidCommand(String command) {
        boolean isValid = false;
        for (String knownCommand : Luke.commands) {
            if (knownCommand.equals(command)) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }

    private void parseString(String input, ArrayList<String> terms) throws AssertionError {
        // Function to store all terms in input as separate Strings (separated by space in the input)
        int length = input.length();
        assert length >= 1 : "Invalid String Input";
        StringBuilder currentWord = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char currentChar = input.charAt(i);
            if (currentChar == ' ') {
                terms.add(currentWord.toString());
                currentWord.setLength(0);
            } else {
                currentWord.append(currentChar);
            }
        }
        terms.add(currentWord.toString());
    }

    private String parseTiming(ArrayList<String> terms) {
        if (terms.get(0).startsWith("/")) {
            terms.remove(0);
            StringBuilder timing = new StringBuilder();
            int len = terms.size();
            for (int i = 0; i < len; i++) {
                if (i != len - 1){
                    timing.append(terms.get(i)).append(" ");
                } else {
                    timing.append(terms.get(i));
                }
            }
            return timing.toString();
        } else {
            return "";
        }
    }

    private void parseEntry(ArrayList<String> terms) {
        boolean isTermsNotEmpty = !terms.isEmpty();
        if (isTermsNotEmpty) {
            StringBuilder entry = new StringBuilder();
            // Combine All Strings Until End of List or '/' character is found
            ArrayList<String> termsCopy = new ArrayList<>(terms);
            for (String term : termsCopy) {
                if (term.startsWith("/")) {
                    String entryDesc = entry.toString();
                    terms.add(0, entryDesc.substring(0, entryDesc.length()-1));
                    return;
                } else {
                    entry.append(term).append(' ');
                    terms.remove(0);
                }
            }
            String entryDesc = entry.toString();
            terms.add(0, entryDesc.substring(0, entryDesc.length()-1));
        }
    }
}