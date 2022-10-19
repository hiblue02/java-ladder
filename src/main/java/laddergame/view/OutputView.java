package laddergame.view;

import laddergame.domain.*;
import laddergame.domain.service.LadderGame;

import java.util.List;
import java.util.Map;


public class OutputView {

    private static final String TAB = " ";
    private static final int WIDTH = 5;
    private static final String COLUMN = "|";
    private static final String LINE = "-";

    private OutputView() {
    }

    public static void printLadder(LadderGame ladderGame) {
        System.out.println("실행결과");
        printPersonNames(ladderGame.getPersonNames());
        printLines(ladderGame.getLadder());
        printRewards(ladderGame.getRewards());
    }

    private static void printLines(Ladder ladder) {
        ladder.getLines().forEach(line -> System.out.println(printLine(line)));
    }

    private static String printLine(Line line) {
        StringBuilder stringBuilder = new StringBuilder();

        line.getValues()
                .forEach(l -> stringBuilder.append(getRung(l).repeat(WIDTH)).append(COLUMN));

        return stringBuilder.toString();
    }

    private static String getRung(boolean point) {
        if (point) {
            return LINE;
        }
        return TAB;
    }

    private static void printPersonNames(List<PersonName> personNames) {
        StringBuilder stringBuilder = new StringBuilder();
        personNames.forEach(n -> stringBuilder.append(lpad(n.toString())));
        System.out.println(stringBuilder);
    }

    private static void printRewards(Rewards rewards) {
        StringBuilder stringBuilder = new StringBuilder();
        rewards.getRewards().forEach(n -> stringBuilder.append(lpad(n.toString())));
        System.out.println(stringBuilder);
    }

    private static String lpad(String name) {
        return TAB.repeat(WIDTH).substring(name.length()) + name + TAB;
    }

    public static void printResult(Map<PersonName, Reward> result) {
        System.out.println("실행 결과");
        result.forEach((key, value) -> System.out.println(key + " : " + value));

    }
}
