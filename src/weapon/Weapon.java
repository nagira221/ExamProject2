package weapon;

public abstract class Weapon {
    private String name;
    private int damage;
    private int cost = 0;

    public Weapon(String name, int damage) {
        this.setName(name);
        this.setDamage(damage);
    };
    public abstract String attackMessage();

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getDamage(){
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public int getCost(){
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }

}
