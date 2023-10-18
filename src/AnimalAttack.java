public class AnimalAttack {
    private String attackType;
    private int damage;


    public AnimalAttack(String attackType, int damage){
        this.attackType = attackType;
        if(damage <= 0){
            this.damage = 1;
        }else{
            this.damage = damage;
        }
    }

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
