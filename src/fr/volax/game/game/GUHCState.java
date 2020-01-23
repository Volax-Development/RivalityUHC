package fr.volax.game.game;

public enum GUHCState {
    WAIT(true), PREGAME(false), GAME(false), GAMEPVP(false), FINISH(false);

    private boolean canJoin;
    private static  GUHCState currentState;

    GUHCState(boolean canJoin){
        this.canJoin = canJoin;
    }

    public boolean canJoin(){
        return canJoin;
    }

    public static void setState(GUHCState state){
        GUHCState.currentState = state;
    }

    public static boolean isState(GUHCState state){
        return GUHCState.currentState == state;
    }

    public static GUHCState getState(){
        return currentState;
    }
}
