package duke.logic.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Tokenizes the argument for the update command. The argument string is of the form:
 * {@code -d newDescription -by newByTime} etc. where "-d", "-by", "-at", "-end" are
 * possible flags. A flag's value can be an empty string e.g. {@code -d -by 11-09-2021 1030}
 * in which case flag "-d" has an empty string value. Leading and trailing whitespaces
 * of a flag value will be discarded. When a flag is repeated, its value will be taken
 * as the value of the last occurrence. Unrecognised flags are "absorbed" into
 * the value of the immediately preceding flag, e.g. {@code -d Hello -foo Hi} will
 * return {@code Hello -foo Hi} as the value for flag "-d".
 */
public class ArgumentTokenizer {

    /**
     * Tokenizes an argument string and returns a {@code Map} object mapping flags to their
     * respective values. Only the given flags will be checked for in the arguments string.
     * When a flag is repeated, its value will be taken as the value of the last occurrence.
     * If a flag is given but does not appear in the argument string, it will also <em>not</em>
     * appear in the returned {@code Map}.
     *
     * @param fullArgString The argument string to be tokenized.
     * @param flags Flags to tokenize with.
     * @return Map object mapping flags to their values.
     */
    public static Map<String, String> tokenize(String fullArgString, String... flags) {
        List<FlagOccurrence> flagOccurrences = findOccurrences(fullArgString, flags);
        return extractValues(fullArgString, flagOccurrences);
    }

    /**
     * Finds <em>all</em> occurrences of <em>all</em> flags in the argument string.
     *
     * @param fullArgString The argument string.
     * @param flags The flags to find.
     * @return A List of flag occurrences of the form (flag, index of appearance).
     */
    private static List<FlagOccurrence> findOccurrences(String fullArgString, String[] flags) {
        List<FlagOccurrence> flagOccurrences = new ArrayList<>();
        for (String flag : flags) {
            if (!fullArgString.contains(flag)) {
                continue;
            }
            int index = fullArgString.indexOf(flag); // first occurrence
            while (index != -1) { // while there is still an occurrence
                flagOccurrences.add(new FlagOccurrence(flag, index));
                index = fullArgString.indexOf(flag, index + 1); // continue search in the rest of the string
            }
        }
        return flagOccurrences;
    }

    /**
     * Extracts the value for each flag, and returns a {@code Map} object that maps each
     * flag to its value. Only flags that appear in the argument string will be included
     * in the returned Map object.
     *
     * @param fullArgString The argument string.
     * @param flagOccurrences Occurrences of all flags in the argument string.
     * @return Map object mapping flags to their values.
     */
    private static Map<String, String> extractValues(String fullArgString, List<FlagOccurrence> flagOccurrences) {
        Map<String, String> flagToValue = new HashMap<>();

        // Sort by order of appearance
        Collections.sort(flagOccurrences);

        // Insert a dummy flag at the end of fullArgString
        flagOccurrences.add(new FlagOccurrence("", fullArgString.length()));

        // Slice argument string to obtain values
        for (int i = 0; i < flagOccurrences.size() - 1; i++) {
            FlagOccurrence curr = flagOccurrences.get(i);
            FlagOccurrence next = flagOccurrences.get(i + 1);
            int beginIndex = curr.index + curr.flag.length();
            int endIndex = next.index;
            String value = fullArgString.substring(beginIndex, endIndex);
            flagToValue.put(curr.flag, value.trim());
        }
        return flagToValue;
    }

    /**
     * Represents a flag occurrence in the given argument string.
     * Stores the flag and the index it appears at.
     */
    private static class FlagOccurrence implements Comparable<FlagOccurrence> {
        private String flag;
        private Integer index;

        public FlagOccurrence(String flag, Integer index) {
            this.flag = flag;
            this.index = index;
        }

        @Override
        public int compareTo(FlagOccurrence o) {
            return this.index - o.index;
        }
    }

}
