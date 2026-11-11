import java.util.Random;

public class ParalyzedState implements StatusState{
    @Override
    public int getModifiedSpeed(int speed){
        return speed / 2;
    }

    @Override
    public int getModifiedAttack(int attack){
        return attack;
    }

    @Override
    public boolean canAct(Creature creature, Random random){
        // 1 in 4 chance of being unable to move
        if(random.nextInt(4) == 0){
            System.out.println(creature.getName() + " is paralyzed and can't move!");
            return false;
        }
        return true;
    }

    @Override
    public void endOfTurn(Creature creature) {
        // No end-of-turn effect.
    }

}