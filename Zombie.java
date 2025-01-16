import java.util.Random;
public class Zombie extends Adventurer{
  private int rot = 0;
  public Zombie(String name){
    this(name, 10);
  }
  public Zombie(String name, int HP){
    super("A Zombie",20);
    Random seed = new Random();
    Random rng = new Random((long)seed.nextInt());
    this.armor = (int)(rng.nextDouble() * 16);
    this.damage = (int)(rng.nextDouble() * 3)+4;
  }
  public int getSpecial(){
    return rot;
  }
  public String getSpecialName(){
    return "rot";
  }
  public int getSpecialMax(){
    return 100;
  }
  public void setSpecial(int n){
    rot = n;
  }
  public String attack(Adventurer other){
    Random seed = new Random();
    Random rng = new Random((long)seed.nextInt());
    double choice = rng.nextDouble();
    if(choice < .1){
      other.applyDamage(damage*2);
      restoreSpecial(5);
      return(this.getName()+" hit "+other.getName()+" twice dealing "+other.printDamage(damage*2)+" damage.  "+this.getName()+" rots a little.");
    }else if(choice < .7){
      other.applyDamage(damage);
      restoreSpecial(10);
      return(this.getName()+" hit "+other.getName()+" once dealing "+other.printDamage(damage)+" damage.  "+this.getName()+" rots a little.");
    }else{
      restoreSpecial(15);
      return(this.getName()+" tried to hit "+other.getName()+" but is a stupid zombie and missed.  "+this.getName()+" rots a lot");
    }
  }
  public String support(Adventurer other){
    return(this.getName()+" called for reinforcements but it isn't a leader zombie so nothing happened.");
  }
  public String support(){
    return support(this);
  }
  public String specialAttack(Adventurer other){
    other.poison +=5;
    rot -= 40;
    setHP(this.getHP() - 5);
    return(this.getName()+" is rotting so much that it threw pieces of its body at "+other.getName()+" applying 5 poison but also doing 5 damage to themself.");
  }
  public String chooseAction(Adventurer other){
    Random seed = new Random();
    Random rng = new Random((long)seed.nextInt());
    double choice = rng.nextDouble();
    if(rot >= 40 && this.getHP()> 5) return specialAttack(other);
    else if(choice < .8) return attack(other);
    else return support();
  }
}
