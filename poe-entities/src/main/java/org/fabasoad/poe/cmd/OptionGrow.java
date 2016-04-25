package org.fabasoad.poe.cmd;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.fabasoad.poe.core.UsedViaReflection;
import org.fabasoad.poe.entities.food.FoodType;

/**
 * @author Yevhen Fabizhevskyi
 * @date 22.04.2016.
 */
@UsedViaReflection
public class OptionGrow extends Option {

    private static final String COMMAND = "g";

    public OptionGrow() {
        super(COMMAND, "grow", true, buildDescription());
    }

    private static String buildDescription() {
        return String.format("Command to grow the food. Used only in combination with -fo command.%1$sUsage: -fo " +
                "-%2$s <food>.%1$sDefault: %3$s.%1$sPossible values: %4$s", System.getProperty("line.separator"),
                COMMAND, FoodType.defaultToGrowAsString(), FoodType.valuesAsString());
    }

    public static FoodType parse(CommandLine cmd) {
        return FoodType.valueOf(cmd.getOptionValue(COMMAND, FoodType.defaultToGrowAsString()).trim().toUpperCase());
    }
}