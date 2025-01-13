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
    int hp = (rand.nextInt(3) + 1) * 3;
    setHP(getHP() - hp/3);
    return this + " comes to the aid of " + other + " and gives them " + hp + " hp, losing some of their own!";
  }

  public String support(){
    int hpNeeded = getmaxHP() - getmaxHP();
    if (hpNeeded * 2 > getSpecial()){
      setHP(getHP() + (getSpecial()/2 - 1));
      setSpecial(getSpecial() - (getSpecial()/2 - 1));
      return this + " used his remaining snowballs to generate hp";
    }
    else{
      setHP(getmaxHP());
      setSpecial(getSpecial() - hpNeeded * 2);
      return this + " replenished his full health using some of his snowballs";
    }
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
