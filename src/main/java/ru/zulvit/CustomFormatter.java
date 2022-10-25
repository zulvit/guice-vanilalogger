package ru.zulvit;

import org.jetbrains.annotations.NotNull;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {
    @Override
    public String format(@NotNull LogRecord record) {
        return record.getMessage() + "\r\n";
    }
}
