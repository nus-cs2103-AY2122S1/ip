package duke;

import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    Parser() {}

    public ArrayList<String> parseInput (String input) throws DukeException {
        ArrayList<String> terms = new ArrayList<>();
        this.parseString(input, terms);
        String command = "";
        if (terms.isEmpty()) {
            throw new DukeException("Duke can't find any commands :(");
        } else {
            command = terms.remove(0);
        }
        this.parseEntry(terms);
        String entry = terms.isEmpty() ? "" : terms.remove(0);
        String timing = terms.isEmpty() ? "" : this.parseTiming(terms);
        return new ArrayList<>(Arrays.asList(command, entry, timing));
    }

    private void parseString (String input, ArrayList<String> terms) {
        //Function to store all terms in input as separate Strings (separated by space in the input)
        int length = input.length();
        if (length >= 1) {
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
        if (!terms.isEmpty()) {
            StringBuilder entry = new StringBuilder();
            //Combine All Strings Until End of List or '/' character is found
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
