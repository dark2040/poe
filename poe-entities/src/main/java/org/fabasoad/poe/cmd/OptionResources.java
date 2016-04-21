package org.fabasoad.poe.cmd;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

/**
 * @author Yevhen Fabizhevskyi
 * @date 21.04.2016.
 */
public class OptionResources extends Option {

    private static final String COMMAND = "r";

    public OptionResources() {
        super(COMMAND, "resources", false, "Command to collect the resources.");
    }

    public static boolean has(CommandLine cmd) {
        return cmd.hasOption(COMMAND);
    }
}