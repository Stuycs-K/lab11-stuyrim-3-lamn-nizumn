public class Tester{
  public static void main(String[] args){
    Adventurer Snow = new SnowGolem();
    Adventurer CW = new CodeWarrior();

    System.out.println(Snow.getName() + ", " + Snow.getSpecialName() + ", " + Snow.getSpecial() + ", " + Snow.getSpecialMax());
    Snow.setSpecial(29);
    System.out.println(Snow.getName() + ", " + Snow.getSpecialName() + ", " + Snow.getSpecial() + ", " + Snow.getSpecialMax());

    System.out.println(CW.attack(Snow));
    System.out.println(Snow + ", " + Snow.getHP() + "/" + Snow.getmaxHP() + " HP, " + Snow.getSpecial() + "/" + Snow.getSpecialMax() + " " + Snow.getSpecialName());
    System.out.println(Snow.attack(CW));
    System.out.println(CW + ", " + CW.getHP() + "/" + CW.getmaxHP() + " HP, " + CW.getSpecial() + "/" + CW.getSpecialMax() + " " + CW.getSpecialName());
    System.out.println(Snow.support(CW));
    System.out.println(CW.support(Snow));

    System.out.println(Snow + ", " + Snow.getHP() + "/" + Snow.getmaxHP() + " HP, " + Snow.getSpecial() + "/" + Snow.getSpecialMax() + " " + Snow.getSpecialName());
    System.out.println(CW + ", " + CW.getHP() + "/" + CW.getmaxHP() + " HP, " + CW.getSpecial() + "/" + CW.getSpecialMax() + " " + CW.getSpecialName());
    System.out.println(Snow.specialAttack(CW));
    System.out.println(CW + ", " + CW.getHP() + "/" + CW.getmaxHP() + " HP, " + CW.getSpecial() + "/" + CW.getSpecialMax() + " " + CW.getSpecialName());

  }
}
