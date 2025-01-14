import java.util.Random;
public class Steve extends Adventurer{
  private int items=0;
  public Steve(String name){
    this("Steve", 20);
  }
  public Steve(String name, int HP){
    super(name, HP);
    this.armor = 0;
    this.damage = 5;
  }
  public String getSpecialName(){
    return "items";
  }
  public int getSpecial(){
    return items;
  }
  public int getSpecialMax(){
    return 36;
  }
  public void setSpecial(int n){
    this.items = n;
  }
  public String attack(Adventurer other){
    Random seed = new Random();
    Random hit = new Random((long)seed.nextInt());
    double rng = hit.nextDouble();
    int resourcesgathered = (int)(hit.nextDouble() * 10);
    this.restoreSpecial(resourcesgathered);
    if(rng <.2){
      other.applyDamage((int)(this.damage * 1.5));
      return("Steve swung his weapon at the enemy and dealt a critical hit!"+" He dealt "+(int)(this.damage *1.5)+" damage.  He also did some off-camera mining and gathered "+resourcesgathered+" useful items.");
    }else if(rng >=.2 && rng <.4){
    this.restoreSpecial(5);
      return("Steve swung his weapon at the enemy but he missed.  Instead, he collected "+(resourcesgathered+5)+" useful items.");
    }else{
      other.applyDamage(this.damage);
      return("Steve swung his weapon at the enemy and dealt "+this.damage+" damage.  He also did some off-camera mining and gathered "+resourcesgathered+" useful items.");
    }

  }
  public String support(Adventurer other){
    String[][] potions = {{"resistance I","increasing their armor by 3"},{"strength I", "increasing their damage by 3"},{"instant health I","healing 6 HP"}};
    Random seed = new Random();
    Random choice = new Random();
    double chose = choice.nextDouble();
    int potionindex;
    if(chose < .30){
      potionindex=0;
      other.armor +=3;
    }else if(chose >= .30 && chose < .60){
      potionindex=1;
      other.damage+=3;
    }else if(chose >= .60 && chose < .90){
      potionindex=2;
      if (other.getHP()+6 <= other.getmaxHP()){
        other.setHP(other.getHP()+6);
      }else{
        other.setHP(other.getmaxHP());
      }
    }else{
      return("Steve tried to throw a potion at "+other.getName()+" but he couldn't find one");
    }
    return("Steve threw a splash potion of "+potions[potionindex][0]+" at "+other.getName()+", "+potions[potionindex][1]+".");
  }
  public String support(){
    String[][] potions = {{"resistance I","increasing his armor by 3"},{"strength I", "increasing his damage by 3"},{"instant health I","healing 6 HP"}};
    Random seed = new Random();
    Random choice = new Random();
    double chose = choice.nextDouble();
    int potionindex;
    if(chose < .30){
      potionindex=0;
      armor +=3;
    }else if(chose >= .30 && chose < .60){
      potionindex=1;
      damage+=3;
    }else if(chose >= .60 && chose < .90){
      potionindex=2;
      if (this.getHP()+6 <= this.getmaxHP()){
        this.setHP(this.getHP()+6);
      }else{
        this.setHP(this.getmaxHP());
      }
    }else{
      return("Steve tried to drink a potion but he couldn't find one");
    }
    return("Steve drank a potion of "+potions[potionindex][0]+", "+potions[potionindex][1]+".");
  }
  public String specialAttack(Adventurer other){
    if(this.items >= 10){
      items -=10;
      other.stunChance = .5;
      return "Steve built a building around "+other.getName()+" and made them stuck.";
    }else{
      other.applyDamage(this.items*2);
      items = 0;
      return "Steve tried to build a building around "+ other.getName()+", but he did not have enough blocks.  Instead, Steve dropped the blocks on "+other.getName()+" and did "+this.items*2+" damage.";
    }
  }
  public String specialAbility(){
    Random seed = new Random();
    Random rng = new Random((long)seed.nextInt());
    if(this.items>=5){
      items -= 5;
      if(rng.nextDouble() < .5){
        this.damage++;
        return("Steve used some of his items to craft a better weapon, increasing his attack! (+1 max hit)");
      }else{
        this.armor += 2.5;
        return("Steve used some of his items to craft an piece of armor, increasing his defense! (+2.5 armor)");
      }
    }else{
      int itemgather = ((int)(rng.nextDouble()*3)+2);
      this.restoreSpecial(itemgather);
      return"Steve tried to craft an upgraded piece of equipment but he was too dumb and forgot how to craft.  In his rage, he threw the crafting table at the wall, causing a small explosion and mining "+itemgather+" blocks.";
    }
  }
}
