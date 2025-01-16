import java.util.*;
import java.util.Random;

public class Game{
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;
  Random rand = new Random();

  public static void main(String[] args) {
    run();
  }


  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    Text.clear();

    String horizontal = "-";
    String vertical = "|";

    Text.go(1,1);
    for (int i = 0; i < 80; i++){
      System.out.print(Text.colorize(horizontal, Text.GREEN));
    }

    for (int line = 2; line < 29; line++){
      Text.go(line,1);
      System.out.print(Text.colorize(vertical, Text.WHITE));
      Text.go(line,80);
      System.out.print(Text.colorize(vertical, Text.WHITE));
    }

    Text.go(28,1);
    for (int i = 0; i < 80; i++){
      System.out.print(Text.colorize(horizontal, Text.GREEN));
    }

    Text.go(30,1);

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }

  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    Text.go(startRow, startCol);
    System.out.print(s);
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }

  /*Use this method to place text on the screen at a particular location.
  *When the length of the text exceeds width, continue on the next line
  *for up to height lines.
  *All remaining locations in the text box should be written with spaces to
  *clear previously written text.
  *@param row the row to start the top left corner of the text box.
  *@param col the column to start the top left corner of the text box.
  *@param width the number of characters per row
  *@param height the number of rows
  */
  public static void TextBox(int row, int col, int width, int height, String text){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    int currentRow = row;
    int currentCol = col;
    String [] words = text.split(" ");
    String line = "";

    for (String word : words){
      if (line.length() + word.length() + 1 > width){ //will surpass the given width
        Text.go(currentRow++, col);
        System.out.print(line);
        line = "";
        if (currentRow - row >= height) { break; }
      }
    }

    if (currentRow - row < height){
      Text.go(currentRow, col);
      System.out.print(line);
    }

    for (int i = currentRow - row; i < height; i++){
      Text.go(row + i, col);
      System.out.print(" ".repeat(width));
    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }




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
      int character = (int)(Math.random() * 6);
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
      /*if (character == 5) {
        return new Wither(name+(int)(Math.random()*100));
      }
      else {
        return new Zombie(name+(int)(Math.random()*100));
      }*/
    }

    /*Display a List of 2-4 adventurers on the rows row through row+3 (4 rows max)
    *Should include Name HP and Special on 3 separate lines.
    *Note there is one blank row reserved for your use if you choose.
    *Format:
    *Bob          Amy        Jun
    *HP: 10       HP: 15     HP:19
    *Caffeine: 20 Mana: 10   Snark: 1
    * ***THIS ROW INTENTIONALLY LEFT BLANK***
    */
    public static void drawParty(ArrayList<Adventurer> party,int startRow){

      /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
      //YOUR CODE HERE
      int start = startRow;
      int column = 2;
      for (int i = 0; i < party.size(); i++){
        Text.go(startRow, column);
        Adventurer current = party.get(i);
        System.out.print(current.getName());
        startRow++;
        Text.go(startRow, column);
        System.out.print("HP: " + current.getHP());
        startRow++;
        Text.go(startRow, column);
        System.out.print(current.getSpecialName() + ": " + current.getSpecial());
        startRow = start;
        column += Math.max(1, 78 / party.size());
      }
      /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
    }


  //Use this to create a colorized number string based on the % compared to the max value.
  public static String colorByPercent(int hp, int maxHP){
    String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
    //COLORIZE THE OUTPUT IF HIGH/LOW:
    double percent = ((double) hp/maxHP);
    int color = 0;
    // under 25% : red
    if (percent < 25){
      color = Text.RED;
    }
    // under 75% : yellow
    if (percent < 75){
        color = Text.YELLOW;
    }
    // otherwise : white
    else{
      color = Text.WHITE;
    }
    return output;
  }


  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen(ArrayList<Adventurer>enemies, ArrayList<Adventurer>party){
    drawBackground();
    Text.go(15,2);
    System.out.println("Your Players: ");
    //draw player party
    drawParty(party, 16);
    //draw enemy party
    Text.go(20, 2);
    System.out.println("Enemy Players: ");
    int enemyRow = 21;
    drawParty(enemies, enemyRow);
  }

  public static String userInput(Scanner in){
      //Move cursor to prompt location
      Text.go(30, 2);
      //show cursor
      Text.showCursor();
      String input = in.nextLine();

      //clear the text that was written
      Text.clear();
      return input;
  }

  public static void quit(){
    Text.reset();
    Text.showCursor();
    Text.go(32,1);
  }

  public static void run(){
    //Clear and initialize
    Text.hideCursor();
    Text.clear();


    //Things to attack:
    //Make an ArrayList of Adventurers and add 1-3 enemies to it.
    //If only 1 enemy is added it should be the boss class.
    //start with 1 boss and modify the code to allow 2-3 adventurers later.
    ArrayList<Adventurer>enemies = new ArrayList<Adventurer>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    enemies.add(createRandomEnemy());
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    ArrayList<Adventurer> party = new ArrayList<>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    party.add(createRandomAdventurer());
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    Scanner in = new Scanner(System.in);
    //Draw the window border
    drawBackground();
    //You can add parameters to draw screen!
    drawScreen(enemies, party);//initial state.

    //Main loop

    //display this prompt at the start of the game.
    String preprompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";

    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //Read user input
      input = userInput(in);

      //example debug statment
      TextBox(24,2,1,78,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );

      //display event based on last turn's input
      if(partyTurn){

        //Process user input for the last Adventurer:
        if(input.equals("attack") || input.equals("a")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          System.out.print(party.get(whichPlayer).attack(enemies.get(whichOpponent)));
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.equals("special") || input.equals("sp")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          System.out.print(party.get(whichPlayer).specialAttack(enemies.get(whichOpponent)));
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.startsWith("su ") || input.startsWith("support ")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          System.out.print(party.get(whichPlayer).support());
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }

        //You should decide when you want to re-ask for user input
        //If no errors:
        whichPlayer++;


        if(whichPlayer < party.size()){
          //This is a player turn.
          //Decide where to draw the following prompt:
          String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";


        }else{
          //This is after the player's turn, and allows the user to see the enemy turn
          //Decide where to draw the following prompt:
          String prompt = "press enter to see monster's turn";

          partyTurn = false;
          whichOpponent = 0;
        }
        //done with one party member
      }else{
        //not the party turn!


        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        //YOUR CODE HERE
        int whichEnemeny = Math.random() * (enemies.size());
        int chance = Math.random() *2;
        if (whichEnemeny.getHP() < 4){
          if (whichPlayer.getHP() < 4){
            if (chance == 0){
              System.out.println(whichEnemeny.support());
        }
          if (chance == 1){
            System.out.println(whichEnemeny.attack(whichPlayer));
          }
        }
        else{
          System.out.println(bot.support());
        }
      }
      else if (other.getHP() > other.getmaxHP() / 2 && bot.getSpecial() > 7){
        System.out.println(bot.specialAttack(other));
      }
      else{
        System.out.println(bot.attack(other));
      }
        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/


        //Decide where to draw the following prompt:
        String prompt = "press enter to see next turn";

        whichOpponent++;

      }//end of one enemy.

      //modify this if statement.
      if(!partyTurn && whichOpponent >= enemies.size()){
        //THIS BLOCK IS TO END THE ENEMY TURN
        //It only triggers after the last enemy goes.
        whichPlayer = 0;
        turn++;
        partyTurn=true;
        //display this prompt before player's turn
        String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
      }

      //display the updated screen after input has been processed.
      drawScreen(enemies, party);


    }//end of main game loop


    //After quit reset things:
    quit();
  }
}
