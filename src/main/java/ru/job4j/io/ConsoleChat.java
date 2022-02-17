package ru.job4j.io;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "finish";
    private static final String STOP = "stop";
    private static final String CONTINUE = "continue";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        List<String> answers = readPhrases();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("You can ask any question that can be answered with yes or no.\n" +
                    "Enter finish - to end program, stop - to pause, continue - to continue.");
            String question = reader.readLine();
            String answer;
            while (!question.equals(OUT)) {
                if (question.equals(STOP) || question.equals(CONTINUE)) {
                    log.add(question);
                    while (!question.equals(CONTINUE)) {
                        question = reader.readLine();
                        log.add(question);
                    }
                } else {
                    answer = answers.get((int) (Math.random() * answers.size()));
                    System.out.println(answer);
                    log.add(question);
                    log.add(answer);
                }
                question = reader.readLine();
            }

            log.add(question);
            saveLog(log);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            in.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(path, true)))) {
            for (String l : log) {
                out.write(l);
                out.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/conversation.txt", "./data/answers.txt");
        cc.run();
    }
}