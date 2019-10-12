import java.util.Iterator;
/**
 * A Doubly-Linked Node implementation of the StreetParking Interface
 *
 * Implement the appropriate methods from StreetParking to get this to compile (and then delete this comment)
 */
public class GeorgeLinkedStreet implements StreetParking {

    private int streetLength;
    private int freeSpace;
    private int carLength;
    private int blockLength;
    private boolean block;
    private int cars;
    private Node georgeCar = new Node(10);
    private Iterator<Integer> it = GeorgeLinkedStreet.Iteraror();


    /**
     * @param lengthOfStreet
     * @param lengthOfGeorgesCar
     */
    public GeorgeLinkedStreet(int lengthOfStreet, int lengthOfGeorgesCar) {
        streetLength = lengthOfStreet;
        freeSpace = streetLength;
        cars = 1;
    }


    /**
     * This method will add cars before Georges car on to the street.
     *
     * @param length : - length of the car.
     */
    @Override
    int push(int length) {
        if (streetLength <= 0 || freeSpace <= 0)    //if Street is full.
        {
            System.out.println("No more Space to park your car. please come later.");
        }
        carLength = length;
        freeSpace = freeSpace - carLength; // free space value update
        Node newcar = new Node(length);    // new car
        newcar.next = georgeCar;
        newcar.previous = null;

        georgeCar.previous = newcar;
        georgeCar = newcar;
        cars++;
    }

    /**
     * this method will add car at the end of the street
     *
     * @param blockedCarLength :- length of the car
     */
    @Override
    boolean Block(int blockedCarLength) {

        if (streetLength <= 0 || freeSpace <= 0 || isBlocked()) {
            System.out.println("No space to park your car");
        }
        freeSpace = freeSpace - blockedcarLength;       // free space value update
        blockLength = blockedCarLength;
        Node localCar = new Node(blockedCarLength);        // new car as Blocked car
        Node last = georgeCar;
        localCar.next = null;

        while (last.next != null) {
            last = last.next;
            last.next = localCar;
            localCar.previous = last;
        }
        block = true;
        cars++;
    }

    /**
     * this method will remove the car of the given ticket number.
     *
     * @param ticketNumber :- unique number given to the car.
     */
    void remove(int ticketNumber) {

        while (it.hasNext()) {
            if (it.next() == ticketNumber) {
                it.remove();
                cars++;
            }
        }
    }

    /**
     * @return number of cars present on the street.
     */
    int numberOfCars() {
        return cars;
    }

    /**
     * @return
     */
    String toString() {
        while (it.hasNext()) {
            int temp = it.next();
            if (temp == freeSpace) {
                System.out.print("E" + temp);
            }
            if (temp == blockLength) {
                System.out.print("B" + temp);
            } else {
                System.out.print("G" + temp);
            }
        }

    }

    /**
     * will return the free space left on the street.
     */
    int freeSpace() {
        return freeSpace;
    }


    /**
     * check is the street block or not.
     */
    boolean isBlocked() {
        return block;
    }


    /**
     * Node class that will alocate data space to given data.
     */
    class Node {
        int data;
        Node previous;
        Node next;

        Node(int d) {
            data = d;
        }
    }


    /**
     * this interface implements ListIterator.
     */
    class Iterator<Integer> implements ListIterator
    {
        private Node position;
        private Node previous;
        private boolean isAfterNext;

        /**
         * Default Constructor
         */
        public Iterator() {
            position = null;
            previous = null;
            isAfterNext = false;
        }

        /**
         * this method will move iterator to the next object
         *
         * @return position of the iterator.
         */
        boolean hasNext() {
            if (position == null) {
                return first != null;
            } else {
                return position.next != null;
            }
        }


    /**
     * this method will return the value at iterator position.
     *
     * @return
     */
    public int next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        previous = position;
        isAfterNext = true;
        if (position == null) {
            position = first;
        } else {
            position = position.next;
        }
        return position.data;
    }

    /**
     * this method will remove an object at the iterator position.
     */
    public void remove() {
        if (!isAfterNext) {
            throw new IllegalStateException();
        }
        if (position == first) {
            removeFirst();
        } else {
            previous.next = position.next;
        }
        position = previous;
        isAfterNext = false;
    }
    }
}


