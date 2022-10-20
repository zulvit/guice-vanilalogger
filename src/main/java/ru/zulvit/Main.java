package ru.zulvit;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.jetbrains.annotations.NotNull;
import ru.zulvit.logservice.LoggerService;

public class Main {
    public static void main(@NotNull String[] args) {
        final Injector injector = Guice.createInjector(new LoggerModule(args));
        injector.getInstance(LoggerService.class).waitForInput();
    }
}

