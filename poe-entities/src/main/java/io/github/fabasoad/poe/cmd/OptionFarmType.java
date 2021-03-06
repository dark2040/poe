package io.github.fabasoad.poe.cmd;

import org.apache.commons.cli.CommandLine;
import io.github.fabasoad.annotations.UsedViaReflection;
import io.github.fabasoad.poe.entities.fleet.farm.FarmType;

@UsedViaReflection
public class OptionFarmType extends OptionBase {

    private static final String COMMAND = "ft";

    public OptionFarmType() {
        super(COMMAND, "farm-type", true, buildDescription());
    }

    private static String buildDescription() {
        return String.format("Command to set farm strategy. Used only in combination with -fl command.%1$s" +
                        "Usage: -fl -%2$s <some_strategy>.%1$sDefault value: %3$s.%1$sPossible values: %4$s",
                System.getProperty("line.separator"), COMMAND, FarmType.getDefaultAsString(), FarmType.valuesAsString());
    }

    public static FarmType parse(CommandLine cmd) {
        return FarmType.valueOf(getPropertyOrDefault(cmd, COMMAND, FarmType.getDefaultAsString()).trim().toUpperCase());
    }
}
