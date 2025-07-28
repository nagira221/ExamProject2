package character;

import creature.Character;
import creature.Creature;

import weapon.Weapon;


public class Hero extends Character {

    public Hero(String name, int hp, Weapon weapon){
        super(name,hp,weapon);
    }
    @Override
    public void attack(Creature target){
        target.setHp(target.getHp()- getWeapon().getDamage());
        System.out.println(getName() + "は" + getWeapon().getName()  + getWeapon().attackMessage() + target.getName() + "に10のダメージを与えた！");
    }
}