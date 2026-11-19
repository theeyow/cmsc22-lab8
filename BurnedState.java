import java.util.Random;

// ---- applying state pattern: behavior when the creature is burned ----
// WHAT CHANGED? moved burn related conditions and behaviors from Battle.java into a separate state class 

public class BurnedState implements StatusState{
    @Override
    public int getModifiedSpeed(int speed){
        return speed;       // being burned does not modify speed
    }

    @Override
    public double getModifiedAttack(int attack){
        return attack / 2.0;      // burned creatures deal half their normal attack
    }

    // checks whethere a creature can perform its action
    @Override
    public boolean canAct(Creature creature, Random random){
        return true;        // being burned does not prevent the creature from acting
    }

    // applies the burn effect at the end of the turn
    @Override
    public void endOfTurn(Creature creature){
        creature.takeDamage(6);
        System.out.println(creature.getName() + " is hurt by its burn. (-6 HP)");       // reduces HP by 6
        // checks if the burn damage caused the creature to faint
        if(creature.isFainted()){
            System.out.println(creature.getName() + " fainted!");
        }
    }
}