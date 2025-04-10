package com.example.gamesapp.data_access;

public class GameDAFactory {

    public static IGameDA getGameDA(){
        return new GameDA();
    }
}
