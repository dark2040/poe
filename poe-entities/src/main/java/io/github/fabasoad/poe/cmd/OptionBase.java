package io.github.fabasoad.poe.cmd;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

abstract class OptionBase extends Option {

    static Properties DEFAULT = new Properties();

    static {
        try (InputStream stream = OptionBase.class.getClassLoader().getResourceAsStream("default.properties")) {
            DEFAULT.load(stream);
        } catch (IOException e) {
            DEFAULT.clear();
        }
    }

    OptionBase(String opt, String longOpt, boolean hasArg, String description) throws IllegalArgumentException {
        super(opt, longOpt, hasArg, description);
    }

    static String getPropertyOrDefault(CommandLine cmd, String key, String defaultValue) {
        return cmd.getOptionValue(key, DEFAULT.getProperty(key, defaultValue));
    }
}
