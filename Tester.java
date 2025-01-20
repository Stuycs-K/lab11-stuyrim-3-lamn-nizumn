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
    int turns = 0;
    /////////////////////////////////////////

    /////////////////////////////////////////////
    String input = "";//blank to get into the main loop.
    Scanner in = new Scanner(System.in);
    String preprompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
    System.out.println(preprompt);
    ///////////////////////////////////////////////////

    while(! ((input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) && enemies.size() > 0 && party.size() > 0)){
      //Read user input
      input = in.nextLine();

      //////////////////////////////////////////
      if (partyTurn){

        ///////////////////////////////////////
        if (! (input.equals("a") || input.equals("sp") || input.equals("su") || input.equals("quit"))){
        System.out.println("invalid input. please type again");
        }
        
        if(input.equals("attack") || input.equals("a")){
          System.out.println(party.get(whichPlayer).attack(enemies.get(whichOpponent)));
          System.out.println("1.) party attack done");
          System.out.println("-------------------------------------------------");
        }
        else if(input.equals("special") || input.equals("sp")){
          System.out.println(party.get(whichPlayer).specialAttack(enemies.get(whichOpponent)));
          System.out.println("1.) party attack done");
          System.out.println("-------------------------------------------------");
        }
        else if(input.startsWith("su ") || input.startsWith("support ")){
          System.out.println(party.get(whichPlayer).support());
          System.out.println("1.) party attack done");
          System.out.println("-------------------------------------------------");
        }
        /////////////////////////////////////////

        if (enemies.get(whichOpponent).getHP() <= 0){
          System.out.println(enemies.get(whichOpponent).getName() + " has died!");
          enemies.remove(whichOpponent);
        }


        /////////////////////////////////////////
        if(party.size() > 0 && party.size() <= 3 && party.get(whichPlayer).getHP() < party.get(whichPlayer).getmaxHP()/2 && turns >= 4){
          System.out.println("Add another player? y/n");
          input = in.nextLine();
          if(input.equals("y")){
            party.add(createRandomAdventurer());
            System.out.println("1.5.) party member added");
            System.out.println("-------------------------------------------------");
          }
        }
        //////////////////////////////////////////
        whichPlayer++;
        System.out.println("party number: " + party.size() + " player number: " + whichPlayer);
        System.out.println("-------------------------------------------------");

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
      ///////////////////////////////////////////
      ////////////////////////////////////////////
      else{
        whichPlayer = (int)(Math.random() * party.size());
        System.out.println(enemies.get(whichOpponent).chooseAction(party.get(whichPlayer)));
        System.out.println("2.) enemy attack done");
        System.out.println("-------------------------------------------------");

        if (party.get(whichPlayer).getHP() <= 0){
          System.out.println(party.get(whichPlayer).getName() + " has died!");
          party.remove(whichPlayer);
        }

        ///////////////////////////////////////////
        if(enemies.size() > 0 && enemies.size() <= 3 && enemies.get(whichOpponent).getHP() < enemies.get(whichOpponent).getmaxHP()/2 && turns >= 3){
          System.out.println("enemies increased!");
          enemies.add(createRandomEnemy());
          System.out.println("2.5.) enemy member added");
          System.out.println("-------------------------------------------------");
        }

        ///////////////////////////////////////////
        String prompt = "press enter to see next turn";
        System.out.println(prompt);

        whichOpponent++;
        System.out.println("enemy number: " + enemies.size() + " enemy number: " + whichOpponent);
        System.out.println("-------------------------------------------------");

      }

      if(!partyTurn && whichOpponent >= enemies.size()){
        //THIS BLOCK IS TO END THE ENEMY TURN
        //It only triggers after the last enemy goes.
        whichPlayer = 0;
        whichOpponent = 0;
        partyTurn=true;
        //display this prompt before player's turn
        String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
        System.out.println(prompt);
      }

    }
    //while loop ended
    ///////////////////////////////////////////////////////////////
    if (input.equals("quit") || input.equals("qu")){
      System.out.println("Forfeit means loss. Never forget this shame.");
    }
    else if (enemies.size() < 0){
      System.out.println("You have died. You have brough disgrace to your name.");
    }
    else{
      System.out.println("You have slain your enemy. You deserve a celebration.");
    }

  }
}
