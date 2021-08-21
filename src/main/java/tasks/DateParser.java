package tasks;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateParser {

  public static final boolean isDebug = true;
  public static final String REGEX_DATE_NUMERIC =
    "(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])";
  public static final String REGEX_TIME_24HR = "(([01]\\d|2[0-3])[0-5]\\d)";

}

/*
Notes for REGEX:

(19|20)\d\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])
1900-01-01 to 2099-12-31, with space, dot, slash or hyphen as separators

([0-2]?\d|3[0-1])[- /][a-zA-Z]{3,}[- /](19|20)\d{2}
2-xYz
2-xyZ
2-Xyz
02-XYZ to 31-XYZ
followed by another separator then a year (1900-2099)
separator can be space, hyphen or forward slash

(([01]\d|2[0-3])[0-5]\d)
0000 - 2359

(0?[1-9]|1[0-2])[:.]?([0-5][0-9])?\s?(pm|am|PM|AM)
2 PM
2.30 PM
02.30 PM
02:30 PM
hours from 1-12, minutes from 00 - 59
followed by AM/PM/pm/am, preceding space not necessary.
 */
