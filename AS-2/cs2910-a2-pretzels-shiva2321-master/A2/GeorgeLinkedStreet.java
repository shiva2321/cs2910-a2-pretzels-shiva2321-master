import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * A Doubly-Linked Node implementation of the StreetParking Interface
 *
 * Implement the appropriate methods from StreetParking to get this to compile (and then delete this comment)
 */
public class GeorgeLinkedStreet implements StreetParking {

    private int streetLength;
    private int freeSpace;
    private int blockLength;
    private boolean block;
    private int cars;
    private Node georgeCar;
    private Iterator<Integer> it = iterator();


    /**
     * @param lengthOfStreet length of the street given by user
     * @param lengthOfGeorgesCar length of the car given by user
     */
    public GeorgeLinkedStreet(int lengthOfStreet, int lengthOfGeorgesCar) {
        streetLength = lengthOfStreet;
        freeSpace = streetLength;
        it.set(lengthOfGeorgesCar);

    }


    /**
     * This method will add cars before Georges car on to the street.
     *
     * @param length : - length of the car.
     */
    @Override
    public int push (int length) {
        if (streetLength <= 0 || freeSpace <= 0)    //if Street is full.
        {

            System.out.println("No more Space to park your car. please come later.");
            return -1;
        }
        // free space value update
        freeSpace = freeSpace - length;
        it.add(length);
        Object e = it.next();
        return (int) e;
    }

    /**
     * this method will add car at the end of the street
     *
     * @param size :- length of the car
     */
    @Override
    public boolean block(int size) {

        if (streetLength <= 0 || freeSpace <= 0 || isBlocked()) {
            System.out.println("No space to park your car");
        }
        freeSpace = freeSpace - size;       // free space value update
        Node localCar = new Node(size);        // new car as Blocked car
        Node last = georgeCar;
        localCar.next = null;

        while (last.next != null) {
            last = last.next;
            last.next = localCar;
            localCar.previous = last;
        }
        block = true;
        cars++;
        return isBlocked();
    }

    /**
     * this method will remove the car of the given ticket number.
     *
     * @param ticketNumber :- unique number given to the car.
     */
    @Override
    public void remove(int ticketNumber) {
        Stack<Object> stack = new Stack<>();

        while (it.hasNext()) {
            boolean remove = false;
            if ((int) it.next() == ticketNumber) {
                remove = true;
                cars--;
            }
            if(!remove)
            {
                stack.push(it.next());
            }
        }

        while(it.hasNext())
        {
            it.add(stack.pop());
        }
    }


    /**
     * @return number of cars present on the street.
     */
    @Override
    public int numberOfCars() {
        return cars;
    }

    /**
     * @return string containing cars in order
     */
    @Override
    public String toString() {
        String s = "";
        while (it.hasNext()) {
            Object st = it.next();
            int temp = (int) st;

            if (temp == freeSpace) {
                s = s + "E" + temp;
            }
            if (temp == blockLength) {
                s = s + "B" + temp;
            } else {
                s = s + "G" + temp;
            }
        }
        return s;
    }

    @Override
    public Iterator<Integer> iterator() {

        return new Iterator<>();
    }


    /**
     * will return the free space left on the street.
     */
    @Override
    public int freeSpace() {
        return freeSpace;
    }


    /**
     * check is the street block or not.
     */
    @Override
    public boolean isBlocked() {
        return block;
    }


    /**
     * Node class that will alocate data space to given data.
     */
    class Node {
        Object data;
        Node previous;
        Node next;

        Node(Object d) {
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
        private Node first;

        /**
         * Default Constructor
         */
        private Iterator() {
            position = null;
            previous = null;
            isAfterNext = false;
            //first =new Node(0);
        }

        /**
         * this method will move iterator to the next object
         *
         * @return position of the iterator.
         */
        public boolean hasNext() {
            if (position == null) {
                return first != null;
            } else {
                return position.next != null;
            }
        }


        /**
         * this method will return the value at iterator position.
         *
         * @return object stands at the position.
         */
        public Object next(){
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

        @Override
        public void remove(){
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public Object previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void set(Object o) {
            georgeCar = new Node(o);
            first = georgeCar;
            cars = 1;
        }

        @Override
        public void add(Object o) {

            Node newcar = new Node(o);    // new car
            newcar.next = georgeCar;
            newcar.previous = null;

            georgeCar.previous = newcar;
            georgeCar = newcar;
            cars++;
        }
    }
}


