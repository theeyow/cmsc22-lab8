import java.util.Random;

// ---- applying state pattern : behavior when the creature is asleep -----
// WHAT CHANGED? moved the sleep related conditions and behaviors from Battle.java into separate state class

public class AsleepState implements StatusState{
    @Override 
    public int getModifiedSpeed(int speed){
        return speed;           // sleeping does not modify speed
    }

    @Override
    public double getModifiedAttack(int attack){
        return attack;          // sleeping does not modify attack
    }

    // checks if the creature can act while being asleep
    @Override
    public boolean canAct(Creature creature, Random random){
        // 1 in 3 chance to wake up
        if (random.nextInt(3) == 0){
            creature.setStatus(new NormalState());      // changes state back to normal
            System.out.println(creature.getName() + " woke up!");       // creature can now perform its action
            return true;
        }
        System.out.println(creature.getName() + " is fast asleep.");        // creature skips its turn
        return false;
    }

    // handles the effects that happen at the end of the turn
    @Override
    public void endOfTurn(Creature creature){
        // no end of turn effect while asleep
    }
}