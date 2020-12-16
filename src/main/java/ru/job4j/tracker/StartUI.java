package ru.job4j.tracker;

public class StartUI {
    public static void createItem(Input input, Tracker tracker) {
        System.out.println("=== Create a new Item ===");
        String name = input.askString("Enter name: ");
        Item item = new Item(name);
        item.setName(name);
        tracker.add(item);
    }

    public static void showAllItem(Tracker tracker) {
        System.out.println("=== Show all items ===");
        Item[] items = tracker.findAll();
        for (int i = 0; i < items.length; i++) {
            System.out.println("Name " + items[i].getName() + " id - " + items[i].getId());
        }
    }

    public static void replaceItem(Input input, Tracker tracker) {
        System.out.println("=== Replace a current Item ===");
        int id = input.askInt("Enter id: ");
        String name = input.askString("Enter name: ");
        Item item = new Item(name);
        item.setName(name);
        if (tracker.replace(id, item)) {
            System.out.println("Replace success!");
        } else {
            System.out.println("Error!");
        }
    }

    public static void deleteItem(Input input, Tracker tracker) {
        System.out.println("=== Delete Item ===");
        int id = input.askInt("Enter id: ");
        if (tracker.delete(id)) {
            System.out.println("Item # " + id + " has been deleted successfully!");
        } else {
            System.out.println("Error has been occurred!");
        }
    }

    public static void findItemById(Input input, Tracker tracker) {
        System.out.println("=== Find item by Id ===");
        int id = input.askInt("Enter id: ");
        if (tracker.findById(id) != null) {
            Item item = tracker.findById(id);
            System.out.println(item.toString());
        } else {
            System.out.println("The item with id = " + id + " was not found");
        }
    }

    public static void findItemByName(Input input, Tracker tracker) {
        System.out.println("=== Find items by name ===");
        String name = input.askString("Enter name item: ");
        Item[] item = tracker.findByName(name);
        if (item.length > 0) {
            for (int i = 0; i < item.length; i++) {
                System.out.println("Name " + item[i].getName() + "id " + item[i].getId());
            }
        } else {
            System.out.println("Items with name \"" + name + "\" were not found");
        }
    }

    public void init (Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = input.askInt("Select: ");
            if (select >= 0 && select < 7) {
                if (select == 0) {
                    StartUI.createItem(input, tracker);
                } else if (select == 1) {
                    StartUI.showAllItem(tracker);
                } else if (select == 2) {
                    StartUI.replaceItem(input, tracker);
                } else if (select == 3) {
                    StartUI.deleteItem(input, tracker);
                } else if (select == 4) {
                    StartUI.findItemById(input, tracker);
                } else if (select == 5) {
                    StartUI.findItemByName(input, tracker);
                } else {
                    System.out.println("Exit programme success!");
                    run = false;
                }
            }
        }
    }

    private void showMenu() {
        System.out.println("**************************");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
        System.out.println("**************************");
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
    }
}
