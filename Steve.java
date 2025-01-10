import java.util.HashMap;
public class Steve extends Adventurer{
  private ArrayList<String> armortiers = new ArrayList<String>;
  private ArrayList<String> weapontiers = new ArrayList<String>;
  private String weapon = "pickaxe", currentarmor = "none";
  private int items=0;
  public Steve(String name){
    this.("Steve", 20);
  }
  public Steve(String name, int HP){
    this.name = name;
    this.HP = HP;
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
    other.applyDamage((int) (hit.nextDouble()*this.damage+1));

  }
  public String support(Adventurer other){
    public String[][] potions = {{"resistance","increasing their armor by 3"},{"strength", "increasing their damage by 3"}};
    Random seed = new Random();
    Random choice = new Random();
    double chose = choice.nextDouble();
    int potionindex;
    if(chose<.5){
      potionindex=0;
      other.armor +=3
    }else{
      potionindex=1;
      other.damage+=3
    }
    return("Steve throws a splash potion of "+potions[potionindex][0]+" at "+other.getName()+", "+potions[potionindex][1]+".")
  }
  public String support(){

  }
  public String specialAttack(Adventurer other){

  }
  public String specialAbility(Adventurer other){
    Random seed = new Random();
    Random rng = new Random(seed);
    if(this.items>=5){
      if(rng.nextDouble() < .5){
        this.damage++;
        return("Steve used some of his items t")
      }else{
        this.armor += 2.5;
        return("Steve used some of his items to craft an piece of armor, increasing his defense! (+2.5 armor)")
      }
    }
  }
}
