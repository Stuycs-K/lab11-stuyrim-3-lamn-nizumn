import java.util.ArrayList;
import java.util.Scanner;


public class Tester{
  //return a random adventurer (choose between all available subclasses)
  //feel free to overload this method to allow specific names/stats.
  static String [] defaultNames = new String [] {"Andy", "Bob", "Chad", "Dave", "Ethan", "Fin", "Gabe", "Hector", "Ivan", "Jake", "Kevin", "Liam", "Mike", "Nick", "Oscar", "Peter", "Rob", "Steve", "Tom"};
  public static Adventurer createRandomAdventurer(){
    String name = defaultNames[(int) (Math.random() * 19)];
    int character = (int)(Math.random() * 3);
    if (character == 1){
      return new CodeWarrior(name+(int)(Math.random()*100));
    }
    if (character == 2){
      return new SnowGolem(name+(int)(Math.random()*100));
    }
    else {
      return new Steve(name+(int)(Math.random()*100));
    }
  }

  public static Adventurer createRandomEnemy(){
    String name = defaultNames[(int) (Math.random() * 19)];
    int character = (int)(Math.random() * 5);
    /*if (character == 1){
      return new Creeper(name+(int)(Math.random()*100));
    }
    if (character == 2){
      return new Dragon(name+(int)(Math.random()*100));
    }
    if (character == 3) {
      return new Skeleton(name+(int)(Math.random()*100));
    }*/
    //if (character == 4) {
      return new Spider(name+(int)(Math.random()*100));
    //}
    /*else {
      return new Zombie(name+(int)(Math.random()*100));
    }*/
  }

  public static void main(String[] args){

    ///////////////////
    ArrayList<Adventurer>enemies = new ArrayList<Adventurer>();
    enemies.add(createRandomEnemy());

    ArrayList<Adventurer> party = new ArrayList<Adventurer>();
    party.add(createRandomAdventurer());
    //////////////////

    /////////////////////////////////
    int whichPlayer = 0;
    boolean partyTurn = true;
    int whichOpponent = 0;
    /////////////////////////////////////////

    /////////////////////////////////////////////
    String input = "";//blank to get into the main loop.
    Scanner in = new Scanner(System.in);
    String preprompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
    System.out.println(preprompt);
    ///////////////////////////////////////////////////

    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //Read user input
      input = in.nextLine();

      //////////////////////////////////////////
      if (partyTurn){

        ///////////////////////////////////////
        if(input.equals("attack") || input.equals("a")){
          System.out.println(party.get(whichPlayer).attack(enemies.get(whichOpponent)));
        }
        else if(input.equals("special") || input.equals("sp")){
          System.out.println(party.get(whichPlayer).specialAttack(enemies.get(whichOpponent)));
        }
        else if(input.startsWith("su ") || input.startsWith("support ")){
          System.out.println(party.get(whichPlayer).support());
        }
        /////////////////////////////////////////

        /*/////////////////////////////////////////
        if(party.size() <= 3 || party.get(whichPlayer).getHP() < party.get(whichPlayer).getMaxHP()/2){
          System.out.println("Add another player? y/n");
          if(input.equals("y")){
            party.add(createRandomAdventurer());
          }
        }
        //////////////////////////////////////////*/
        whichPlayer++;

        if(whichPlayer < party.size()){
          String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
          System.out.println(prompt);
        }else{
          String prompt = "press enter to see monster's turn";
          System.out.println(prompt);
          partyTurn = false;
          whichOpponent = 0;
        }

      }
      ////////////////////////////////////////////
      else{
        System.out.println(enemies.get(whichOpponent).chooseAction(party.get(whichOpponent)));

        /*///////////////////////////////////////////
        if(enemies.size() <= 3 || enemies.get(whichPlayer).getHP() < enemies.get(whichPlayer).getMaxHP()/2){
          enemies.add(createRandomEnemy());
        }
        ///////////////////////////////////////////*/

      }

    }

  }
}
