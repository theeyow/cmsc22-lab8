import java.util.Random;
import referentials.Moves;

// plays the rounds, applies moves, types and statuses, and prints the result.
class Battle {
    // stores the two creatures participating in the battle
    private Creature player;
    private Creature rival;

    // WHAT CHANGED? - replaced String difficulty with RivalStrategy to implement the strategy pattern
                  // - allows Battle to use different rival decision making strategies without knowing specific difficulty implementation 
    private RivalStrategy rivalStrategy; 

    private Random random;
    private int round = 0;

    private BattlePrint printEvent = new BattlePrint();
    private BattleActions actionEvent = new BattleActions();

    // constructor for initializing the battle properties
    Battle(Creature player, Creature rival, RivalStrategy rivalStrategy, Random random) {
        this.player = player;
        this.rival = rival;
        this.rivalStrategy = rivalStrategy;
        this.random = random;
    }

    // checks whether either creature has fainted
    public boolean isOver() {
        return player.isFainted() || rival.isFainted();
    }

    public void showStatus(){
        printEvent.showStatus(player, rival, round);
    }

    public void printCreature(){
        printEvent.printCreature(player);
    }

    public void showMoveMenu(){
        printEvent.showMoveMenu(player);
    }

    // choice: 0 to 2 is one of the player's moves, 3 is a potion.
    public void playRound(int choice) {
        round++;

        // gets the rival's action through its assigned strategy
        int rivalChoice = chooseRivalAction();

        // determines which creature acts first based on effective speed
        if (getSpeed(player) >= getSpeed(rival)) {
            takeAction(player, rival, choice);
            if (!isOver()) {
                takeAction(rival, player, rivalChoice);
            }
        } else {
            takeAction(rival, player, rivalChoice);
            if (!isOver()) {
                takeAction(player, rival, choice);
            }
        }
        // applies each creature's end-of-turn status effect
        if (!isOver()) {
            endOfTurn(player);
            endOfTurn(rival);
        }
    }
    
    public void printResult(){
        printEvent.printResult(player, rival);
    }

    // handles the action selected by a creature
    private void takeAction(Creature actor, Creature target, int choice) {
        // StatusState checks canAct through Creature 
        if (!actor.canAct(random)) {
            return;
        }
        if (choice == 3) {
            usePotion(actor);
        } else {
            useMove(actor, target, actor.getMoves()[choice], random);
        }
    }

    // handles potion usage
    private void usePotion(Creature actor) {
        actionEvent.usePotion(player);
    }

    // handles move execution and damage calculation
    private void useMove(Creature attacker, Creature target, Moves move, Random random) {
        actionEvent.useMove(attacker, target, move, random);
    }

    // StatusState implementations handle end of turn events
    private void endOfTurn(Creature creature) {
        creature.endOfTurn();
    }

    // gets the creature's effective speed after applying its current status
    private int getSpeed(Creature creature) {
        return creature.getEffectiveSpeed();
    }

    // respective difficulty choice is implemented through strategy
    private int chooseRivalAction() {
        return rivalStrategy.chooseAction(rival, player, random);
    }

}
