package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");

        String name = "Anastasia";
        byte age = 27;
        char sex = 'F';
        float weight = 58.20f;
        double height = 169.09;
        boolean hasJob = true;
        long salary = 100000L;
        short work_exp = 7;
        int savings = 200000;

        LOG.debug("User Info name: {}, age: {}, sex: {}, weight: {}, height: {}, hasJob: {}, salary: {}, work_exp: {}, savings: {}",
                name, age, sex, weight, height, hasJob, salary, work_exp, savings);

    }
}