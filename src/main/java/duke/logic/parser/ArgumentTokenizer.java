package duke.logic.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArgumentTokenizer {
    private String fullArgString;
    private List<String> flags;
    private Map<String, String> flagToArgument;

    public ArgumentTokenizer(String fullArgString, String... flags) {
        this.fullArgString = fullArgString.trim();
        this.flags = Arrays.asList(flags);
        this.flagToArgument = new HashMap<>();
        tokenize();
    }

    private void tokenize() {
        Map<String, Integer> flagToIndex = new HashMap<>();
        List<String> presentFlags = new ArrayList<>();  // flags that actually appear in fullArgString
        for (String flag : flags) {
            int index = fullArgString.indexOf(flag);
            if (index != -1) {
                flagToIndex.put(flag, index);
                presentFlags.add(flag);
            }
        }
        // sort by order of appearance
        presentFlags.sort((flag1, flag2) -> flagToIndex.get(flag1) - flagToIndex.get(flag2));
        // dummy flag at the end of fullArgString
        presentFlags.add("");
        flagToIndex.put("", fullArgString.length());
        // slice arguments
        for (int i = 0; i < presentFlags.size() - 1; i++) {
            String currFlag = presentFlags.get(i);
            String nextFlag = presentFlags.get(i + 1);
            int beginIndex = flagToIndex.get(currFlag) + currFlag.length();
            int endIndex = flagToIndex.get(nextFlag);
            String arg = fullArgString.substring(beginIndex, endIndex);
            flagToArgument.put(currFlag, arg.trim());
        }
    }

    public String getArgumentOf(String flag) {
        return flagToArgument.getOrDefault(flag, null);
    }
}
