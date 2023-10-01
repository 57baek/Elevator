import java.util.Scanner;
public class Elevator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Set the current floor: ");
        int currentFloor = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Set the current Elevator1's floor: ");
        int elevatorFloor1 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Set the current Elevator2's floor: ");
        int elevatorFloor2 = scanner.nextInt();
        scanner.nextLine();
        Elevator.setInitialSetting(currentFloor, elevatorFloor1, elevatorFloor2);

        boolean Maintenance = false;
        while(!Maintenance) {

            int elevatorNode1 = Elevator.elevatorFloor1;
            int elevatorNode2 = Elevator.elevatorFloor2;

            int elevatorCounts1 = 0; // reset the counts to 0 after maintenance
            int elevatorCounts2 = 0;

            System.out.print("Is Elevator1 already heading to other floor? (true/false): ");
            boolean node1 = scanner.nextBoolean();
            if (node1) {
                System.out.print("Which floor?: ");
                elevatorNode1 = scanner.nextInt();
                scanner.nextLine();
            }
            System.out.print("Is Elevator2 already heading to other floor? (true/false): ");
            boolean node2 = scanner.nextBoolean();
            if (node2) {
                System.out.print("Which floor?: ");
                elevatorNode2 = scanner.nextInt();
                scanner.nextLine();
            }



            System.out.println("You are on the " + getFloorString(Elevator.currentFloor) + " floor.");
            System.out.println("Elevator 1 is on the " + getFloorString(Elevator.elevatorFloor1) + " floor.");
            if (node1) {
                System.out.println("Elevator 1 is moving to the " + getFloorString(elevatorNode1) + " floor.");
            }
            System.out.println("Elevator 2 is on the " + getFloorString(Elevator.elevatorFloor2) + " floor.");
            if (node2) {
                System.out.println("Elevator 2 is moving to the " + getFloorString(elevatorNode2) + " floor.");
            }

            System.out.print("Press the button (Up or Down): ");
            String button = scanner.next();
            button.toLowerCase();
            Elevator.button = button;

            Elevator.elevatorMover(Elevator.currentFloor, Elevator.elevatorFloor1, Elevator.elevatorFloor2);

            System.out.print("Which floor are you heading to: ");
            int targetFloor = scanner.nextInt();
            scanner.nextLine();
            switch (targetFloor) {
                case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10:
                    System.out.println(getFloorString(targetFloor) + "is a selected.");
                case -1, -2, -3, -4:
                    System.out.println(getFloorString(targetFloor) + "is a selected.");
                case -5:
                    System.out.println(getFloorString(targetFloor) + "is a selected.");
                    System.out.println(getFloorString(targetFloor) + "is a maintenance room.");
                    System.out.print("Press the Access Code: ");
                    String AccessCode = scanner.nextLine().toLowerCase();
                    if (AccessCode == "hxb294") {
                        if (!elevator1IsMoving() && !elevator2IsMoving()) {
                            System.out.println("Elevator 1 is moving to the " + getFloorString(-5) + " floor.");
                            Elevator.floorLoadingPrinter(Elevator.elevatorFloor1, -5);
                            System.out.println("Elevator 2 is moving to the " + getFloorString(-5) + " floor.");
                            Elevator.floorLoadingPrinter(Elevator.elevatorFloor2, -5);
                            System.out.println("CAUTION: MAINTENANCE IN PROGRESS");
                            Maintenance = true;
                            break;
                        } else {
                            System.out.println("Wait until there is no users.");
                            if (!elevator1IsMoving()) {
                                Elevator.floorLoadingPrinter(Elevator.elevatorFloor2, Elevator.elevatorNode2);
                            }
                            if (!elevator2IsMoving()) {
                                Elevator.floorLoadingPrinter(Elevator.elevatorFloor1, Elevator.elevatorNode1);
                            }

                            System.out.println("Elevator 1 is moving to the " + getFloorString(-5) + " floor.");
                            Elevator.floorLoadingPrinter(Elevator.elevatorFloor1, -5);
                            System.out.println("Elevator 2 is moving to the " + getFloorString(-5) + " floor.");
                            Elevator.floorLoadingPrinter(Elevator.elevatorFloor2, -5);
                            System.out.println("CAUTION: MAINTENANCE IN PROGRESS");
                            Maintenance = true;
                            break;
                        }
                    }
                    else {
                        System.out.println("ERROR: Access Denied");
                        break;
                    }
                default:
                    System.out.println("ERROR");
                    break;
            }
        }
    }

    static int currentFloor;
    static int elevatorFloor1;
    static int elevatorFloor2;
    static int elevatorCounts1;
    static int elevatorCounts2;
    static int elevatorNode1;
    static int elevatorNode2;
    static String button;
    static int maxWeight = 100;
    static int weight;
    public static void setInitialSetting(int currentFloor, int elevatorFloor1, int elevatorFloor2) {
        Elevator.currentFloor = currentFloor;
        Elevator.elevatorFloor1 = elevatorFloor1;
        Elevator.elevatorFloor2 = elevatorFloor2;
    }
    public static String getFloorString(int floor) {
        if (floor <= 0) { // Basement: floor int 0 to -4 will be changed to B1 to B5
            floor = floor - 1;
            return "B" + floor;
        } else if (floor == 1) {
            return "1st";
        } else if (floor == 2) {
            return "2nd";
        } else if (floor == 3) {
            return "3rd";
        } else { // any other floor except 1st, 2nd, and 3rd
            return floor + "th";
        }
    }

    public static void setFloor(int floor) {
        Elevator.currentFloor = floor;
    }
    public static void setElevatorFloor1(int floor) {
        Elevator.elevatorFloor1 = floor;
        elevatorCounts1 = elevatorCounts1 + 1;
    }
    public static void setElevatorFloor2(int floor) {
        Elevator.elevatorFloor2 = floor;
        elevatorCounts2 = elevatorCounts2 + 1;
    }
    public static int getFloor(int floor) {
        return floor;
    }


    public static String floorLoadingPrinter(int myFloor, int targetFloor) {
        // try 'for loops'
        if(myFloor > 0 && targetFloor > 0) { // In case 0 does not exist in the middle of the loop
            while(myFloor < (targetFloor - 1)) {
                myFloor = myFloor + 1;
                System.out.println(myFloor);
            }
            while(myFloor > (targetFloor + 1)) {
                myFloor = myFloor - 1;
                System.out.println(myFloor);
            }
        }
        if(myFloor < 0 && targetFloor > 0) { // In case 0 exist in the middle of the loop
            while(myFloor < (targetFloor - 1)) {
                myFloor = myFloor + 1;
                if(myFloor != 0) {
                    System.out.println(myFloor);
                }
            }
        }
        if(myFloor > 0 && targetFloor < 0) {
            while(myFloor > (targetFloor + 1)) {
                myFloor = myFloor - 1;
                if(myFloor != 0) {
                    System.out.println(myFloor);
                }
            }
        }
        if(myFloor < 0 && targetFloor < 0) {
            while(myFloor < (targetFloor - 1)) {
                myFloor = myFloor + 1;
                System.out.println(myFloor);
            }
            while(myFloor > (targetFloor + 1)) {
                myFloor = myFloor - 1;
                System.out.println(myFloor);
            }
        }
        Elevator.setFloor(targetFloor);
        return null;
    }

    public static boolean elevator1IsMoving() {
        if(elevatorNode1 != Elevator.elevatorFloor1) {
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean elevator2IsMoving() {
        if(elevatorNode2 != Elevator.elevatorFloor2) {
            return true;
        }
        else {
            return false;
        }
    }

    // When Up or Down button is pressed
    // elevatorPosition = Where is the elevator right now!
    public static void elevatorMover(int myFloor, int elevatorPosition1, int elevatorPosition2) {
        // the input for basement floor's will be +1 to consider them as if they start from the 0th floor
        if(elevatorPosition1 < 0) {
            elevatorPosition1 = elevatorPosition1 + 1;
        }
        if(elevatorPosition2 < 0) {
            elevatorPosition2 = elevatorPosition2 + 1;
        }

        int totalDistance1 = Math.abs(myFloor) + Math.abs(elevatorPosition1);
        int totalDistance2 = Math.abs(myFloor) + Math.abs(elevatorPosition2);

        if(elevator1IsMoving() && elevator2IsMoving()) {
            if(button.equals("up")) {
                // case1
                if(elevatorPosition1 < myFloor && myFloor < Elevator.elevatorNode1 && elevatorPosition2 < myFloor && myFloor < Elevator.elevatorNode2 ) {
                    if(Math.abs(elevatorPosition1 - myFloor) < Math.abs(elevatorPosition2 - myFloor)) {
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, elevatorPosition1));
                        setElevatorFloor1(myFloor);
                        System.out.println("Elevator 1 is on the " + getFloorString(Elevator.elevatorFloor1) + " floor.");
                    }
                    if(Math.abs(elevatorPosition1 - myFloor) > Math.abs(elevatorPosition2 - myFloor)) {
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, elevatorPosition2));
                        setElevatorFloor2(myFloor);
                        System.out.println("Elevator 2 is on the " + getFloorString(Elevator.elevatorFloor2) + " floor.");
                    }
                }
                // case2
                if(myFloor < elevatorPosition1 && myFloor < Elevator.elevatorNode1 && myFloor < elevatorPosition2 && myFloor < Elevator.elevatorNode2 ) {
                    if(totalDistance1 < totalDistance2) {
                        System.out.println(Elevator.floorLoadingPrinter(Elevator.elevatorNode1, elevatorPosition1));
                        setElevatorFloor1(Elevator.elevatorNode1);
                        System.out.println("Elevator 1 is on the " + getFloorString(Elevator.elevatorNode1) + " floor.");
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, Elevator.elevatorNode1));
                        setElevatorFloor1(myFloor);
                        System.out.println("Elevator 1 is on the " + getFloorString(myFloor) + " floor.");
                    }
                    if(totalDistance1 > totalDistance2) {
                        System.out.println(Elevator.floorLoadingPrinter(Elevator.elevatorNode2, elevatorPosition2));
                        setElevatorFloor1(Elevator.elevatorNode2);
                        System.out.println("Elevator 2 is on the " + getFloorString(Elevator.elevatorNode2) + " floor.");
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, Elevator.elevatorNode2));
                        setElevatorFloor1(myFloor);
                        System.out.println("Elevator 2 is on the " + getFloorString(myFloor) + " floor.");
                    }
                }
                // case3
                if(myFloor < Elevator.elevatorNode1 && myFloor < Elevator.elevatorNode2 ) {
                    if(elevatorPosition1 < myFloor && elevatorPosition2 > myFloor) {
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, elevatorPosition1));
                        setElevatorFloor1(myFloor);
                        System.out.println("Elevator 1 is on the " + getFloorString(Elevator.elevatorFloor1) + " floor.");
                    }
                    if(elevatorPosition1 > myFloor && elevatorPosition2 < myFloor) {
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, elevatorPosition2));
                        setElevatorFloor2(myFloor);
                        System.out.println("Elevator 2 is on the " + getFloorString(Elevator.elevatorFloor2) + " floor.");
                    }
                }
            }
            else { // both elevators are on the same floor
                // When both elevators are going up, choose the one with lower node
                if (Elevator.elevatorNode1 > Elevator.elevatorNode2) {
                    System.out.println(Elevator.floorLoadingPrinter(Elevator.elevatorNode2, elevatorPosition2));
                    setElevatorFloor1(Elevator.elevatorNode2);
                    System.out.println("Elevator 2 is on the " + getFloorString(Elevator.elevatorNode2) + " floor.");
                    System.out.println(Elevator.floorLoadingPrinter(myFloor, Elevator.elevatorNode2));
                    setElevatorFloor1(myFloor);
                    System.out.println("Elevator 2 is on the " + getFloorString(myFloor) + " floor.");
                } else if (Elevator.elevatorNode1 < Elevator.elevatorNode2) {
                    System.out.println(Elevator.floorLoadingPrinter(Elevator.elevatorNode1, elevatorPosition1));
                    setElevatorFloor1(Elevator.elevatorNode1);
                    System.out.println("Elevator 1 is on the " + getFloorString(Elevator.elevatorNode1) + " floor.");
                    System.out.println(Elevator.floorLoadingPrinter(myFloor, Elevator.elevatorNode1));
                    setElevatorFloor1(myFloor);
                    System.out.println("Elevator 1 is on the " + getFloorString(myFloor) + " floor.");
                } else {
                    if (elevatorCounts1 <= elevatorCounts2) {
                        System.out.println(Elevator.floorLoadingPrinter(Elevator.elevatorNode1, elevatorPosition1));
                        setElevatorFloor1(Elevator.elevatorNode1);
                        System.out.println("Elevator 1 is on the " + getFloorString(Elevator.elevatorNode1) + " floor.");
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, Elevator.elevatorNode1));
                        setElevatorFloor1(myFloor);
                        System.out.println("Elevator 1 is on the " + getFloorString(myFloor) + " floor.");
                    } else {
                        System.out.println(Elevator.floorLoadingPrinter(Elevator.elevatorNode2, elevatorPosition2));
                        setElevatorFloor1(Elevator.elevatorNode2);
                        System.out.println("Elevator 2 is on the " + getFloorString(Elevator.elevatorNode2) + " floor.");
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, Elevator.elevatorNode2));
                        setElevatorFloor1(myFloor);
                        System.out.println("Elevator 2 is on the " + getFloorString(myFloor) + " floor.");
                    }
                }
            }
            if(button.equals("down")) {
                // case1
                if(elevatorPosition1 > myFloor && myFloor > Elevator.elevatorNode1 && elevatorPosition2 > myFloor && myFloor > Elevator.elevatorNode2 ) {
                    if(Math.abs(elevatorPosition1 - myFloor) < Math.abs(elevatorPosition2 - myFloor)) {
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, elevatorPosition1));
                        setElevatorFloor1(myFloor);
                        System.out.println("Elevator 1 is on the " + getFloorString(Elevator.elevatorFloor1) + " floor.");
                    }
                    if(Math.abs(elevatorPosition1 - myFloor) > Math.abs(elevatorPosition2 - myFloor)) {
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, elevatorPosition2));
                        setElevatorFloor2(myFloor);
                        System.out.println("Elevator 2 is on the " + getFloorString(Elevator.elevatorFloor2) + " floor.");
                    }
                }
                // case2
                if(myFloor > elevatorPosition1 && myFloor > Elevator.elevatorNode1 && myFloor > elevatorPosition2 && myFloor > Elevator.elevatorNode2 ) {
                    if(totalDistance1 < totalDistance2) {
                        System.out.println(Elevator.floorLoadingPrinter(Elevator.elevatorNode1, elevatorPosition1));
                        setElevatorFloor1(Elevator.elevatorNode1);
                        System.out.println("Elevator 1 is on the " + getFloorString(Elevator.elevatorNode1) + " floor.");
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, Elevator.elevatorNode1));
                        setElevatorFloor1(myFloor);
                        System.out.println("Elevator 1 is on the " + getFloorString(myFloor) + " floor.");
                    }
                    if(totalDistance1 > totalDistance2) {
                        System.out.println(Elevator.floorLoadingPrinter(Elevator.elevatorNode2, elevatorPosition2));
                        setElevatorFloor1(Elevator.elevatorNode2);
                        System.out.println("Elevator 2 is on the " + getFloorString(Elevator.elevatorNode2) + " floor.");
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, Elevator.elevatorNode2));
                        setElevatorFloor1(myFloor);
                        System.out.println("Elevator 2 is on the " + getFloorString(myFloor) + " floor.");
                    }
                }
                // case3
                if(myFloor > Elevator.elevatorNode1 && myFloor > Elevator.elevatorNode2 ) {
                    if(elevatorPosition1 > myFloor && elevatorPosition2 < myFloor) {
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, elevatorPosition1));
                        setElevatorFloor1(myFloor);
                        System.out.println("Elevator 1 is on the " + getFloorString(Elevator.elevatorFloor1) + " floor.");
                    }
                    if(elevatorPosition1 < myFloor && elevatorPosition2 > myFloor) {
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, elevatorPosition2));
                        setElevatorFloor2(myFloor);
                        System.out.println("Elevator 2 is on the " + getFloorString(Elevator.elevatorFloor2) + " floor.");
                    }
                }
            }
            else { // both elevators are on the same floor
                // When both elevators are going down, choose the one with a higher node
                if (Elevator.elevatorNode1 < Elevator.elevatorNode2) {
                    System.out.println(Elevator.floorLoadingPrinter(Elevator.elevatorNode2, elevatorPosition2));
                    setElevatorFloor1(Elevator.elevatorNode2);
                    System.out.println("Elevator 2 is on the " + getFloorString(Elevator.elevatorNode2) + " floor.");
                    System.out.println(Elevator.floorLoadingPrinter(myFloor, Elevator.elevatorNode2));
                    setElevatorFloor1(myFloor);
                    System.out.println("Elevator 2 is on the " + getFloorString(myFloor) + " floor.");
                } else if (Elevator.elevatorNode1 > Elevator.elevatorNode2) {
                    System.out.println(Elevator.floorLoadingPrinter(Elevator.elevatorNode1, elevatorPosition1));
                    setElevatorFloor1(Elevator.elevatorNode1);
                    System.out.println("Elevator 1 is on the " + getFloorString(Elevator.elevatorNode1) + " floor.");
                    System.out.println(Elevator.floorLoadingPrinter(myFloor, Elevator.elevatorNode1));
                    setElevatorFloor1(myFloor);
                    System.out.println("Elevator 1 is on the " + getFloorString(myFloor) + " floor.");
                } else { // even if their nodes are the same
                    if (elevatorCounts1 <= elevatorCounts2) {
                        System.out.println(Elevator.floorLoadingPrinter(Elevator.elevatorNode1, elevatorPosition1));
                        setElevatorFloor1(Elevator.elevatorNode1);
                        System.out.println("Elevator 1 is on the " + getFloorString(Elevator.elevatorNode1) + " floor.");
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, Elevator.elevatorNode1));
                        setElevatorFloor1(myFloor);
                        System.out.println("Elevator 1 is on the " + getFloorString(myFloor) + " floor.");
                    } else {
                        System.out.println(Elevator.floorLoadingPrinter(Elevator.elevatorNode2, elevatorPosition2));
                        setElevatorFloor1(Elevator.elevatorNode2);
                        System.out.println("Elevator 2 is on the " + getFloorString(Elevator.elevatorNode2) + " floor.");
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, Elevator.elevatorNode2));
                        setElevatorFloor1(myFloor);
                        System.out.println("Elevator 2 is on the " + getFloorString(myFloor) + " floor.");
                    }
                }

            }
        }
        else if(elevator1IsMoving()) {
            if(button.equals("up") && elevatorPosition1 < Elevator.elevatorNode1) {
                if(elevatorPosition1 < myFloor && myFloor < Elevator.elevatorNode1) {
                    if(Math.abs(elevatorPosition2 - myFloor) > Math.abs(elevatorPosition1 - myFloor)) {
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, elevatorPosition1));
                        setElevatorFloor1(myFloor);
                        System.out.println("Elevator 1 is on the " + getFloorString(Elevator.elevatorFloor1) + " floor.");
                    }
                    else { // Math.abs(elevatorPosition2 - myFloor) < Math.abs(elevatorPosition1 - myFloor)
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, elevatorPosition2));
                        setElevatorFloor2(myFloor);
                        System.out.println("Elevator 2 is on the " + getFloorString(Elevator.elevatorFloor2) + " floor.");
                    }
                }
                else { // unless myFloor is between the path of an elevator = use the other one
                    System.out.println(Elevator.floorLoadingPrinter(myFloor, elevatorPosition2));
                    setElevatorFloor2(myFloor);
                    System.out.println("Elevator 2 is on the " + getFloorString(Elevator.elevatorFloor2) + " floor.");
                }
            }
            if(button.equals("down") && elevatorPosition1 > Elevator.elevatorNode1) {
                if(elevatorPosition1 > myFloor && myFloor > Elevator.elevatorNode1) {
                    if(Math.abs(elevatorPosition2 - myFloor) > Math.abs(elevatorPosition1 - myFloor)) {
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, elevatorPosition1));
                        setElevatorFloor1(myFloor);
                        System.out.println("Elevator 1 is on the " + getFloorString(Elevator.elevatorFloor1) + " floor.");
                    }
                    else { // Math.abs(elevatorPosition2 - myFloor) < Math.abs(elevatorPosition1 - myFloor)
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, elevatorPosition2));
                        setElevatorFloor2(myFloor);
                        System.out.println("Elevator 2 is on the " + getFloorString(Elevator.elevatorFloor2) + " floor.");
                    }
                }
                else {
                    System.out.println(Elevator.floorLoadingPrinter(myFloor, elevatorPosition2));
                    setElevatorFloor2(myFloor);
                    System.out.println("Elevator 2 is on the " + getFloorString(Elevator.elevatorFloor2) + " floor.");
                }
            }
        }
        else if(elevator2IsMoving()) {
            if(button.equals("up") && elevatorPosition2 < Elevator.elevatorNode2) {
                if(elevatorPosition2 < myFloor && myFloor < Elevator.elevatorNode2) {
                    if(Math.abs(elevatorPosition1 - myFloor) > Math.abs(elevatorPosition2 - myFloor)) {
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, elevatorPosition2));
                        setElevatorFloor2(myFloor);
                        System.out.println("Elevator 2 is on the " + getFloorString(Elevator.elevatorFloor2) + " floor.");
                    }
                    else { //Math.abs(elevatorPosition1 - myFloor) < Math.abs(elevatorPosition2 - myFloor)
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, elevatorPosition1));
                        setElevatorFloor1(myFloor);
                        System.out.println("Elevator 1 is on the " + getFloorString(Elevator.elevatorFloor1) + " floor.");
                    }
                }
                else { // unless myFloor is between the path of an elevator = use the other one
                    System.out.println(Elevator.floorLoadingPrinter(myFloor, elevatorPosition1));
                    setElevatorFloor1(myFloor);
                    System.out.println("Elevator 1 is on the " + getFloorString(Elevator.elevatorFloor1) + " floor.");
                }
            }
            if(button.equals("down") && elevatorPosition2 > Elevator.elevatorNode2) {
                if(elevatorPosition2 > myFloor && myFloor > Elevator.elevatorNode2) {
                    if(Math.abs(elevatorPosition1 - myFloor) > Math.abs(elevatorPosition2 - myFloor)) {
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, elevatorPosition2));
                        setElevatorFloor2(myFloor);
                        System.out.println("Elevator 2 is on the " + getFloorString(Elevator.elevatorFloor2) + " floor.");
                    }
                    else { // Math.abs(elevatorPosition1 - myFloor) < Math.abs(elevatorPosition2 - myFloor)
                        System.out.println(Elevator.floorLoadingPrinter(myFloor, elevatorPosition1));
                        setElevatorFloor1(myFloor);
                        System.out.println("Elevator 1 is on the " + getFloorString(Elevator.elevatorFloor1) + " floor.");
                    }
                }
                else {
                    System.out.println(Elevator.floorLoadingPrinter(myFloor, elevatorPosition1));
                    setElevatorFloor1(myFloor);
                    System.out.println("Elevator 1 is on the " + getFloorString(Elevator.elevatorFloor1) + " floor.");
                }
            }
        }
        else { // if both elevators are not moving
            if(Math.abs(elevatorPosition1 - myFloor) < Math.abs(elevatorPosition2 - myFloor)) {
                Elevator.floorLoadingPrinter(myFloor, elevatorPosition1);
                setElevatorFloor1(myFloor);
                System.out.println("Elevator 1 is on the " + getFloorString(Elevator.elevatorFloor1) + " floor.");
            }
            else if(Math.abs(elevatorPosition1 - myFloor) > Math.abs(elevatorPosition2 - myFloor)) {
                Elevator.floorLoadingPrinter(myFloor, elevatorPosition2);
                setElevatorFloor2(myFloor);
                System.out.println("Elevator 1 is on the " + getFloorString(Elevator.elevatorFloor2) + " floor.");
            }
            else { // If both elevators are on the same floor or in the same distance from the user's floor, less used one since maintenance will work
                if(elevatorCounts1 <= elevatorCounts2) {
                    Elevator.floorLoadingPrinter(myFloor, elevatorPosition2);
                    setElevatorFloor1(myFloor);
                    System.out.println("Elevator 1 is on the " + getFloorString(Elevator.elevatorFloor1) + " floor.");
                }
                else {
                    System.out.println(Elevator.floorLoadingPrinter(myFloor, elevatorPosition1));
                    setElevatorFloor2(myFloor);
                    System.out.println("Elevator 2 is on the " + getFloorString(Elevator.elevatorFloor2) + " floor.");
                }
            }
        }
    }
}
