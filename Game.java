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
    Text.hideCursor();
    Text.clear();

    int rows = 30;
    int cols = 80;
    String horizontal = "-";
    String vertical = "|";

    // Draw the top and bottom borders
    for (int col = 1; col <= cols; col++) {
        Text.go(1, col); // Top border
        System.out.print(horizontal);
        Text.go(rows-1, col); // Bottom border
        System.out.print(horizontal);
    }

    // Draw the left and right borders
    for (int row = 2; row <= rows-1; row++) {
        Text.go(row, 1); // Left border
        System.out.print(vertical);
        Text.go(row, cols); // Right border
        System.out.print(vertical);
    }

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
    for (int i = 0; i < height; i++) {
      drawText(" ".repeat(width), row + i, col);
    }

    // Split the text into lines that fit within the specified width
    int startIndex = 0;
    for (int i = 0; i < height; i++) {
      // Calculate the end index for the current line
      int endIndex = Math.min(startIndex + width, text.length());

      // Extract the substring to display in the current line
      String line = text.substring(startIndex, endIndex);

      // Draw the line in the appropriate row and column
      drawText(line, row + i, col);

      // Update the start index for the next line
      startIndex += width;

      // Stop if all text has been displayed
      if (startIndex >= text.length()) {
          break;
        }

    }
    Text.go(row+1, col);

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
        return new Boss(name+(int)(Math.random()*100));
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
      int column = 5;
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
        column += Math.max(1, 74 / party.size());
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
    Text.go(3,2);
    System.out.println("Your Players: ");
    //draw player party
    drawParty(party, 4);
    //draw enemy party
    Text.go(8, 2);
    System.out.println("Enemy Players: ");
    int enemyRow = 9;
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


  /////////////////////////////////
  /////////////////////////////////
  /////////////////////////////////
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
    ArrayList<Adventurer> party = new ArrayList<Adventurer>();
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
    //You can add parameters to draw screen!
    drawScreen(enemies, party);//initial state.

    //Main loop

    //display this prompt at the start of the game.
    String preprompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
    TextBox(15,5,70,1,preprompt);

    while(! ((input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) && enemies.size() > 0 && party.size() > 0)){
      //Read user input
      input = in.nextLine();
      drawScreen(enemies, party);

      //////////////////////////////////////////
      if (partyTurn){

        ///////////////////////////////////////

        if(input.equals("attack") || input.equals("a")){
          TextBox(20,5,70,2, party.get(whichPlayer).attack(enemies.get(whichOpponent)));
        }
        else if(input.equals("special") || input.equals("sp")){
          TextBox(20,5,70,2, party.get(whichPlayer).specialAttack(enemies.get(whichOpponent)));
        }
        else if(input.startsWith("su ")){
          int supportWho = input.charAt(3) - '0';
          if (supportWho == whichPlayer){
            TextBox(20,5,70,2, party.get(whichPlayer).support());
          }
          else{
            TextBox(20,5,70,2, party.get(whichPlayer).support(party.get(supportWho)));
          }
        }
        else if(input.startsWith("support ")){
          int supportWho = input.charAt(8) - '0';
          if (supportWho == whichPlayer){
            TextBox(20,5,70,2, party.get(whichPlayer).support());
          }
          else{
            TextBox(20,5,70,2, party.get(whichPlayer).support(party.get(supportWho)));
          }
        }
        /////////////////////////////////////////

        //IF ENEMY DIES
        ///////////////////////////////////
        if (enemies.get(whichOpponent).getHP() <= 0){
          TextBox(20,5,70,1, enemies.get(whichOpponent).getName() + " has died!");
          enemies.remove(whichOpponent);
        }
        ///////////////////////////////////

        //ADD ANOTHER PARTY MEMBER
        /////////////////////////////////////////
        if(party.size() > 0 && party.size() <= 3 && party.get(whichPlayer).getHP() < party.get(whichPlayer).getmaxHP()/2 && turn >= 4){
          TextBox(25,5,70,1, "Add another player? y/n");
          input = in.nextLine();
          if(input.equals("y")){
            party.add(createRandomAdventurer());
          }
        }
        //////////////////////////////////////////

        //NEXT PLAYER WILL MAKE THEIR MOVE
        /////////////////////////////////////////
        whichPlayer++;

        if(whichPlayer < party.size()){
          String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
          TextBox(15,5,70,1, prompt);
        }else{
          String prompt = "press enter to see monster's turn";
          TextBox(15,5,70,1, prompt);
          partyTurn = false;
          whichOpponent = 0;
        }
        //done with one party member
        ///////////////////////////////////////////
        ////////////////////////////////////////////
      }else if (enemies.size() > 0){
        //not the party turn!


        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        //YOUR CODE HERE

        whichPlayer = (int)(Math.random() * party.size()); //choose a random player
        TextBox(20,5,70,2, enemies.get(whichOpponent).chooseAction(party.get(whichPlayer)));

        //IF PLAYER DIES
        ///////////////////////////////////
        if (party.get(whichPlayer).getHP() <= 0){
          TextBox(20,5,70,2, party.get(whichPlayer).getName() + " has died!");
          party.remove(whichPlayer);
        }
        ///////////////////////////////////

        //ENEMY MEMBER ADDED
        ///////////////////////////////////////////
        if(enemies.size() > 0 && enemies.size() <= 3 && enemies.get(whichOpponent).getHP() < enemies.get(whichOpponent).getmaxHP()/2 && turn >= 3){
          TextBox(25,5,70,2, "enemies increased!");
          enemies.add(createRandomEnemy());
        }

        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/


        //Decide where to draw the following prompt:
        //NEXT ENEMY WILL MAKE THEIR MOVE
        ///////////////////////////////////////////
        String prompt = "press enter to see next turn";
        TextBox(15,5,70,2, prompt);


        whichOpponent++;

      }//end of one enemy.

      //modify this if statement.
      if(!partyTurn && whichOpponent >= enemies.size()){
        //THIS BLOCK IS TO END THE ENEMY TURN
        //It only triggers after the last enemy goes.
        whichPlayer = 0;
        whichOpponent = 0;
        partyTurn=true;
        //display this prompt before player's turn
        String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
        TextBox(15,5,70,2, prompt);
      }

      //display the updated screen after input has been processed.
      //drawScreen(enemies, party);


    }//end of main game loop
    //while loop ended
    ///////////////////////////////////////////////////////////////
    if (input.equals("quit") || input.equals("qu")){
      TextBox(25,5,70,2, "Forfeit means loss. Never forget this shame.");
    }
    else if (enemies.size() < 0){
      TextBox(25,5,70,2, "You have died. You have brought disgrace to your name.");
    }
    else{
      TextBox(25,5,70,2, "You have slain your enemy. You deserve a celebration.");
    }


    //After quit reset things:
    quit();
  }
}
