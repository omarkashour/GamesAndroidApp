package com.example.gamesapp.data_access;

import com.example.gamesapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GameDA implements IGameDA {


    private List<Game> games = new ArrayList<>();

    public GameDA() {
        Calendar cal = Calendar.getInstance();

        // 1) The Witcher 3 (RPG, PC)
        cal.set(2015, Calendar.MAY, 19);
        games.add(new Game(
                "The Witcher 3: Wild Hunt",
                cal.getTime(),
                10,
                149, // ₪149
                Game.PC,
                "RPG",
                "An open-world RPG where you play Geralt of Rivia on a monster-hunting journey.",
                9,
                R.drawable.thewitcher3pc
        ));

        // 2) Uncharted 4 (Adventure, PlayStation)
        cal.set(2016, Calendar.MAY, 10);
        games.add(new Game(
                "Uncharted 4: A Thief’s End",
                cal.getTime(),
                9,
                179,
                Game.PLAYSTATION,
                "Adventure",
                "Nathan Drake returns in this action-adventure epic filled with treasure hunts.",
                32,
                R.drawable.uncharted4ps4
        ));

        // 3) Forza Horizon 5 (Simulation, Xbox)
        cal.set(2021, Calendar.NOVEMBER, 9);
        games.add(new Game(
                "Forza Horizon 5",
                cal.getTime(),
                9,
                199,
                Game.XBOX,
                "Simulation",
                "Experience the vibrant landscapes of Mexico in this open-world racing sim.",
                54,
                R.drawable.forza5xbox
        ));

        // 4) Minecraft (SandBox, PC)
        cal.set(2011, Calendar.NOVEMBER, 18);
        games.add(new Game(
                "Minecraft",
                cal.getTime(),
                8,
                89,
                Game.PC,
                "SandBox",
                "A block-building sandbox game that lets you create and explore infinite worlds.",
                16,
                R.drawable.minecraftpc
        ));

        // 5) Animal Crossing: New Horizons (Family, Nintendo Switch)
        cal.set(2020, Calendar.MARCH, 20);
        games.add(new Game(
                "Animal Crossing: New Horizons",
                cal.getTime(),
                9,
                169,
                Game.NINTENDO_SWITCH,
                "Family",
                "Build your own island paradise and befriend charming animal villagers.",
                27,
                R.drawable.animalcrossingnintendo
        ));

        // 6) Portal 2 (Physics, PC)
        cal.set(2011, Calendar.APRIL, 19);
        games.add(new Game(
                "Portal 2",
                cal.getTime(),
                10,
                59,
                Game.PC,
                "Physics",
                "Solve mind-bending puzzles using your portal gun in this critically acclaimed sequel.",
                1453,
                R.drawable.portal2pc
        ));

        // 7) Kerbal Space Program (Education, PC)
        cal.set(2015, Calendar.APRIL, 27);
        games.add(new Game(
                "Kerbal Space Program",
                cal.getTime(),
                8,
                119,
                Game.PC,
                "Education",
                "Design and launch rockets to explore space, learning real orbital mechanics.",
                134,
                R.drawable.ksppc
        ));

        // 8) God of War (Action, PlayStation)
        cal.set(2018, Calendar.APRIL, 20);
        games.add(new Game("God of War", cal.getTime(), 10, 189, Game.PLAYSTATION, "Action",
                "A visceral third‑person action game following Kratos and his son Atreus through Norse realms.", 16,R.drawable.gowps5));

        // 9) Hollow Knight (Indie, PC)
        cal.set(2017, Calendar.FEBRUARY, 24);
        games.add(new Game("Hollow Knight", cal.getTime(), 9, 79, Game.PC, "Adventure",
                "A beautifully hand‑drawn Metroidvania with deep exploration and tight combat.", 150,R.drawable.hollowknightpc));

        // 10) Civilization VI (Strategy, PC)
        cal.set(2016, Calendar.OCTOBER, 21);
        games.add(new Game("Civilization VI", cal.getTime(), 8, 129, Game.PC, "Simulation",
                "Turn‑based strategy where you build an empire to stand the test of time.", 19,R.drawable.civ4pc));

        // 11) Stardew Valley (Simulation, Nintendo Switch)
        cal.set(2016, Calendar.FEBRUARY, 26);
        games.add(new Game("Stardew Valley", cal.getTime(), 9, 99, Game.NINTENDO_SWITCH, "SandBox",
                "A farming simulator meets RPG—grow crops, raise animals, and build relationships.", 12,R.drawable.stardewvalleyswitchjpg));

        // 12) Celeste (Indie, PC)
        cal.set(2018, Calendar.JANUARY, 25);
        games.add(new Game("Celeste", cal.getTime(), 10, 69, Game.PC, "Adventure",
                "A precision platformer about climbing a mountain and overcoming personal challenges.", 36, R.drawable.celestepc));

        // 13) The Legend of Zelda: Breath of the Wild (Adventure, Nintendo Switch)
        cal.set(2017, Calendar.MARCH, 3);
        games.add(new Game("The Legend of Zelda: Breath of the Wild", cal.getTime(), 10, 199, Game.NINTENDO_SWITCH, "Adventure",
                "An open‑world masterpiece that redefines exploration and freedom in gaming.", 52,R.drawable.zeldabowswitch));

        // 14) FIFA 25 (Sports, PC)
        cal.set(2021, Calendar.OCTOBER, 1);
        games.add(new Game("FC 25", cal.getTime(), 7, 159, Game.PC, "Simulation",
                "The latest in EA’s football series with HyperMotion technology for realistic gameplay.", 83,R.drawable.fc25pc));

        // 15) Among Us (Party, PC)
        cal.set(2018, Calendar.JUNE, 15);
        games.add(new Game("Among Us", cal.getTime(), 8, 39, Game.PC, "Education",
                "A social deduction party game where crewmates root out impostors aboard a spaceship.", 71,R.drawable.amonguspc));
    }

    @Override
    public String[] getGenres() {
        return new String[]{"Action", "Adventure", "RPG", "Family", "Physics", "SandBox", "Simulation", "Education"};
    }

    @Override
    public List<Game> getGames() {
        return games;
    }

    @Override
    public List<Game> getGamesByTitle(String title) {
        List<Game> result = new ArrayList<>();
        for (Game b : games) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase()))
                result.add(b);
        }
        return result;
    }

    @Override
    public List<Game> getGamesByFilters(Game filters) {
        List<Game> result = new ArrayList<>();
        for (Game g : games) {
            // 1) title substring match?
            if (filters.getTitle() != null &&
                    !g.getTitle().toLowerCase().contains(filters.getTitle().toLowerCase())) {
                continue;
            }

            // 2) exact genre match?
            if (filters.getGenre() != null &&
                    !g.getGenre().equalsIgnoreCase(filters.getGenre())) {
                continue;
            }

            // 3) exact platform match?
            if (filters.getPlatform() != null &&
                    !g.getPlatform().equalsIgnoreCase(filters.getPlatform())) {
                continue;
            }

            // 4) minimum rating?
            if (filters.getRating() > 0 &&
                    g.getRating() < filters.getRating()) {
                continue;
            }

            // 5) maximum price?
            if (filters.getPrice() > 0 &&
                    g.getPrice() > filters.getPrice()) {
                continue;
            }

            // 6) release date range?
            Date from = filters.getReleaseDate();
            if (from != null && g.getReleaseDate().before(from)) {
                continue;
            }

            // if we get here, the current game passed all active filters
            result.add(g);
        }

        return result;
    }


}
