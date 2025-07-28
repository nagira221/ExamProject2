package character;

import creature.Character;
import creature.Creature;
import weapon.Weapon;

public class Thief extends Character {
    private boolean guard;
    public Thief(String name,int hp,Weapon weapon) {
        super(name, hp,weapon);
    }
    @Override
    public void attack(Creature target) {
        target.setHp(target.getHp()- getWeapon().getDamage()*2);
        System.out.println(getName() +"は素早く二回攻撃した！" + getWeapon().getName() + getWeapon().attackMessage() + target.getName() + "に5のダメージを与えた！");
    }
    public void guard(){
        this.setGuard(true);
    }
    public void setHP(){
        if(guard == true){
            this.setGuard(false);
            System.out.println("しかし、" + this.getName() + "は攻撃を回避し、ダメージが入らなかった！");
        } else{
            super.setHp(getHp());
        }
    }
    public boolean getGuard() {
        return guard;
    }

    public void setGuard(boolean guard) {
        this.guard = guard;
    }

}
