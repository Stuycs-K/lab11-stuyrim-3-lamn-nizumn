import java.util.Random;

public class WanderingTrader extends Adventurer{
  int Artifact1, Artifact2, Artifact3, ArtifactPowerMax;
  int Artifact;
  Random rand = new Random();

  public void whichArtifact(){
    Artifact = rand.nextInt(3) + 1;
  }

  public WanderingTrader(String name, int hp){
    super(name, hp);
    ArtifactPowerMax = rand.nextInt(11) + 45;
    whichArtifact();
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
    if (Artifact == 1){ return "Artifact1";}
    else if (Artifact == 2){ return "Artifact2";}
    else { return "Artifact3";}
  }

  public int getSpecial(){
    if (Artifact == 1){ return Artifact1;}
    else if (Artifact == 2){ return Artifact2;}
    else { return Artifact3;}
  }

  public int getSpecialMax(){
    return ArtifactPowerMax;
  }

  public void setSpecial(int n){
    if (Artifact == 1){ Artifact1 = n;}
    else if (Artifact == 2){ Artifact2 = n;}
    else { Artifact3 = n;}
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
