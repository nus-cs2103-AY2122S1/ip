public enum RegexType {
    GET_DESCRIPTION_REGEX("/.+"),
    GET_AT_REGEX(".+/at "),
    GET_BY_REGEX(".+/by "),
    SPACE_REGEX("\\s"),
    START_LINE_REGEX("^"),
    DIGITS_REGEX("\\d+"),
    DEADLINE_REGEX(".+/by.+"),
    TASK_ADDED_LINE("Got it. I've added this task:"),
    DUKE_TXT("data/duke.txt"),
    EVENT_REGEX(".+/at.+");


    public final String str;

    RegexType(String str) {
        this.str = str;
    }

    public String getRegexType() {
        return str;
    }

}
