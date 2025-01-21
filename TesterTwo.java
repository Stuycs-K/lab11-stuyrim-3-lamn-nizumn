public class TesterTwo{

  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){
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

  }


  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    Text.go(startRow, startCol);
    System.out.print(s);
  }


  public static void TextBox( int row, int col, int width, int height, String text){

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

  }


  public static void main (String [] args){
    drawBackground();

    // Example usage of TextBox
    String exampleText = "This is an example of text that spans multiple lines and demonstrates the TextBox functionality.";
    TextBox(5, 10, 20, 4, exampleText);
    Text.go(30, 1);
    Text.reset();
    Text.showCursor();
  }
}
