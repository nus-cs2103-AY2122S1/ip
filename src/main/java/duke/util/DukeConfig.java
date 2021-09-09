package duke.util;

import java.util.Arrays;
import java.util.Optional;

/**
 * DukeConfig class encapsulates the user configurations for duke.
 */
public class DukeConfig {
    private DukeDateConfig dateConfig;

    public DukeConfig(DukeDateConfig dateConfig) {
        this.dateConfig = dateConfig;
    }

    public DukeConfig() {
        this.dateConfig = DukeDateConfig.DDMMYYYY;
    }

    /**
     * A factory method to initialise a DukeConfig class.
     * If the config is invalid, return a default config.
     *
     * @param config The string config of the date
     * @return DukeConfig object
     */
    public static DukeConfig of(String config) {
        return DukeDateConfig.matchDukeDateConfig(config)
                .map(DukeConfig::new)
                .orElseGet(DukeConfig::new);
    }

    public String getDateConfig() {
        return this.dateConfig.format;
    }

    /**
     * The enum DukeDateConfig encapsulates all the Date config formats there are.
     */
    enum DukeDateConfig {
        DDMMYYYY("DDMMYYYY"), MMDDYYYY("MMDDYYYY");

        final String format;

        DukeDateConfig(String format) {
            this.format = format;
        }

        public static Optional<DukeDateConfig> matchDukeDateConfig(String config) {
            return Arrays.stream(DukeDateConfig.values())
                    .filter(x -> x.format.equals("config"))
                    .findFirst();
        }
    }
}
