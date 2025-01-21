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
    if(choice < .5){
      hit = (int)((damage+1) * damagemult);
      return(this.getName()+" shot a fireball at "+other.getName()+" and did "+hit+" damage.");
    }else{
      hit = (int)(damage * damagemult);
      return(this.getName()+" swooped down and hit "+other.getName()+" with its wings, dealing "+hit+" damage.");
    }
  }
  public String support(Adventurer other){
    return(support());
  }
  public String support(){
    return(this.getName()+" perched on the portal and just let you attack it.");
  }
  public String specialAttack(Adventurer other){
    other.setHP(0);
    return(this.getName()+" launched "+other.getName()+" into the stratosphere, killing them instantly upon re-entry.");
  }
  public String specialAbility(){
    crystals -=2;
    this.setHP(this.getHP()+25);
    return(this.getName()+" used the end crystals to heal itself for 25 HP.  However, the party destroyed 2 of them with arrows");
  }
  public String chooseAction(Adventurer other){
    
  }
}
