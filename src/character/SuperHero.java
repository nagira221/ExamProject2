package character;

import creature.Creature;

public class SuperHero extends Hero {
    public SuperHero(Hero hero) {
        super(hero.getName(), hero.getHp()-30, hero.getWeapon());
    }
    @Override
    public void attack(Creature target) {
        int damage = (int)(getWeapon().getDamage() * 2.5);
        System.out.println(getName() + "は" + getWeapon().getName() + getWeapon().attackMessage() + "で攻撃！" + target.getName() + "に25のダメージを与えた！");
        target.setHp(target.getHp() - damage);
    }

}
