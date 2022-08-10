package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GameStoreTest {

    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    @Test
    //Не должна создаваться игра с уже существующими заголовком и жанром
    public void shouldAddExistingGame() {

        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertFalse(store.containsGame(game2));
    }

    @Test
    // не должны добавляться в каталог игры без названия и жанра
    public void shouldAddNotTitleGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame(null, null);


        assertFalse(store.containsGame(game));
    }

    @Test
    //
    public void shouldAddPlayerTimeOneHours() {

        GameStore store = new GameStore();
        store.addPlayTime("Ivan", 1);
        store.addPlayTime("Fill", 0);

        String actual = store.getMostPlayer();
        String expected = "Ivan";

        assertEquals(expected, actual);
    }
    @Test
    //общее время игрока не может быть отрицательным
    public void shouldAddNegativeTime() {

        GameStore store = new GameStore();
        store.addPlayTime("Ivan", -4);

        String actual = store.getMostPlayer();
        String expected = null;

        assertEquals(expected, actual);
    }

    @Test
    //время игрока должно суммироваться c прошлым значением
    public void shouldSumPlayerTime() {

        GameStore store = new GameStore();
        store.addPlayTime("Ivan", 6);
        store.addPlayTime("Ivan", 4);
        store.addPlayTime("Fill", 8);
        String actual = store.getMostPlayer();
        String expected = "Ivan";

        assertEquals(expected, actual);
    }

    @Test
    //должен показываться игрок с наибольшим временeм в игре
    public void shouldMostPlayer() {

        GameStore store = new GameStore();

        store.addPlayTime("John", 5);
        store.addPlayTime("Ivan", 1);
        store.addPlayTime("Fill", 2);
        String actual = store.getMostPlayer();
        String expected = "John";

        assertEquals(expected, actual);
    }


    @Test
    //если у всех игроков равное время игры, то при вызове игрока с большим временем, должен возвращаться первый
    public void shouldMostPlayerEqualTime() {

        GameStore store = new GameStore();
        store.addPlayTime("Fill", 5);
        store.addPlayTime("Ivan", 5);
        store.addPlayTime("John", 5);
        String actual = store.getMostPlayer();
        String expected = "Fill";

        assertEquals(expected, actual);
    }

    @Test
    //если у всех игроков время равно 0, то метод должен возвращать пустое значение
    public void shouldMostPlayerNullTime() {

        GameStore store = new GameStore();
        store.addPlayTime("Fill", 0);
        store.addPlayTime("Ivan", 0);
        store.addPlayTime("John", 0);
        String actual = store.getMostPlayer();
        String expected = null;

        assertEquals(expected, actual);
    }

    @Test
//метод getSumPlayedTime должен возвращать количество времени всех игроков
    public void shouldSumPlayedTime() {

        GameStore store = new GameStore();
        store.addPlayTime("Fill", 5);
        store.addPlayTime("Ivan", 5);
        store.addPlayTime("John", 5);
        int actual = store.getSumPlayedTime();
        int expected = 15;

        assertEquals(expected, actual);
    }


}
