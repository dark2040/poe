package org.fabasoad.poe.cmd;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.fabasoad.poe.entities.food.FoodType;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Yevhen Fabizhevskyi
 * @date 22.04.2016.
 */
public class OptionCollect extends Option {

    private static final String COMMAND = "c";

    public OptionCollect() {
        super(COMMAND, "collect", true, buildDescription());
    }

    private static String buildDescription() {
        return String.format("Command to collect the food. Used only in combination with -fo command.%1$s" +
                "Usage: -fo -c <food_1,food_2,...>.%1$sDefault: %2$s.%1$sPossible values: %3$s",
                System.getProperty("line.separator"), FoodType.defaultToCollectAsString(), FoodType.valuesAsString());
    }

    public static Collection<FoodType> parse(CommandLine cmd) {
        return Arrays.stream(cmd.getOptionValue(COMMAND, FoodType.defaultToCollectAsString()).split(","))
                    .map(v -> FoodType.valueOf(v.trim().toUpperCase()))
                    .collect(Collectors.toList());
    }
}
