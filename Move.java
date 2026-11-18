public class Move{
    // stores the move's name, power, and type
    // WHAT CHANGED? created separated Move class instead of storing moves as Strings, so each move can have its own properties
    private String name;
    private int power;
    private String type;

    // initializes a Move object with its name, power, and type
    public Move(String name, int power, String type){
        this.name = name;
        this.power = power;
        this.type = type;
    }

    // returns the name of the move
    public String getName(){
        return name;
    }

    // returns the power of the move
    public int getPower(){
        return power;
    }

    // returns the type of the move
    public String getType(){
        return type;
    }
}