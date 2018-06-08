// DO NOT MODIFY THIS FILE!
// IF YOU DO, THEN YOUR ASSIGNMENT WILL NOT BE GRADED.

/**
 * A box that contains an element. 
 *
 * @param <T> the type of element in this box
 * @author Michael E. Cotterell
 */
public class Box<T> {
    
    /**
     * Creates an array of box objects of the specified type.
     *
     * <p>
     * Here is an example that creates a <code>bBox</code> array of length 10:
     * {@code
     * Box<String>[] boxes = Box.<String>array(10);
     * }
     * 
     * @param <U> the type of elements in each box
     * @param length the length of the array to create
     * @return an array of box objects
     */
    @SuppressWarnings("unchecked")
    public static <U> Box<U>[] array(int length) {
	Box<U>[] boxes = (Box<U>[]) new Box[length];
	return boxes;
    } // array

    private T elem = null;

    /**
     * Creates a new box.
     */
    public Box() {}

    /** 
     * Creates a new box with the given element. 
     * @param elem element in the box
     */
    public Box(T elem) {
	this.elem = elem;
    } // Box

    /**
     * Return the element in the box.
     * @return the element in the box
     */
    public T get() {
	return elem;
    } // get

    /**
     * Sets the element in the box.
     * @param elem the element in the box
     */
    public void set(T elem) {
	this.elem = elem;
    } // set

} // Box<T>
