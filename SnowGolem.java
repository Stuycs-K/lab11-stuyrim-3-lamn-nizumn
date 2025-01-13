import java.util.Random;

public class SnowGolem extends Adventurer{
  int SnowBalls, SnowBallsMax;
  Random rand = new Random();

  public SnowGolem(String name, int hp){
    super(name, hp);
    SnowBallsMax = rand.nextInt(10) + 31;
    SnowBalls = 30;
  }

  public SnowGolem(String name){
    this(name, 20);
  }

  public SnowGolem(){
    this("Benny");
  }

  //abstract methods

  public String getSpecialName(){
    return "snowballs";
  }

  public int getSpecial(){
    return SnowBalls;
  }

  public int getSpecialMax(){
    return SnowBallsMax;
  }

  public void setSpecial(int n){
    if (n > getSpecialMax()){
      SnowBalls = SnowBallsMax;
    }
    else{
      SnowBalls = n;
    }
  }

  public String support(Adventurer other){
    return null;
  }

  public String support(){
    return null;
  }

  public String attack(Adventurer other){
    return null;
  }

  public String specialAttack(Adventurer other){
    return null;
  }

  public String specialAblity(Adventurer other){
    return null;
  }


}
