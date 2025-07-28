package creature;

import weapon.Weapon;

public abstract class Character implements Creature {
    private String name;
    private int hp;
    private Weapon weapon;

    public Character(String name, int hp,Weapon weapon) {
        this.setName(name);
        this.setHp(hp);
        this.setWeapon(weapon);
        if (hp < 0) {
            throw new IllegalArgumentException("初期設定に誤りがあるため、キャラクターを作成できませんでした");
        }
    }
    public final boolean isAlive() {
        return hp > 0;
    }
    public void die() {
        System.out.println(getName() + "は死んでしまった！");
    }
    public void showStatus(){
        System.out.println(getName() + ":HP " + getHp());
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getHp(){
        return hp;
    }
    public void setHp(int hp){
        this.hp = Math.max(0, hp);
    }

    public Weapon getWeapon(){
        return weapon;
    }
    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }
}
