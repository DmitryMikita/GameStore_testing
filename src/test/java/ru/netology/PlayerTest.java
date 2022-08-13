package ru.netology;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    //должен показать сыгрнное время одной игры
    public void shouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    //должен суммировать сыгранное время двух игр одного жанра
    public void shouldSumGenreTwoGames() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Герои меча и магии", "Рпг");
        Game game1 = store.publishGame("Герои меча и магии 2", "Рпг");
        Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);
        player.installGame(game2);
        player.play(game, 8);
        player.play(game1, 5);
        player.play(game2, 2);

        int expected = 13;
        int actual = player.sumGenre("Рпг");
        assertEquals(expected, actual);
    }

    @Test
    //дожен показывать уведомление о неустановленной игре
    public void shouldThrowRunTimeException() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Герои меча и магии", "Рпг");
        Game game1 = store.publishGame("Герои меча и магии 2", "Рпг");
        Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);


        assertThrows(RuntimeException.class, () -> {
            player.play(game2, 2);

        });
    }

    @Test
    //должен показывать жанр игры, в которую играли больше всего
    public void shouldShowTheGenreGameWasPlayedTheMost() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Герои меча и магии", "Рпг");
        Game game1 = store.publishGame("Герои меча и магии 2", "Рпг");
        Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);
        player.installGame(game2);
        player.play(game, 8);
        player.play(game1, 5);
        player.play(game2, 2);

        Game expected = game;
        Game actual = player.mostPlayerByGenre("Рпг");
        assertEquals(expected, actual);
    }

    @Test
    //не должен показывать жанр игры, в которую не играли
    public void shouldNotShowTheGenreGameHasNotBeenPlayed() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Герои меча и магии", "Рпг");
        Game game1 = store.publishGame("Герои меча и магии 2", "Рпг");
        Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);
        player.play(game, 8);
        player.play(game1, 5);


        Game expected = null;
        Game actual = player.mostPlayerByGenre("Аркады");
        assertEquals(expected, actual);
    }

    @Test
    //не должен задавать отрицательное время
    public void shouldNotSetNegativeHours() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);

        assertThrows(Exception.class, () -> {
            player.play(game, -1);
        });

    }

    @Test
    //должен показывать уже сыгранное время при повторном добавлении игры
    public void shouldShowAlreadyPlayedTimeWhenReAddingGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Герои меча и магии", "Рпг");
        Game game1 = store.publishGame("Герои меча и магии 2", "Рпг");
        Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);
        player.installGame(game);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

}
