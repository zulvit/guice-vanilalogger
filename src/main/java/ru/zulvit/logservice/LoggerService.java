package ru.zulvit.logservice;

@FunctionalInterface
public interface LoggerService {
    void log(String msg);
}
