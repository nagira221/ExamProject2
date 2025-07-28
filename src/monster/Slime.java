package monster;

import creature.Creature;
import creature.Monster;

public final class Slime extends Monster {
    public Slime(int hp, char suffix) {
        super("スライム",hp, suffix);
    }
    @Override
    public void attack(Creature target) {
        System.out.println("スライム" + getSuffix() + "は体当たり攻撃！" + target.getName() + "に5のダメージを与えた！");
        target.setHp(target.getHp() - 5);
    }
}

