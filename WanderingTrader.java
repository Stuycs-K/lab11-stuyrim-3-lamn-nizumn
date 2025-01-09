import java.util.Random;

public class WanderingTrader extends Adventurer{
  int Artifact1, Artifact2, Artifact3, ArtifactPowerMax;


  public WanderingTrader(String name, int hp){
    super(name, hp);
    Random rand = new Random();
    ArtifactPowerMax = rand.nextInt(11) + 45;
    Artifact1 = 40;
    Artifact2 = 40;
    Artifact3 = 40;
  }

  public WanderingTrader(String name){
    this(name, 20);
  }

  public WanderingTrader(){
    this("Thomas");
  }

  //abstract methods:

  public String getSpecialName(){
    return null;
  }

  public int getSpecial(){
    return 0;
  }

  public int getSpecialMax(){
    return ArtifactPowerMax;
  }

  public void setSpecial(int n){

  }

  public String attack(Adventurer other){
    return null;
  }

  public String support(Adventurer other){
    return null;
  }

  public String support(){
    return null;
  }

  public String specialAttack(Adventurer other){
    return null;
  }

}
