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
    this("Snow Golem " +(int)(Math.random()*100));
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
    else if (n < 0) {
      SnowBalls = 0;
    }
    else{
      SnowBalls = n;
    }
  }

  public String support(Adventurer other){
    int hp = (rand.nextInt(3) + 1) * 3;
    setHP(getHP() - hp/3);
    other.setHP(other.getHP() + hp);
    return this + " comes to the aid of " + other + " and gives them " + hp + " hp, losing some of their own!";
  }

  public String support(){
    int hpNeeded = getmaxHP() - getHP();
    if (hpNeeded * 2 > getSpecial()){
      int restoredHP = getSpecial() / 2;
      setHP(getHP() + restoredHP);
      setSpecial(getSpecial() - restoredHP);
      return this + " used their remaining snowballs to generate " + restoredHP + " hp.";
    }
    else{
      setHP(getmaxHP());
      setSpecial(getSpecial() - hpNeeded * 2);
      return this + " replenished their full health using some of their snowballs";
    }
  }

  public String attack(Adventurer other){
    int damage = 3;
    other.applyDamage(damage);  //might pass limit
    setSpecial(getSpecial() + 1);
    return this + " inflicted a damge of " + damage + " hp on " + other + " using his snowballs and gains a snowball in turn!";
  }

  public String specialAttack(Adventurer other){
    if (getSpecial() > 6){
      int damage = (rand.nextInt(3) + 2) * 3;
      other.applyDamage(damage);
      setSpecial(getSpecial() - 6);
      return this + " used their special attack to throw " + damage + " snowballs at " + other +", depleting them of " + damage + " hp!";
    }
    else{
      return this + " has run out of snoballs and instead " + attack(other);
    }
  }

  public void splitAttack(Adventurer opp1, Adventurer opp2, Adventurer opp3){
    System.out.println(this + " splits into 3 mini versions of themselves and attacks all three opponents at once!");
    opp1.applyDamage(3);
    System.out.println(opp1 + " has lost 3 hp!");
    opp2.setSpecial(getSpecial() - 3);
    System.out.println(opp2 + " has lost 3 " + getSpecialName() + "!");
    opp3.setSpecial(getSpecial() - 2);
    opp3.applyDamage(1);
    System.out.println(opp3 + " Has lost 2 " + getSpecialName() + " and 1 hp!");
  }


}
