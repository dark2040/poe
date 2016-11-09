package org.fabasoad.poe;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.fabasoad.poe.cmd.*;
import org.fabasoad.poe.entities.fleet.farm.FarmType;
import org.fabasoad.poe.entities.fleet.FleetManager;
import org.fabasoad.poe.entities.food.FoodManager;
import org.fabasoad.poe.entities.food.FoodType;
import org.fabasoad.poe.entities.monsters.Monster;
import org.fabasoad.poe.entities.resources.ResourceManager;
import org.fabasoad.poe.entities.temp.TempManager;
import org.fabasoad.poe.entities.validation.ValidationManager;
import org.fabasoad.poe.statistics.StatisticsCollector;
import org.sikuli.script.ImagePath;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Yevhen Fabizhevskyi
 * @date 26.03.2016.
 */
public class Runner {

    private static void setUp() {
        ImagePath.add("org.fabasoad.poe.Runner/img");
        Runtime.getRuntime().addShutdownHook(new Thread(StatisticsCollector.getInstance()::print));
    }

    public static void main(String[] args) throws ParseException {
        Options cmdOptions = OptionsCollector.getInstance().collect();
        final CommandLine cmd = new BasicParser().parse(cmdOptions, args);

        if (OptionHelp.has(cmd)) {
            OptionHelp.print(cmdOptions);
            return;
        }

        setUp();

        Collection<Runnable> actions = collectActions(cmd);

        while (true) {
            actions.forEach(Runnable::run);
            StatisticsCollector.getInstance().print();
        }
    }

    private static Collection<Runnable> collectActions(CommandLine cmd) {
        final Collection<Runnable> result = new ArrayList<>();
        if (OptionTest.has(cmd)) {
            result.add(TempManager.getInstance()::test);
        } else {
            if (!OptionSkipValidation.has(cmd)) {
                result.add(ValidationManager.getInstance()::validateAll);
            }
            if (OptionResources.has(cmd)) {
                result.add(ResourceManager.getInstance()::collect);
            }
            if (OptionFood.has(cmd)) {
                final Collection<FoodType> foodToCollect = OptionCollect.parse(cmd);
                final FoodType foodToGrow = OptionGrow.parse(cmd);
                result.add(() -> FoodManager.getInstance().collectAndGrow(foodToCollect, foodToGrow));
            }
            if (OptionFleet.has(cmd)) {
                final Collection<Monster> monsters = OptionMonsters.parse(cmd);
                final FarmType farmType = OptionFarmType.parse(cmd);
                result.add(() -> FleetManager.getInstance().sendFleets(farmType, monsters));
            }
        }
        return result;
    }
}
