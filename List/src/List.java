import java.util.Iterator;

/**
 * An ordered collection (also known as a sequence). The user of this interface 
 * has precise control over where in the list each element is inserted. The 
 * user can access elements by their integer index (position in the list), and 
 * search for elements in the list.
 *
 * <p>
 * Student implementations of this interface are not allowed to adapt existing
 * implementations of <code>java.util.List</code>. If they do so, then their
 * assignment will not be graded. Students should provide their own 
 * implementations.
 *
 * <p>
 * This interface is heavily based on <code>java.util.List</code>. The 
 * documentation here is adapted from the original documentation under academic 
 * fair use.
 *
 * @param <T> the type of elements in this list
 * @author Michael E. Cotterell
 */
public interface List<T> {

    /**
     * Adds the specified element to the list at an implementation-specific
     * position.
     * @param elem element to be inserted
     * @throws NullPointerException if elem is null
     */
    void add(T elem) throws NullPointerException;

    /**
     * Inserts the specified element at the specified position in this list. 
     * Shifts the element currently at that position (if any) and any subsequent
     * elements to the right (adds one to their indices).
     * @param index index at which the specified element is to be inserted
     * @param elem  element to be inserted
     * @throws NullPointerException if elem is null
     * @throws IndexOutOfBoundsException if the index is out of range 
     *         {@code (index < 0 || index > size())}
     */
    void add(int index, T elem) throws NullPointerException, IndexOutOfBoundsException;

    /** 
     * Removes all of the elements from this list. The list will be empty after 
     * this call returns.
     */
    void clear();

    /** 
     * Compares the specified list with this list for equality. Returns 
     * <code>true</code> if and only if the specified object is also a 
     * <code>List</code>, both lists have the same size, and all corresponding 
     * pairs of elements in the two lists are equal.
     * If <code>list</code> is a list of the same list type, then students
     * may safely cast the object to the appropriat list type when implementing
     * this method.
     * @param list the object to be compared for equality with this list
     * @return <code>true</code> if the specified object is equal to this list
     */
    boolean equals(Object list);

    /**
     * Returns the element at the specified position in this list.
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         {@code (index < 0 || index >= size())}
     */
    T get(int index) throws IndexOutOfBoundsException;

    /**
     * Replaces the element at the specified position in this list with the 
     * specified element
     * @param index index of the element to replace
     * @param elem element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws NullPointerException if the specified element is null
     * @throws IndexOutOfBoundsException if the index is out of range
     *         {@code (index < 0 || index >= size())}
     */
    T set(int index, T elem) throws NullPointerException, IndexOutOfBoundsException;

    /**
     * Returns the number of elements in this list. If this list contains more 
     * than <code>Integer.MAX_VALUE</code> elements, return 
     * <code>Integer.MAX_VALUE</code>.
     * @return the number of elements in this list
     */
    int size();

    /**
     * Returns true if and only if this list contains no elements.
     * @return <code>true</code> if this list contains no elements
     */ 
    boolean isEmpty();

    /**
     * Returns <code>true</code> if this list contains the specified element. 
     * More formally, returns <code>true</code> if and only if this list 
     * contains at least one element <code>o</code> such that 
     * <code>o.equals(elem)</code>.
     * @param elem element whose presence in this list is to be tested
     * @return <code>true</code> if this list contains the specified element
     * @throws NullPointerException if elem is null
     */
    boolean contains(T elem) throws NullPointerException;

    /**
     * Returns an iterator over the elements in this list in proper sequence
     * Students will need to properly create their own <code>Iterator</code> 
     * class for their list implementation and return an appropriate instance 
     * of it here.
     *
     * <p>
     * This interface method may seem strange since it's not abstract. This is
     * an example of a Java 8 (or higher) default method. See the link below
     * for more information on default methods.
     *
     * @return an iterator over the elements in this list in proper sequence
     * @throws UnsupportedOperationException if the iterator operation is not 
     *         supported by this list
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html">java.util.Iterator</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html">The Java™ Tutorials: Default Methods</a>
     */
    default Iterator<T> iterator() throws UnsupportedOperationException {
	throw new UnsupportedOperationException("iterator() not supported");
    } // iterator()

    /** 
     * Removes the first occurrence of the specified element from this list, if 
     * it is present. If this list does not contain the element, it is 
     * unchanged. More formally, removes the element with the lowest index 
     * <code>i</code> such that <code>elem.equals(get(i))</code> (if such an 
     * element exists). Returns <code>true</code> if this list contained the 
     * specified element (or equivalently, if this list changed as a result of 
     * the call).
     * @param elem element to be removed from this list, if present
     * @return <code>true</code> if this list contained the specified element
     * @throws NullPointerException if elem is null
     */
    boolean remove(T elem) throws NullPointerException;

    /**
     * Returns the index of the first occurrence of the specified element in 
     * this list, or -1 if this list does not contain the element.
     * @param elem element to search for
     * @return the index of the first occurrence of the specified element in 
     *         this list, or -1 if this list does not contain the element
     * @throws NullPointerException if elem is null
     */
    int indexOf(T elem) throws NullPointerException; 

} // List<T>