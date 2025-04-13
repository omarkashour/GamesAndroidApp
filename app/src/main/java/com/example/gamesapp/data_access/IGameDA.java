package com.example.gamesapp.data_access;

import java.util.Date;
import java.util.List;

public interface IGameDA {
    String[] getGenres();
    List<Game> getGames();


}
