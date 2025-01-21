import java.util.Random;
public class Spider extends Adventurer{
  private int cobwebs = 0;
  public Spider(String name){
    this(name, 16);
  }
  public Spider(String name, int HP){
    super("Spider", HP);
    this.armor = 0;
    this.damage = 4;
  }
  public String getSpecialName(){
    return"Cobwebs";
  }
  public int getSpecial(){
    return cobwebs;
  }
  public int getSpecialMax(){
    return 10;
  }
  public void setSpecial(int n){
    cobwebs = n;
  }
  public String attack(Adventurer other){
    Random seed = new Random();
    Random rng = new Random((long)seed.nextInt());
    int hit = this.damage+((int)(rng.nextDouble()*3-1));
    other.applyDamage(hit);
    return(this.getName()+" bit "+other.getName()+" for "+other.printDamage(hit)+" damage.  The spider also spun "+this.restoreSpecial(((int)rng.nextDouble()*3)+1)+" cobwebs");
  }
  public String support(Adventurer other){
    return(this.getName()+" got stuck in a wall and forgot it was battling.  Spider spun "+restoreSpecial(5)+" cobwebs.");
  }
  public String support(){
    return support(this);
  }
  public String specialAttack(Adventurer other){
      Random seed = new Random();
      Random rng = new Random((long)seed.nextInt());
      int damage = 7+(int)(rng.nextDouble()*10 - 5);
      other.applyDamage(damage);
      cobwebs -= 8;
      return(this.getName()+" spun a large web and trapped "+other.getName()+" in it.  Other monsters took advantage of this opportunity, dealing "+other.printDamage(damage)+".");
  }
  public String specialAbility(Adventurer other){
    return(this.getName()+" tells you a spider fact: Did you know that spiders need a 3x3 area to spawn?");
  }
  public String chooseAction(Adventurer other){
    Random seed = new Random();
    Random rng = new Random((long)seed.nextInt());
    double choice = rng.nextDouble();
    if (cobwebs >= 8) return specialAttack(other);
    else if(choice < .7) return attack(other);
    else if(choice < .9) return support();
    else return specialAbility(other);
  }
}
