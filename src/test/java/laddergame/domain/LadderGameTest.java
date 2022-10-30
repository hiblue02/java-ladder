package laddergame.domain;

import laddergame.domain.service.LadderGame;
import laddergame.dto.RewardRecord;
import laddergame.dto.RewordRecords;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class LadderGameTest {

    @Test
    @DisplayName("결과를 입력받을 사람 이름이 all이면 no exception")
    void result_name_when_all() {
        //given
        PersonName all = new PersonName("all");

        List<Line> lines = new ArrayList<>();

        lines.add(new Line(Arrays.asList(new Point(0, Direction.of(false, true))
                , new Point(1, Direction.of(true, false))
                , new Point(2, Direction.of(false, true))
                , new Point(3, Direction.of(true, false)))));

        lines.add(new Line(Arrays.asList(new Point(0, Direction.of(false, false))
                , new Point(1, Direction.of(false, true))
                , new Point(2, Direction.of(true, false))
                , new Point(3, Direction.of(false, false)))));

        lines.add(new Line(Arrays.asList(new Point(0, Direction.of(false, true))
                , new Point(1, Direction.of(true, false))
                , new Point(2, Direction.of(false, false))
                , new Point(3, Direction.of(false, false)))));

        lines.add(new Line(Arrays.asList(new Point(0, Direction.of(false, false))
                , new Point(1, Direction.of(false, true))
                , new Point(2, Direction.of(true, false))
                , new Point(3, Direction.of(false, false)))));

        lines.add(new Line(Arrays.asList(new Point(0, Direction.of(false, true))
                , new Point(1, Direction.of(true, false))
                , new Point(2, Direction.of(false, true))
                , new Point(3, Direction.of(true, false)))));

        People people = new People("aaa,bbb,ccc".split(","));
        Rewards rewards = new Rewards("꽝,5000,꽝,3000".split(","));

        LadderGame ladderGame = new LadderGame(people, rewards, new Ladder(lines));


        //then
        assertThatNoException().isThrownBy(() -> ladderGame.makeResult(all));
    }


    @Test
    @DisplayName("결과를 입력받을 사람 이름이 all도 아니고, 사다리 게임에 참여한 사람 이름도 아니면 오류")
    void result_name_when_not_contain_people() {
        //given
        PersonName ddd = new PersonName("ddd");
        LadderGame ladderGame = new LadderGame(new People("aaa,bbb,ccc".split(",")), null, null);
        //then
        assertThatIllegalArgumentException().isThrownBy(() -> ladderGame.makeResult(ddd));
    }

    @Test
    @DisplayName("실행결과")
    void result_reward_one() {
        //given
        Rewards rewards = new Rewards("꽝,5000,꽝,3000".split(","));
        People people = new People("pobi,honux,crong,jk".split(","));

        List<Line> lines = new ArrayList<>();
        lines.add(new Line(Arrays.asList(new Point(0, Direction.of(false, true))
                , new Point(1, Direction.of(true, false))
                , new Point(2, Direction.of(false, true))
                , new Point(3, Direction.of(true, false)))));

        lines.add(new Line(Arrays.asList(new Point(0, Direction.of(false, false))
                , new Point(1, Direction.of(false, true))
                , new Point(2, Direction.of(true, false))
                , new Point(3, Direction.of(false, false)))));

        lines.add(new Line(Arrays.asList(new Point(0, Direction.of(false, true))
                , new Point(1, Direction.of(true, false))
                , new Point(2, Direction.of(false, false))
                , new Point(3, Direction.of(false, false)))));

        lines.add(new Line(Arrays.asList(new Point(0, Direction.of(false, false))
                , new Point(1, Direction.of(false, true))
                , new Point(2, Direction.of(true, false))
                , new Point(3, Direction.of(false, false)))));

        lines.add(new Line(Arrays.asList(new Point(0, Direction.of(false, true))
                , new Point(1, Direction.of(true, false))
                , new Point(2, Direction.of(false, true))
                , new Point(3, Direction.of(true, false)))));

        LadderGame ladderGame = new LadderGame(people, rewards, new Ladder(lines));
        //then
        PersonName pobi = new PersonName("pobi");
        RewordRecords rewordRecords = ladderGame.makeResult(pobi);
        RewardRecord rewardRecord = rewordRecords.getRecords().get(0);
        //when
        assertAll(
                () -> assertThat(rewardRecord.getPlayerName()).isEqualTo("pobi"),
                () -> assertThat(rewardRecord.getReword()).isEqualTo("꽝")
        );

    }

    @Test
    @DisplayName("실행결과")
    void result_reward_all() {
        //given
        Rewards rewards = new Rewards("꽝,5000,꽝,3000".split(","));
        People people = new People("pobi,honux,crong,jk".split(","));

        List<Line> lines = new ArrayList<>();
        lines.add(new Line(Arrays.asList(new Point(0, Direction.of(false, true))
                , new Point(1, Direction.of(true, false))
                , new Point(2, Direction.of(false, true))
                , new Point(3, Direction.of(true, false)))));

        lines.add(new Line(Arrays.asList(new Point(0, Direction.of(false, false))
                , new Point(1, Direction.of(false, true))
                , new Point(2, Direction.of(true, false))
                , new Point(3, Direction.of(false, false)))));

        lines.add(new Line(Arrays.asList(new Point(0, Direction.of(false, true))
                , new Point(1, Direction.of(true, false))
                , new Point(2, Direction.of(false, false))
                , new Point(3, Direction.of(false, false)))));

        lines.add(new Line(Arrays.asList(new Point(0, Direction.of(false, false))
                , new Point(1, Direction.of(false, true))
                , new Point(2, Direction.of(true, false))
                , new Point(3, Direction.of(false, false)))));

        lines.add(new Line(Arrays.asList(new Point(0, Direction.of(false, true))
                , new Point(1, Direction.of(true, false))
                , new Point(2, Direction.of(false, true))
                , new Point(3, Direction.of(true, false)))));

        LadderGame ladderGame = new LadderGame(people, rewards, new Ladder(lines));
        //then
        PersonName all = new PersonName("all");

        RewordRecords rewordRecords = ladderGame.makeResult(all);
        RewardRecord pobiRecord = rewordRecords.getRecords().get(0);
        RewardRecord honuxRecord = rewordRecords.getRecords().get(1);
        RewardRecord crongRecord = rewordRecords.getRecords().get(2);
        RewardRecord jkRecord = rewordRecords.getRecords().get(3);
        //when
        assertAll(
                () -> assertThat(pobiRecord.getPlayerName()).isEqualTo("pobi"),
                () -> assertThat(pobiRecord.getReword()).isEqualTo("꽝"),
                () -> assertThat(honuxRecord.getPlayerName()).isEqualTo("honux"),
                () -> assertThat(honuxRecord.getReword()).isEqualTo("3000"),
                () -> assertThat(crongRecord.getPlayerName()).isEqualTo("crong"),
                () -> assertThat(crongRecord.getReword()).isEqualTo("꽝"),
                () -> assertThat(jkRecord.getPlayerName()).isEqualTo("jk"),
                () -> assertThat(jkRecord.getReword()).isEqualTo("5000")
        );

    }

}