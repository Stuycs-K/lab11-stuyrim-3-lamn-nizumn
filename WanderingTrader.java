import java.util.Random;
import java.util.ArrayList;

public class WanderingTrader extends Adventurer{
  int MagicMirror, GoldenGoblet, InfinityPouch, ArtifactPowerMax;
  int Artifact;
  int ability; //can acess more artifacts with greater ability
  Random rand = new Random();

  public void whichArtifact(){
    Artifact = rand.nextInt(3) + 1;
  }

  public WanderingTrader(String name, int hp){
    super(name, hp);
    ArtifactPowerMax = rand.nextInt(11) + 25;
    whichArtifact();
    MagicMirror = 40;
    GoldenGoblet = 40;
    InfinityPouch = 40;
  }

  public WanderingTrader(String name){
    this(name, 20);
  }

  public WanderingTrader(){
    this("The Wandering Trader" + (int)(Math.random()*100));
  }

  //abstract methods:

  public String getSpecialName(){
    if (Artifact == 1){ return "Magic Mirror";}
    else if (Artifact == 2){ return "Golden Goblet";}
    else { return "Infinity Pouch";}
  }

  public int getSpecial(){
    if (Artifact == 1){ return MagicMirror;}
    else if (Artifact == 2){ return GoldenGoblet;}
    else { return InfinityPouch;}
  }

  public int getSpecialMax(){
    return ArtifactPowerMax;
  }

  public void setSpecial(int n){
    if (Artifact == 1){ MagicMirror = n;}
    else if (Artifact == 2){ GoldenGoblet = n;}
    else { InfinityPouch = n;}
  }

  public String attack(Adventurer other){
    int damage = (rand.nextInt(5)+1)*2;
    other.applyDamage(damage);
    ability += damage;
    int restore;
    if (damage >= 5){
      restore = damage;
    }
    else{
      restore = damage * 2;
    }
    setSpecial(getSpecial() + restore);
    return this.getName() + " attacked " + other.getName() + " and dealt a damage of " + damage + " hp. In turn, the " + getSpecialName() + " gained " + restore + "power" + "!";
  }

  public String support(Adventurer other){
    int restore = (other.getmaxHP() - other.getHP()) / 2;
    other.setSpecial(other.getSpecial() + restore);
    return this.getName() + " replenishes the resources of " + other.getName() + " and restores " + other.restoreSpecial(restore) + " " + other.getSpecialName();
  }

  public String support(){
    int restore = (this.getmaxHP() - this.getHP()) / 2;
    int hp = rand.nextInt(4) + 5;
    setHP(getHP()+hp);
    return "Takes a swift break to restore " + this.restoreSpecial(restore) + " power to " + this.getSpecialName() + " and gain " + hp + " hp.";
  }

  public String specialAttack(Adventurer other){
    if (getSpecial() >= 7){
      int damage = (int)(7 * (Math.random() + 1)+1);
      other.applyDamage(damage);
      ability += damage;
      setSpecial(getSpecial()-7);
      return this.getName() + " used his " + getSpecialName() + " to zap " + other.getName() + " and dealt a damge of " + damage + " hp points!";
    }
    return this.getName() + " has depleted all the power of his " + getSpecialName() + "and so must " + attack(other);
  }
  public String specialAbility(Adventurer other){
    Random seed = new Random();
    Random rng = new Random((long)seed.nextInt());
    if(MagicMirror>=20 && GoldenGoblet>=20 && InfinityPouch>=20){
      int damage = (30 + (int)(rng.nextDouble()*10-5));
      other.applyDamage(damage);
      MagicMirror -= 20;
      GoldenGoblet -=20;
      InfinityPouch -=20;
      return(this.getName()+" used all of their artifacts to unleash a laser on "+other.getName()+" and dealt "+damage+" damage.");
    }
    else{
      attack(other);
      return(this.getName()+" didn't have enough artifact power to use their ability, instead they must attack "+other.getName());
    }
  }
}
