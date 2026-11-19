import java.util.Random;

public class ParalyzedState implements StatusState{
    @Override
    public int getModifiedSpeed(int speed){
        return speed / 2;       // reduces the creature's speed by half while paralyzed
    }

    @Override
    public double getModifiedAttack(int attack){
        return attack;      // attack remains unchanged while paralyzed
    }

    @Override
    public boolean canAct(Creature creature, Random random){
        // 1 in 4 chance of being unable to move
        if(random.nextInt(4) == 0){
            System.out.println(creature.getName() + " is paralyzed and can't move!");
            return false;       // prevents the creature from performing its action
        }
        return true;        // allows the creature to perform its action
    }

    @Override
    public void endOfTurn(Creature creature) {
        // No end-of-turn effect while creature is paralyzed
    }

}