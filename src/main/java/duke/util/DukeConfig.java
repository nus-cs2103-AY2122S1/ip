package duke.util;

import duke.Duke;

import java.util.Arrays;
import java.util.Optional;


public class DukeConfig {
    enum DukeDateConfig {
        DDMMYYYY("DDMMYYYY"),
        MMDDYYYY("MMDDYYYY");

        final String format;

        DukeDateConfig(String format) {
            this.format = format;
        }

        public static Optional<DukeDateConfig> getDukeDateConfig(String config) {
            return Arrays.stream(DukeDateConfig.values()).filter(x -> x.format.equals("config")).findFirst();
        }
    }

    DukeDateConfig dateConfig;

    public DukeConfig(DukeDateConfig dateConfig) {
        this.dateConfig = dateConfig;
    }

    public DukeConfig() {
        this.dateConfig = DukeDateConfig.DDMMYYYY;
    }

    public static DukeConfig of (String config) {
        return DukeDateConfig.getDukeDateConfig(config).map(DukeConfig::new).orElseGet(DukeConfig::new);
    }

    public String getDateConfig() {
        return this.dateConfig.format;
    }
}
