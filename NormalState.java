import java.util.Random;

// ---- apply state pattern: behavior when a creature has no status effect ----
// WHAT CHANGED? separated the normal state behavior from Battle.java into its own class. This allows each status 
                // state to handle its own effects without putting all status condition inside the Battle.java

public class NormalState implements StatusState{
    @Override
    public int getModifiedSpeed(int speed){
        return speed;       // speed remains unchanged when the creature is in its normal state 
    }

    @Override
    public int getModifiedAttack(int attack){
        return attack;      // attack remains unchanged when the creature is in its normal state
    }

    @Override 
    public boolean canAct(Creature creature, Random random){
        return true;        // allows the creature to perform its action
    }

    @Override 
    public void endOfTurn(Creature creature){
        // no end of turn effect when the creature is in its normal state
    }

}