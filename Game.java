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
    Text.go(row+height+1, col);

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }




    //return a random adventurer (choose between all available subclasses)
    //feel free to overload this method to allow specific names/stats.
    public static Adventurer createRandomAdventurer(){
      int character = (int)(Math.random() * 3);
      if (character == 1){
        return new WanderingTrader();
      }
      if (character == 2){
        return new SnowGolem();
      }
      else {
        return new Steve("Steve");
      }
    }

    public static Adventurer createRandomEnemy(){
      int character = (int)(Math.random() * 2);
      /*if (character == 1){
        return new Creeper(name+(int)(Math.random()*100));
      }
      if (character == 3) {
        return new Skeleton(name+(int)(Math.random()*100));
      }*/
      if (character == 0) {
        return new Spider("");
      }
      else {
        return new Zombie("");
      }
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
        System.out.println(colorByPercent(current.getHP(), current.getmaxHP()));
        startRow++;
        Text.go(startRow, column);
        System.out.print(current.getSpecialName() + ": " + current.getSpecial());
        startRow = start;
        column += Math.max(1, 74 / 3);
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
    return Text.colorize(output, color);
  }


  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen(ArrayList<Adventurer>enemies, ArrayList<Adventurer>party){
    Text.go(3,5);
    System.out.println("Your Adventurers: ");
    //draw player party
    drawParty(party, 4);
    //draw enemy party
    Text.go(9, 5);
    System.out.println("Enemies");
    drawParty(enemies, 10);

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
    Random rand = new Random();
    Random realrand = new Random((long)rand.nextInt());
    if(realrand.nextDouble()<.5){
      for(int i = 0; i<3; i++){
        enemies.add(createRandomEnemy());
      }
    }else{
      enemies.add(new Boss(""));
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    ArrayList<Adventurer> party = new ArrayList<Adventurer>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    party.add(new Steve(""));
    for(int i = 1; i<3; i++){
    party.add(createRandomAdventurer());
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    for(int i = 0; i<party.size(); i++){
      for(int j = 0; j<enemies.size(); j++){
        party.get(i).addEnemies(enemies.get(j));
      }
    }

    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    String actionWho;
    Scanner in = new Scanner(System.in);
    //You can add parameters to draw screen!
    drawBackground();
    drawScreen(enemies, party);
    //Main loop

    //display this prompt at the start of the game.


    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) ){
      if (enemies.size() == 0){
        TextBox(20,5,70,3,"You WIN");
        break;
      }else if (party.size()==0){
        TextBox(20,5,70,3,"You lose :(");
        break;
      }
      for(int i = 0; i < enemies.size(); i++){
        if(enemies.get(i).getHP()<=0){
          enemies.remove(i);
          drawBackground();
          drawScreen(enemies, party);
          TextBox(20,5,70,3,enemies.get(i).getName()+" has died.");
        }
      }
      for(int i = 0; i < party.size(); i++){
        if(party.get(i).getHP()<=0){
          party.remove(i);
          drawBackground();
          drawScreen(enemies,party);
          TextBox(20,5,70,3,party.get(i).getName()+" has died.");

        }
      }




      //////////////////////////////////////////
      if (partyTurn){
        drawScreen(enemies, party);
        String preprompt = "Enter command for "+party.get(whichPlayer)+": attack(a)/support(su)/special attack(sp)/special ability(sa)/quit(q)";
        TextBox(23,5,70,2,preprompt);
        input = in.nextLine();
        ///////////////////////////////////////

        if(input.equals("a")){
          TextBox(23,5,70,3,"Which enemy do you want to attack? (0-2 left to right)");
          actionWho = in.nextLine();
          TextBox(20,5,70,3, party.get(whichPlayer).attack(enemies.get(Integer.parseInt(actionWho))));
        }
        else if(input.equals("sp")){
          TextBox(23,5,70,3,"Which enemy do you want to special attack? (0-2 left to right)");
          actionWho = in.nextLine();
          TextBox(20,5,70,3, party.get(whichPlayer).specialAttack(enemies.get(Integer.parseInt(actionWho))));
        }
        else if(input.equals("su")){
          TextBox(23,5,70,3,"Which friend do you want to support? (0-2 left to right)");
          actionWho = in.nextLine();
          TextBox(20,5,70,3, party.get(whichPlayer).support(party.get(Integer.parseInt(actionWho))));
        }
        else if(input.equals("sa")){
          TextBox(23,5,70,3,"Which enemy do you want to use your special ability on? (0-2 left to right)");
          actionWho = in.nextLine();
          TextBox(20,5,70,3, party.get(whichPlayer).specialAbility(enemies.get(Integer.parseInt(actionWho))));
        }
        else if(input.equals("q")){
          break;
        }
        else{
          TextBox(23,5,70,3, "invalid command. please type again");
          continue;
        }
        drawScreen(enemies, party);

        /////////////////////////////////////////

        //IF ENEMY DIES
        ///////////////////////////////////

        ///////////////////////////////////

        //ADD ANOTHER PARTY MEMBER
        /////////////////////////////////////////



        //////////////////////////////////////////

        //NEXT PLAYER WILL MAKE THEIR MOVE
        /////////////////////////////////////////
        whichPlayer++;

        if(whichPlayer >= party.size()){
          String prompt = "press enter to see monster's turn";
          TextBox(23,5,70,3, prompt);
          partyTurn = false;
          whichOpponent = 0;
          input = in.nextLine();
        }
        //done with one party member
        ///////////////////////////////////////////
        ////////////////////////////////////////////
      }else{
        drawScreen(enemies, party);
        //not the party turn!


        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        //YOUR CODE HERE
        for(int i = 0; i<enemies.size();i++){
          whichPlayer = (int)(Math.random() * party.size()); //choose a random player
          TextBox(20,5,70,3, enemies.get(i).chooseAction(party.get(whichPlayer)) +"  Press enter to see the next turn");
          input = in.nextLine();
          drawScreen(enemies, party);
        }
        whichPlayer = 0;
        partyTurn=true;
        turn++;
        }

      //display the updated screen after input has been processed.
      //drawScreen(enemies, party);



    }//end of main game loop
    //while loop ended
    ///////////////////////////////////////////////////////////////
    if(input.equals("q")){
      TextBox(25,5,70,2, "You quit.");
    }

    //After quit reset things:
    quit();
  }
}
