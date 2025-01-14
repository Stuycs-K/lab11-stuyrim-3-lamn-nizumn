import java.util.Random;

public class WanderingTrader extends Adventurer{
  int Artifact1, Artifact2, Artifact3, ArtifactPowerMax;
  int Artifact;
  int [] Artifacts = new int [] {Artifact1, Artifact2, Artifact3};
  int ability; //can acess more artifacts with greater ability
  Random rand = new Random();

  public void whichArtifact(){
    Artifact = rand.nextInt(3) + 1;
  }

  public WanderingTrader(String name, int hp){
    super(name, hp);
    ArtifactPowerMax = rand.nextInt(11) + 25;
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
    int damage = (int)((rand.nextInt(5)+1)*2);
    other.applyDamage(damage);
    int restore;
    if (damage >= 5){
      restore = damage;
    }
    else{
      restore = damage * 2;
    }
    restoreSpecial(restore);
    return this.getName() + " attacked " + other.getName() + " and dealt a damage of " + damage + " hp. In turn, the " + getSpecial() + " gained " + restore + " power!";
  }

  public String support(Adventurer other){
    int restore = (other.getmaxHP() - other.getHP()) / 2;
    return this.getName() + " replenishes the resources of " + other.getName() + " and restores " + other.restoreSpecial(restore) + " " + other.getSpecialName();
  }

  public String support(){
    int restore = (this.getmaxHP() - this.getHP()) / 2;
    int hp = rand.nextInt(4) + 5;
    setHP(getHP()+hp);
    return "Takes a swift break to restore " + this.restoreSpecial(restore) + "to " + this.getSpecialName() + " and gain " + hp + "hp.";
  }

  public String specialAttack(Adventurer other){
    if (getSpecial() >= 7){
      int damage = (int)(7 * Math.random() * 1.5);
      other.applyDamage(damage);
      setSpecial(getSpecial()-7);
      return this.getName() + " used the " + getSpecialName() + " to zap " + other.getName() + " and dealt a damge of " + damage + " hp points!";
    }
    return this.getName() + " has depleted all the power of the " + getSpecialName() + "and so must " + attack(other);
  }

}
