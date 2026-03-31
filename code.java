import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class code {

    static Scanner scanner = new Scanner(System.in);
    static List<CruiseShip> cruiseShips = new ArrayList<>();
    static List<CargoShip> cargoShips = new ArrayList<>();
    static List<FishingBoat> fishingBoats = new ArrayList<>();
    static int shipsCount = 0;
    static File file = new File("data.txt");
    static FileWriter fileWriter;
    static Scanner fileScanner;

    abstract static class Ship {
        String name;
        int capacity;
        String propulsionType;

        abstract void Navigate();

        abstract void GetDetails();
    }

    static class CruiseShip extends Ship implements EmergencyProtocol {
        String cruiseLine;
        int passengerCapacity;

        CruiseShip(String name, String cruiseLine, int passengerCapacity, String propulsionType) {
            this.name = name;
            this.cruiseLine = cruiseLine;
            this.passengerCapacity = passengerCapacity;
            this.propulsionType = propulsionType;
        }

        void hostEntertainment() {
            System.out.println("Organizirane na zabavleniq");
        }

        @Override
        void Navigate() {
            System.out.println("Deistvie na kruizen korab po vrene na navigaciq");
        }

        @Override
        void GetDetails() {
            System.out.println("\n========================================");
            if (passengerCapacity == 1) {
                System.out.println("\nName: " + name + "\nCruise line: " + cruiseLine + "\nPassenger capacity: "
                        + passengerCapacity + " person" + "\nPropulsion type: " + propulsionType);
            } else {
                System.out.println("\nName: " + name + "\nCruise line: " + cruiseLine + "\nPassenger capacity: "
                        + passengerCapacity + " people" + "\nPropulsion type: " + propulsionType);
            }
            System.out.println("\n========================================");
        }

        public void ActivateEmergencyProtocol() {
            System.out.println("\nEmergency protocol just started.");
        }

        public void BroadcastEmergencyMessage() {
            System.out.println("\nMessage: EMERGENCY PROTOCOL JUST STARTED! FOLLOW THE INSTRUCTIONS FOR YOUR SAFETY!");
        }
    }

    static class CargoShip extends Ship {
        String cargoType;
        int cargoCapacity;

        CargoShip(String name, String cargoType, int cargoCapacity, String propulsionType) {
            this.name = name;
            this.cargoType = cargoType;
            this.cargoCapacity = cargoCapacity;
            this.propulsionType = propulsionType;
        }

        void LoadCargo() {
            System.out.println("\n" + name + " was loaded.");
        }

        @Override
        void Navigate() {
            System.out.println("Deistvie na tovaren korab po vreme na navigaciq");
        }

        @Override
        void GetDetails() {
            System.out.println("\n========================================");
            if (cargoCapacity == 1) {
                System.out.println(
                        "\nName: " + name + "\nCargo type: " + cargoType + "\nCargo capacity: " + cargoCapacity + " ton"
                                + "\nPropulsion type: " + propulsionType);
            } else {
                System.out.println(
                        "\nName: " + name + "\nCargo type: " + cargoType + "\nCargo capacity: " + cargoCapacity
                                + " tons"
                                + "\nPropulsion type: " + propulsionType);
            }
            System.out.println("\n========================================");
        }
    }

    static class FishingBoat extends Ship implements EmergencyProtocol {
        String fishingEquipment;
        int fishingCrewSize;

        FishingBoat(String name, String fihsingEquiplment, int fishingCrewSize, String propulsionType) {
            this.name = name;
            this.fishingEquipment = fihsingEquiplment;
            this.fishingCrewSize = fishingCrewSize;
            this.propulsionType = propulsionType;
        }

        void CatchFish() {
            System.out.println("\n" + name + " just caught a fish!");
        }

        @Override
        void Navigate() {
            System.out.println("Deistvie na ribolovna lodka po vreme na navigaciq");
        }

        @Override
        void GetDetails() {
            System.out.println("\n========================================");
            if (fishingCrewSize == 1) {
                System.out.println("\nName: " + name + "\nFishing equipment: " + fishingEquipment + "\nCrew size: "
                        + fishingCrewSize + " person" + "\nPropulsion type: " + propulsionType);
            } else {
                System.out.println("\nName: " + name + "\nFishing equipment: " + fishingEquipment + "\nCrew size: "
                        + fishingCrewSize + " people" + "\nPropulsion type: " + propulsionType);
            }
            System.out.println("\n========================================");
        }

        public void ActivateEmergencyProtocol() {
            System.out.println("\nEmergency protocol just started.");
        }

        public void BroadcastEmergencyMessage() {
            System.out.println("\nMessage: EMERGENCY PROTOCOL JUST STARTED! FOLLOW THE INSTRUCTIONS FOR YOUR SAFETY!");
        }
    }

    interface EmergencyProtocol {
        void ActivateEmergencyProtocol();

        void BroadcastEmergencyMessage();
    }

    static void PrintMenu() {
        System.out.println(
                "\n========================================" +
                        "\n1.   Add ship(s)" +
                        "\n2.   Search for ship(s)" +
                        "\n3.   Display all ships" +
                        "\n4.   Delete ship(s)" +
                        "\n0.   Exit" +
                        "\n========================================" +
                        "\nEnter your choice: ");
    }

    static void PrintShipTypes() {
        System.out.println("\nEnter the type of ship you want to add: " +
                "\n1.   Cruise ship" +
                "\n2.   Cargo ship" +
                "\n3.   Fishing boat" +
                "\nEnter your chocie: ");
    }

    static void PrintEngineTypes() {
        System.out.println("\nEnter the type of the engine: " +
                "\n1.   Disel" +
                "\n2.   Gas Turbine" +
                "\n3.   Dual-Fuel" +
                "\n4.   Steam Turbine" +
                "\n5.   Electric" +
                "\nEnter your chocie: ");
    }

    static void PrintCargoTypes() {
        System.out.println("\nEnter the type of cargo the ship most often carries: " +
                "\n1.   Containerized" +
                "\n2.   Bulk" +
                "\n3.   Liquid" +
                "\n4.   Breakbulk" +
                "\n5.   Refrigerated" +
                "\nEnter your choice: ");
    }

    static String ChooseEgineType() {
        int engineChoice = 0;
        String propulsionType = "";

        PrintEngineTypes();

        do {
            try {
                engineChoice = scanner.nextInt();
                scanner.nextLine();
                if (engineChoice < 1 || engineChoice > 5) {
                    System.out.println("\nError! Try again!");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nError! Try again!");
                scanner.nextLine();
            }
        } while (engineChoice < 1 || engineChoice > 5);

        switch (engineChoice) {
            case 1:
                propulsionType = "Disel";
                break;
            case 2:
                propulsionType = "Gas Turbine";
                break;
            case 3:
                propulsionType = "Dual-Fuel";
                break;
            case 4:
                propulsionType = "Steam Turbine";
                break;
            case 5:
                propulsionType = "Electric";
                break;
        }
        return propulsionType;
    }

    static void PrintSearchMenu() {
        System.out.println("\nSearch in:" +
                "\n1.   Cruise ships" +
                "\n2.   Cargo ships" +
                "\n3.   Fishing boats" +
                "\n4.   All" +
                "\nEnter your choice: ");
    }

    static void AddShip() {
        int choice = 0;
        String name;
        String propulsionType = "";
        int count = -1;

        System.out.println("\nEnter the number of ships you want ot add: ");

        do {
            try {
                count = scanner.nextInt();
                scanner.nextLine();
                if (count < 0) {
                    System.out.println("\nError! Try again: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nError! Try again: ");
                scanner.nextLine();
            }
        } while (count < 0);

        for (int i = 0; i < count; i++) {
            PrintShipTypes();

            do {
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    if (choice < 1 || choice > 3) {
                        System.out.println("\nInvalid choice! Try again: ");
                    }
                } catch (Exception e) {
                    System.out.println("\nError! Try again: ");
                    scanner.nextLine();
                }
            } while (choice < 1 || choice > 3);

            if (choice == 1 || choice == 2) {
                System.out.println("\nEnter the name of the ship: ");
            } else {
                System.out.println("\nEnter the name of the boat: ");
            }
            do {
                name = scanner.nextLine();
                if (name.isBlank()) {
                    System.out.println("\nError! Try again: ");
                }
            } while (name.isBlank());

            switch (choice) {
                case 1:
                    String cruiseLine;
                    int passengerCapacity = 0;

                    System.out.println("\nEnter the cruise line: ");
                    do {
                        cruiseLine = scanner.nextLine();
                        if (cruiseLine.isBlank()) {
                            System.out.println("\nError! Try again: ");
                        }
                    } while (cruiseLine.isBlank());
                    System.out.println("\nEnter the passenger capacity: ");
                    do {
                        try {
                            passengerCapacity = scanner.nextInt();
                            scanner.nextLine();
                            if (passengerCapacity < 1) {
                                System.out.println("\nError! Try again: ");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("\nError! Try again: ");
                            scanner.nextLine();
                        }
                    } while (passengerCapacity < 1);
                    propulsionType = ChooseEgineType();
                    cruiseShips.add(new CruiseShip(name, cruiseLine, passengerCapacity, propulsionType));
                    break;
                case 2:
                    String cargoType = "";
                    int cargoCapacity = 0;
                    int cargoTypeChocie = 0;

                    PrintCargoTypes();
                    do {
                        try {
                            cargoTypeChocie = scanner.nextInt();
                            scanner.nextLine();
                            if (cargoTypeChocie < 1 || cargoTypeChocie > 5) {
                                System.out.println("\nInvalid choice! Try again: ");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("\nError! Try again: ");
                            scanner.nextLine();
                        }
                    } while (cargoTypeChocie < 1 || cargoTypeChocie > 5);
                    switch (cargoTypeChocie) {
                        case 1:
                            cargoType = "Containerized";
                            break;
                        case 2:
                            cargoType = "Bulk";
                            break;
                        case 3:
                            cargoType = "Liquid";
                            break;
                        case 4:
                            cargoType = "Breakbulk";
                            break;
                        case 5:
                            cargoType = "Refrigerated";
                            break;
                    }
                    System.out.println("\nEnter the cargo capacity of the ship(in ton(s)): ");
                    do {
                        try {
                            cargoCapacity = scanner.nextInt();
                            scanner.nextLine();
                            if (cargoCapacity < 1) {
                                System.out.println("\nError! Try again: ");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("\nError! Try again: ");
                            scanner.nextLine();
                        }
                    } while (cargoCapacity < 1);
                    propulsionType = ChooseEgineType();
                    cargoShips.add(new CargoShip(name, cargoType, cargoCapacity, propulsionType));
                    break;
                case 3:
                    String fishingEquipment;
                    int fishingCrewSize = 0;

                    System.out.println("\nEnter the fishing equipment of the boat: ");
                    do {
                        fishingEquipment = scanner.nextLine();
                        if (fishingEquipment.isBlank()) {
                            System.out.println("\nError! Try again: ");
                        }
                    } while (fishingEquipment.isBlank());
                    System.out.println("\nEnter the size of the fishing crew: ");
                    do {
                        try {
                            fishingCrewSize = scanner.nextInt();
                            scanner.nextLine();
                            if (fishingCrewSize < 1) {
                                System.out.println("\nError! Try again: ");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("\nError! Try again: ");
                            scanner.nextLine();
                        }
                    } while (fishingCrewSize < 1);
                    propulsionType = ChooseEgineType();
                    fishingBoats.add(new FishingBoat(name, fishingEquipment, fishingCrewSize, propulsionType));
                    break;
            }
            shipsCount++;
        }
    }

    static void SearchForShip() {
        if (shipsCount == 0) {
            System.out.println("\nNo ships are added! Try again later!");
            return;
        } else {
            int chocie = 0;
            String searchName;

            System.out.println("\nEnter the type of the ship you want to search for:");
            PrintSearchMenu();
            do {
                try {
                    chocie = scanner.nextInt();
                    scanner.nextLine();
                    if (chocie < 1 || chocie > 4) {
                        System.out.println("\nInvalid choice! Try again: ");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\nError! Try again: ");
                    scanner.nextLine();
                }
            } while (chocie < 1 || chocie > 4);

            switch (chocie) {
                case 1:
                    if (cruiseShips.isEmpty()) {
                        System.out.println("\nNo cruise ships are added! Try again later!");
                        return;
                    }
                    break;
                case 2:
                    if (cargoShips.isEmpty()) {
                        System.out.println("\nNo cargo ships are added! Try again later!");
                        return;
                    }
                    break;
                case 3:
                    if (fishingBoats.isEmpty()) {
                        System.out.println("\nNo fishing boats are added! Try again later!");
                        return;
                    }
                    break;
            }

            System.out.println("\nEnter the name of the ship you want to search for: ");
            do {
                searchName = scanner.nextLine();
                if (searchName.isBlank()) {
                    System.out.println("\nError! Try again: ");
                }
            } while (searchName.isBlank());

            switch (chocie) {
                case 1:
                    for (CruiseShip cruiseShip : cruiseShips) {
                        if (cruiseShip.name.equals(searchName)) {
                            cruiseShip.GetDetails();
                        }
                    }
                    break;
                case 2:
                    for (CargoShip cargoShip : cargoShips) {
                        if (cargoShip.name.equals(searchName)) {
                            cargoShip.GetDetails();
                        }
                    }
                    break;
                case 3:
                    for (FishingBoat fishingBoat : fishingBoats) {
                        if (fishingBoat.name.equals(searchName)) {
                            fishingBoat.GetDetails();
                        }
                    }
                    break;
                case 4:
                    for (CruiseShip cruiseShip : cruiseShips) {
                        if (cruiseShip.name.equals(searchName)) {
                            cruiseShip.GetDetails();
                        }
                    }
                    for (CargoShip cargoShip : cargoShips) {
                        if (cargoShip.name.equals(searchName)) {
                            cargoShip.GetDetails();
                        }
                    }
                    for (FishingBoat fishingBoat : fishingBoats) {
                        if (fishingBoat.name.equals(searchName)) {
                            fishingBoat.GetDetails();
                        }
                    }
                    break;
            }
        }
    }

    static void DisplayAllShips() {
        if (shipsCount == 0) {
            System.out.println("\nNo ships are added! Try again later!");
            return;
        } else {
            int chocie = 0;

            System.out.println("\nEnter the type of the ship you want to search for:");
            PrintSearchMenu();
            do {
                try {
                    chocie = scanner.nextInt();
                    scanner.nextLine();
                    if (chocie < 1 || chocie > 4) {
                        System.out.println("\nInvalid choice! Try again: ");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\nError! Try again: ");
                    scanner.nextLine();
                }
            } while (chocie < 1 || chocie > 4);

            switch (chocie) {
                case 1:
                    if (cruiseShips.isEmpty()) {
                        System.out.println("\nNo cruise ships are added!");
                        return;
                    } else {
                        System.out.println("\nCruise ships:");
                        for (CruiseShip cruiseShip : cruiseShips) {
                            cruiseShip.GetDetails();
                        }
                    }
                    break;
                case 2:
                    if (cargoShips.isEmpty()) {
                        System.out.println("\nNo cargo ships are added!");
                        return;
                    } else {
                        System.out.println("\nCargo ships:");
                        for (CargoShip cargoShip : cargoShips) {
                            cargoShip.GetDetails();
                        }
                    }
                    break;
                case 3:
                    if (fishingBoats.isEmpty()) {
                        System.out.println("\nNo fishing boats are added!");
                        return;
                    } else {
                        System.out.println("\nFishing boats:");
                        for (FishingBoat fishingBoat : fishingBoats) {
                            fishingBoat.GetDetails();
                        }
                    }
                    break;
                case 4:
                    if (!cruiseShips.isEmpty()) {
                        System.out.println("\nCruise ships:");
                        for (CruiseShip cruiseShip : cruiseShips) {
                            cruiseShip.GetDetails();
                        }
                    }
                    if (!cargoShips.isEmpty()) {
                        System.out.println("\nCargo ships:");
                        for (CargoShip cargoShip : cargoShips) {
                            cargoShip.GetDetails();
                        }
                    }
                    if (!fishingBoats.isEmpty()) {
                        System.out.println("\nFishing boats:");
                        for (FishingBoat fishingBoat : fishingBoats) {
                            fishingBoat.GetDetails();
                        }
                    }
                    break;
            }
        }
    }

    static void DeleteShip() {
        if (shipsCount == 0) {
            System.out.println("\nNo ships are added! Try again later!");
            return;
        } else {
            int chocie=0;
            String searchName;

            System.out.println("\nEnter the type of the ship you want to delete:");
            PrintSearchMenu();
            do {
                try {
                    chocie = scanner.nextInt();
                    scanner.nextLine();
                    if (chocie < 1 || chocie > 4) {
                        System.out.println("\nInvalid choice! Try again: ");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\nError! Try again: ");
                    scanner.nextLine();
                }
            } while (chocie < 1 || chocie > 4);

            switch (chocie) {
                case 1:
                    if (cruiseShips.isEmpty()) {
                        System.out.println("\nNo cruise ships are added! Try again later!");
                        return;
                    }
                    break;
                case 2:
                    if (cargoShips.isEmpty()) {
                        System.out.println("\nNo cargo ships are added! Try again later!");
                        return;
                    }
                    break;
                case 3:
                    if (fishingBoats.isEmpty()) {
                        System.out.println("\nNo fishing boats are added! Try again later!");
                        return;
                    }
                    break;
            }

            System.out.println("\nEnter the name of the ship you want to delete: ");
            do {
                searchName = scanner.nextLine();
                if (searchName.isBlank()) {
                    System.out.println("\nError! Try again: ");
                }
            } while (searchName.isBlank());

            switch (chocie) {
                case 1:
                    List<CruiseShip> cruiseShipsForDelete = new ArrayList<>();

                    for (CruiseShip cruiseShip : cruiseShips) {
                        if (cruiseShip.name.equals(searchName)) {
                            cruiseShip.GetDetails();
                            System.out.println("\n" + cruiseShip.name + " was deleted succesfully!");
                            cruiseShipsForDelete.add(cruiseShip);
                            shipsCount--;
                        }
                    }
                    for (CruiseShip cruiseShip : cruiseShipsForDelete) {
                        cruiseShips.remove(cruiseShip);
                    }
                    break;
                case 2:
                    List<CargoShip> cargoShipsForDelete = new ArrayList<>();

                    for (CargoShip cargoShip : cargoShips) {
                        if (cargoShip.name.equals(searchName)) {
                            cargoShip.GetDetails();
                            System.out.println("\n" + cargoShip.name + " was deleted succesfully!");
                            cargoShipsForDelete.add(cargoShip);
                            shipsCount--;
                        }
                    }
                    for (CargoShip cargoShip : cargoShipsForDelete) {
                        cargoShips.remove(cargoShip);
                    }
                    break;
                case 3:
                    List<FishingBoat> fishingBoatsForDelete = new ArrayList<>();

                    for (FishingBoat fishingBoat : fishingBoats) {
                        if (fishingBoat.name.equals(searchName)) {
                            fishingBoat.GetDetails();
                            System.out.println("\n" + fishingBoat.name + " was deleted succesfully!");
                            fishingBoatsForDelete.add(fishingBoat);
                            shipsCount--;
                        }
                    }
                    for (FishingBoat fishingBoat : fishingBoatsForDelete) {
                        fishingBoats.remove(fishingBoat);
                    }
                    break;
                case 4:
                    if (!cruiseShips.isEmpty()) {
                        List<CruiseShip> cruiseShipsForDeleteInAll = new ArrayList<>();

                        for (CruiseShip cruiseShip : cruiseShips) {
                            if (cruiseShip.name.equals(searchName)) {
                                cruiseShip.GetDetails();
                                System.out.println("\n" + cruiseShip.name + " was deleted succesfully!");
                                cruiseShipsForDeleteInAll.add(cruiseShip);
                                shipsCount--;
                            }
                        }
                        for (CruiseShip cruiseShip : cruiseShipsForDeleteInAll) {
                            cruiseShips.remove(cruiseShip);
                        }
                    }
                    if (!cargoShips.isEmpty()) {
                        List<CargoShip> cargoShipsForDeleteInAll = new ArrayList<>();

                        for (CargoShip cargoShip : cargoShips) {
                            if (cargoShip.name.equals(searchName)) {
                                cargoShip.GetDetails();
                                System.out.println("\n" + cargoShip.name + " was deleted succesfully!");
                                cargoShipsForDeleteInAll.add(cargoShip);
                                shipsCount--;
                            }
                        }
                        for (CargoShip cargoShip : cargoShipsForDeleteInAll) {
                            cargoShips.remove(cargoShip);
                        }
                    }
                    if (!fishingBoats.isEmpty()) {
                        List<FishingBoat> fishingBoatsForDeleteInAll = new ArrayList<>();

                        for (FishingBoat fishingBoat : fishingBoats) {
                            if (fishingBoat.name.equals(searchName)) {
                                fishingBoat.GetDetails();
                                System.out.println("\n" + fishingBoat.name + " was deleted succesfully!");
                                fishingBoatsForDeleteInAll.add(fishingBoat);
                                shipsCount--;
                            }
                        }
                        for (FishingBoat fishingBoat : fishingBoatsForDeleteInAll) {
                            fishingBoats.remove(fishingBoat);
                        }
                    }
                    break;
            }
        }
    }

    static void SaveData() {
        try {
            fileWriter = new FileWriter("data.txt", false);

            fileWriter.write(shipsCount + "\n");

            if (!cruiseShips.isEmpty()) {
                for (CruiseShip cruiseShip : cruiseShips) {
                    fileWriter.write("cruise|" + cruiseShip.name + "|" + cruiseShip.cruiseLine + "|"
                            + cruiseShip.passengerCapacity
                            + "|" + cruiseShip.propulsionType + "|\n");
                }
            }
            if (!cargoShips.isEmpty()) {
                for (CargoShip cargoShip : cargoShips) {
                    fileWriter
                            .write("cargo|" + cargoShip.name + "|" + cargoShip.cargoType + "|" + cargoShip.cargoCapacity
                                    + "|" + cargoShip.propulsionType + "|\n");
                }
            }
            if (!fishingBoats.isEmpty()) {
                for (FishingBoat fishingBoat : fishingBoats) {
                    fileWriter.write(
                            "fishing|" + fishingBoat.name + "|" + fishingBoat.fishingEquipment + "|"
                                    + fishingBoat.fishingCrewSize
                                    + "|" + fishingBoat.propulsionType + "|\n");
                }
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error has occurred!");
        }
    }

    static void LoadData() {
        try {
            fileScanner = new Scanner(file);

            shipsCount = fileScanner.nextInt();
            fileScanner.nextLine();

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\|");

                String shipType = parts[0].trim();

                switch (shipType) {
                    case "cruise":
                        String cruiseName = parts[1].trim();
                        String cruiseLine = parts[2].trim();
                        int passengerCapacity = Integer.parseInt(parts[3].trim());
                        String cruisePropulsionType = parts[4].trim();
                        CruiseShip cruiseShip = new CruiseShip(cruiseName, cruiseLine, passengerCapacity,
                                cruisePropulsionType);
                        cruiseShips.add(cruiseShip);
                        break;
                    case "cargo":
                        String cargoName = parts[1].trim();
                        String cargoType = parts[2].trim();
                        int cargoCapacity = Integer.parseInt(parts[3].trim());
                        String cargoPropulsionType = parts[4].trim();
                        CargoShip cargoShip = new CargoShip(cargoName, cargoType, cargoCapacity, cargoPropulsionType);
                        cargoShips.add(cargoShip);
                        break;
                    case "fishing":
                        String fishingName = parts[1].trim();
                        String fishingEquipment = parts[2].trim();
                        int fishingCrewSize = Integer.parseInt(parts[3].trim());
                        String fishingPropulsionType = parts[4].trim();
                        FishingBoat fishingBoat = new FishingBoat(fishingName, fishingEquipment, fishingCrewSize,
                                fishingPropulsionType);
                        fishingBoats.add(fishingBoat);
                        break;
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("An error has occurred!");
        }
    }

    public static void main(String[] args) {
        int choice = -1;

        if (file.length() > 0) {
            LoadData();
            System.out.println("Data loaded succesfully!");
        } else {
            System.out.println("No data to be loaded!");
        }

        while (choice != 0) {
            PrintMenu();
            do {
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    if (choice < 0 || choice > 4) {
                        System.out.println("\nInvalid choice! Try again: ");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\nError! Try again: ");
                    scanner.nextLine();
                }
            } while (choice < 0 || choice > 4);
            switch (choice) {
                case 1:
                    AddShip();
                    break;
                case 2:
                    SearchForShip();
                    break;
                case 3:
                    DisplayAllShips();
                    break;
                case 4:
                    DeleteShip();
                    break;
                case 0:
                    SaveData();
                    System.out.println("\nClosing the program!\nData saved succesfully!");
                    break;
            }
        }

        scanner.close();
    }
}
