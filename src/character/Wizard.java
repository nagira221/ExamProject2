package character;

import creature.Character;
import creature.Creature;
import weapon.Weapon;


public class Wizard extends Character {
    int mp;

    public Wizard(String name, int hp,Weapon weapon, int mp) {
        super(name,hp,weapon);
        this.mp = mp;
    }
    public void magic(Creature target){
        this.mp -= getWeapon().getCost();
        if(mp <= 0) {
            System.out.println("MPが足りない！");
            return;
        }
            target.setHp(target.getHp() - getWeapon().getDamage());
            System.out.println(getName() + getWeapon().getName() +  getWeapon().attackMessage() + target.getName() + "に3のダメージを与えた！");
    }
    public void attack(Creature target){
        System.out.println(getName() + "は石を投げた！" + target.getName() + "に3のダメージを与えた！");
        target.setHp(target.getHp()-5);
    }
    public void showStatus() {
        System.out.println(getName() + ":HP " + getHp() + " / MP " +this.mp);
    }
}
