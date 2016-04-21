package org.fabasoad.poe.cmd;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

/**
 * @author Yevhen Fabizhevskyi
 * @date 21.04.2016.
 */
public class OptionFleet extends Option {

    private static String COMMAND = "fl";

    public OptionFleet() {
        super(COMMAND, "fleet", false, "Command to send the fleet.");
    }

    public static boolean has(CommandLine cmd) {
        return cmd.hasOption(COMMAND);
    }
}