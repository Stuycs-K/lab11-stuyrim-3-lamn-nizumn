public class Steve extends Adventurer{
  private int blocks;
  public Steve(String name){
    this.(name, 20);
  }
  public Steve(String name, int HP){
    this.name = name;
    this.HP = HP;
    this.blocks = 0;
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
    other.applyDamage()
  }
}
