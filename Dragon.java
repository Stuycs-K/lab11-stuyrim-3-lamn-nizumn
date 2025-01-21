public class Boss extends Adventurer{
  private int crystals = 10;
  public Boss(String name){
    this(name, 100);
  }
  public Boss(String name, int HP){
    super("The Ender Dragon", HP)
    this.armor = 0;
    this.damage = 5;
  }
  public String getSpecialName(){
    return "Crystals";
  }
  public int getSpecial(){
    return crystals;
  }
  public int getSpecialMax(){
    return 10;
  }
  public void setSpecial(n){
    crystals = n;
  }
  public String attack(Adventurer other){
    Random seed = new Random();
    Random rng = new Random((long)seed.nextInt());
    double choice = rng.nextDouble(),damagemult = rng.nextDouble()*2;
    int hit;
    if(choice < .03){
      other.setHP(0);
      return(this.getName()+" flung "+other.getName()+" dozens of blocks into the air, killing them instantly upon re-entry.");
    }else if(choice < .5){
      hit = (int)((damage+1) * damagemult);
      return(this.getName()+" shot a fireball at "+other.getName()+" and did "+hit+" damage.");
    }else{
      hit = (int)(damage * damagemult);
      return(this.getName()+" swooped down and hit "+other.getName()+" with its wings, dealing "+hit+" damage.");
    }
  }
}
