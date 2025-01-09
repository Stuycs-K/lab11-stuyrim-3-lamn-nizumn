public class Steve extends Adventurer{
  private int blocks=0, damage=6;
  public Steve(String name){
    this.(name, 20);
  }
  public Steve(String name, int HP){
    this.name = name;
    this.HP = HP;
  }
  public String getSpecialName(){
    return "blocks";
  }
  public int getSpecial(){
    return blocks;
  }
  public int getSpecialMax(){
    return 36;
  }
  public void setSpecial(int n){
    this.blocks = n;
  }
  public String attack(Adventurer other){
    Random seed = new Random();
    Random hit = new Random((long)seed.nextInt());
    other.applyDamage((int) hit.nextDouble()*this.damage);
  }
  public String support(Adventurer other){
    
  }
  public String support(){

  }
  public String specialAttack(Adventurer other){

  }
  public String specialAbility(Adventurer other){

  }
}
