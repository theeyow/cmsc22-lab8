import java.util.Random;

public class NormalState implements StatusState{
    @Override
    public int getModifiedSpeed(int speed){
        return speed;
    }

    @Override
    public int getModifiedAttack(int attack){
        return attack;
    }

    @Override 
    public boolean canAct(Creature creature, Random random){
        return true;
    }

    @Override 
    public void endOfTurn(Creature creature){
        // no end of turn effect
    }

}