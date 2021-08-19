public enum RegexType {
    GET_DESCRIPTION_REGEX("/.+"),
    GET_AT_REGEX(".+/at "),
    GET_BY_REGEX(".+/by "),
    SPACE_REGEX("\\s"),
    START_LINE_REGEX("^"),
    DIGITS_REGEX("\\d+"),
    DEADLINE_REGEX(".+/by.+"),
    EVENT_REGEX(".+/at.+");


    public final String str;

    private RegexType(String str) {
        this.str = str;
    }

    public String getRegexType() {
        return str;
    }

}
