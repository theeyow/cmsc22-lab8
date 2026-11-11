import java.util.Random;

public class AsleepState implements StatusState{
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
        // 1 in 3 chance to wake up
        if (random.nextInt(3) == 0){
            creature.setStatus(new NormalState());
            System.out.println(creature.getName() + " woke up!");
            return true;
        }
        System.out.println(creature.getName() + " is fast asleep.");
        return false;
    }

    @Override
    public void endOfTurn(Creature creature){
        // no end of turn effect
    }
}