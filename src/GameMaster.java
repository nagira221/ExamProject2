import character.*;
import creature.*;
import creature.Character;
import monster.*;
import weapon.*;

import java.io.*;
import java.util.*;

public class GameMaster {
    public static void main(String[] args) {
        Random random = new Random();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<creature.Character> party = new ArrayList<>();
        party.add(new Hero("勇者", 100, new Sword()));
        party.add(new Wizard("魔法使い", 60, new Wand(),20));
        party.add(new Thief("盗賊", 70, new Dagger()));

        ArrayList<Monster> monsters = new ArrayList<>();

        int goblinCount = 0;
        int matangoCount = 0;
        int slimeCount = 0;

        // 敵をランダムに5体作成
        for (int i = 0; i < 5; i++) {
            int type = random.nextInt(3);
            char branch;
            switch (type) {
                case 0:
                    branch = (char) ('A' + goblinCount);
                    goblinCount++;
                    monsters.add(new Goblin(50, branch));
                    break;
                case 1:
                    branch = (char) ('A' + matangoCount);
                    matangoCount++;
                    monsters.add(new Matango(45, branch));
                    break;
                case 2:
                    branch = (char) ('A' + slimeCount);
                    slimeCount++;
                    monsters.add(new Slime(40, branch));
                    break;
            }
        }

        System.out.println("戦闘開始！");
        while (!party.isEmpty() && !monsters.isEmpty()) {

            // ステータス表示
            System.out.println("\n---味方パーティ---");
            for (creature.Character c : party) {
                c.showStatus();
            }
            System.out.println("\n---敵グループ---");
            for (Monster m : monsters) {
                m.showStatus();
            }

            // 味方の攻撃ターン
            for (Iterator<Character> pit = party.iterator(); pit.hasNext(); ) {
                creature.Character c = pit.next();

                if (monsters.isEmpty()) break; // 敵全滅チェック

                System.out.println("\n" + c.getName() + "の行動です。");

                // 攻撃相手の選択
                System.out.println("攻撃する敵を選んでください。");
                for (int j = 0; j < monsters.size(); j++) {
                    Monster m = monsters.get(j);
                    System.out.println(j + ": " + m.getName() + m.getSuffix() + ":HP " + m.getHp());
                }

                int targetIndex = -1;
                while (true) {
                    try {
                        System.out.print("番号を入力 > ");
                        String input = br.readLine();
                        targetIndex = Integer.parseInt(input);
                        if (targetIndex >= 0 && targetIndex < monsters.size()) {
                            break;
                        } else {
                            System.out.println("範囲外の番号です。");
                        }
                    } catch (NullPointerException | NumberFormatException e) {
                        System.out.println("正しい番号を入力してください。");
                    } catch (IOException e) {
                        System.out.println("入出力エラーです。");
                    }
                }

                Monster target = monsters.get(targetIndex);
                if (c instanceof Hero) {
                    Hero h = (Hero) c;
                    System.out.println("1: 攻撃  2: SuperHeroになる");
                    String sel = "";
                    try {
                        sel = br.readLine();
                    } catch (Exception e) {
                    }

                    if ("2".equals(sel)) {
                        if (h.getHp() <= 0) {
                            System.out.println(h.getName() + "はもう戦えません。");
                            continue;
                        }
                        System.out.println(h.getName() + "はスーパーヒーローに変身した！");
                        int idx = party.indexOf(h);
                        if (idx != -1) {
                            party.set(idx, new SuperHero(h));
                        }
                    } else {
                        h.attack(target);
                    }
                } else if (c instanceof Wizard) {
                    Wizard w = (Wizard) c;
                    System.out.println("1: 攻撃  2: 魔法攻撃");
                    String sel = "";
                    try {
                        sel = br.readLine();
                    } catch (Exception e) {
                    }

                    if ("2".equals(sel)) {
                        w.magic(target);
                    } else {
                        w.attack(target);
                    }
                } else if (c instanceof Thief) {
                    Thief t = (Thief) c;
                    System.out.println("1: 攻撃  2: 守り");
                    String sel = "";
                    try {
                        sel = br.readLine();
                    } catch (Exception e) {
                    }

                    if ("2".equals(sel)) {
                        t.guard();
                        System.out.println(t.getName() + "は守りの姿勢を取った。");
                    } else {
                        t.attack(target);
                    }
                }
                if (target.getHp() <= 0) {
                    target.die();
                    monsters.remove(target);
                } else if (target.getHp() <= 5) {
                    target.run();
                    System.out.println(target.getName() + "は逃げ出した！");
                    monsters.remove(target);
                }
            }
            // 敵の攻撃ターン
            if (!monsters.isEmpty() && !party.isEmpty()) {
                System.out.println("\n---敵の攻撃!---");
                for (Iterator<Monster> mit = monsters.iterator(); mit.hasNext(); ) {
                    Monster m = mit.next();
                    if (party.isEmpty()) break; // 味方全滅チェック

                    // ランダムに攻撃対象を選ぶ
                    int targetIndex = random.nextInt(party.size());
                    creature.Character target = party.get(targetIndex);

                    m.attack(target);

                    // 攻撃で倒れた味方をパーティから削除
                    if (target.getHp() <= 0) {
                        target.die();
                        party.remove(target);
                    }
                }
            }
            if (party.isEmpty()) {
                System.out.println("\n味方パーティは全滅してしまった…");
                break;
            } else if (monsters.isEmpty()) {
                // 勝利メッセージに勇者の名前を入れる
                String heroName = "";
                for (creature.Character c : party) {
                    if (c instanceof Hero || c instanceof SuperHero) {
                        heroName = c.getName();
                        break;
                    }
                }
                System.out.println("\n敵を全て倒した! " + heroName + "達は勝利した!");
                break;
            }
        }
    }
}