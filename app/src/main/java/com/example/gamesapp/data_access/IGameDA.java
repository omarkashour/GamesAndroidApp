package com.example.gamesapp.data_access;

import java.util.Date;
import java.util.List;

public interface IGameDA {
    String[] getGenres();
    List<Game> getGames();
    List<Game> getGamesByTitle(String title);
    List<Game> getGamesByFilters(Game filters);

}
