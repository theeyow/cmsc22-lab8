import java.util.List;
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;

public class Creature {
    //create type, HP, attack, speed moves, status, and potions
    //WHAT CHANGED? encapsulation, converted package private fields to private for proper encapsulation
    private String name;
    private String type;
    private int maxHp;
    private int hp;
    private int attack;
    private int speed;
    private List<Move> moves;       //WHAT CHANGED? changed String[] moves to List<Move> so each move retains its own power and type propertie
    private StatusState status;     //WHAT CHANGED? Changed String status to StatusState interface to implement the state design pattern
                                    //encapsulates status behaviors (burned, asleep, paralyzed)
    private int potions;            //starting number of potions to be used


    // constructor for intializiing the creature properties
    public Creature(String name, String type, int maxHp, int attack, int speed, List<Move> moves, int potions){
        this.name = name;
        this.type = type;
        this.maxHp = maxHp;
        this.hp = maxHp;                // hp starts at maxHp
        this.attack = attack;
        this.speed = speed;
        this.moves = new ArrayList<>(moves);
        this.status = new NormalState();         //WHAT CHANGD? Replaced string assignment ("NORMAL") with the NormalState state object 
                                                // default status
        this.potions = potions;                    
    }


    // ----- setters and getters ----- : to allow controlled access to private fields
    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }
    
    public int getMaxHp(){
        return maxHp;
    }

    public int getHp(){
        return hp;
    }

    public int getAttack(){
        return attack;
    }

    public int getSpeed(){
        return speed;
    }

    public List<Move> getMoves(){
        return Collections.unmodifiableList(moves);
    }

    public StatusState getStatus(){
        return status;
    }

    public void setStatus(StatusState status){
        if(status == null){
            throw new IllegalArgumentException("Status cannot be null.");
        }
        this.status = status;
    }

    public int getPotions(){
        return potions;
    }


    // --- the creatures behavior and some of its method were moved here ---
    public boolean isFainted(){
        return hp <= 0;
    }

    // added a method to safely reduce hp without going below 0
    public void takeDamage(int damage){
        if(damage < 0) return;
        this.hp = Math.max(0, this.hp - damage);
    }

    // added to safely restore hp without exceeding  maxHp
    public void heal(int amount){
        if(amount < 0) return;
        this.hp = Math.min(this.maxHp, this.hp + amount);
    }

    // potion usage directly within Creature class 
    public boolean usePotion(){
        if(potions <= 0 || hp >= maxHp){
            return false;
        }
        potions--;
        heal(30);       // add 30 to hp whenever potion is used
        return true;
    }




    // ---- state pattern methods ---- 
    // added a status based behaviors to the active StatusState object
    //  removed repetitive if else/switch checks across Battle.java

    // calculates effective speed adjusted by current status
    public int getEffectiveSpeed(){
        return status.getModifiedSpeed(this.speed);
    }

    // calculates effective attack adjusted by current status
    public int getEffectiveAttack(){
        return status.getModifiedAttack(this.attack);
    }

    // checks whether the creature can execute its turn based on current status
    public boolean canAct(Random random){
        return status.canAct(this, random);
    }

    public void endOfTurn(){
        status.endOfTurn(this);
    }


}




