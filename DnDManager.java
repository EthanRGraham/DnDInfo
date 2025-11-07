import java.util.*;

class Dice {
    private static final Random rand = new Random();

    public static int roll(int sides) {
        return rand.nextInt(sides) + 1;
    }

    public static int rollD20() {
        return roll(20);
    }
}

class Character {
    private String name;
    private int health;
    private int armorClass;
    private int stealthMod;
    private List<String> items = new ArrayList<>();

    public Character(String name, int health, int armorClass, int stealthMod) {
        this.name = name;
        this.health = health;
        this.armorClass = armorClass;
        this.stealthMod = stealthMod;
    }

    public void addItem(String item) {
        items.add(item);
        System.out.println(item + " added to inventory.");
    }

    public void showStats() {
        System.out.println("\n--- " + name + " ---");
        System.out.println("Health: " + health);
        System.out.println("Armor Class: " + armorClass);
        System.out.println("Stealth Modifier: " + stealthMod);
        System.out.println("Inventory: " + (items.isEmpty() ? "Empty" : items));
    }

    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) health = 0;
        System.out.println(name + " takes " + amount + " damage. HP now: " + health);
    }

    public void heal(int amount) {
        health += amount;
        System.out.println(name + " heals " + amount + " HP. HP now: " + health);
    }

    public void rollStealth() {
        int roll = Dice.rollD20();
        int total = roll + stealthMod;
        System.out.println("You rolled a " + roll + " + " + stealthMod + " = " + total + " (Stealth Check)");
    }
}

public class DnDManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter character name: ");
        String name = sc.nextLine();
        System.out.print("Enter health: ");
        int hp = sc.nextInt();
        System.out.print("Enter Armor Class: ");
        int ac = sc.nextInt();
        System.out.print("Enter Stealth modifier: ");
        int stealth = sc.nextInt();

        Character player = new Character(name, hp, ac, stealth);

        boolean running = true;
        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. View Character");
            System.out.println("2. Add Item");
            System.out.println("3. Take Damage");
            System.out.println("4. Heal");
            System.out.println("5. Roll Stealth");
            System.out.println("6. Quit");
            System.out.print("Choose an action: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear newline

            switch (choice) {
                case 1:
                    player.showStats();
                    break;
                case 2:
                    System.out.print("Enter item name: ");
                    String item = sc.nextLine();
                    player.addItem(item);
                    break;
                case 3:
                    System.out.print("Damage amount: ");
                    int dmg = sc.nextInt();
                    player.takeDamage(dmg);
                    break;
                case 4:
                    System.out.print("Heal amount: ");
                    int heal = sc.nextInt();
                    player.heal(heal);
                    break;
                case 5:
                    player.rollStealth();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        sc.close();
    }
}

