package types;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Arrays;
import java.util.Collections;

/**
 * Represents a bottle that can hold content of objects that implement the interface Filling.
 */
public class Bottle implements Iterable<Filling> {

	public static int DEFAULT_SIZE = 5;
	public static String empty = "⬜";
	public static String EOL = System.lineSeparator();
	
    private Filling[] content;
	private int size;

    /**
     * Default constructor.
     * Initializes a Bottle instance with the default size.
     */
    public Bottle() {
        this(DEFAULT_SIZE);
    }

	/**
	 * Initializes a Bottle instance with a specified size.
	 *
	 * @param size - The size of the bottle.
	 * @requires size > 0.
	 */
	public Bottle(int size) {
		this.size = size;
		this.content = new Filling[size]; 
	}

	/**
	 * Initializes a Bottle instance filled with the provided content.
	 *
	 * @param content - An array of enum objects that implement the Filling interface, 
	 * representing the content to fill this bottle instance with.
	 */
	public Bottle(Filling[] content) {
		this(content.length);
		// Reverse inserted content to match tests
        List<Filling> contentList = new ArrayList<Filling>(Arrays.asList(content));
        Collections.reverse(contentList);
        this.content = contentList.toArray(new Filling[content.length]);
	}
	
	// Getters and Setters
	
	/**
     * @return the content in the bottle.
     */
    public Filling[] getContent() {
        return content;
    }

    /**
     * Sets the bottle content array initialized in this instance to a new array.
	 *
     * @param content - The new bottle content array to be set
     */
    public void setContent(Filling[] content) {
        this.content = content;
    }

    /**
     * @return The size of the bottle initialized in this instance.
     * 
     */
    public int size() {
        return size;
    }

	/**
	 * Sets the size of the bottle initialized in this instance to a new value.
	 *
	 * @param size - The size of the bottle to be set.
     * @requires size > 0.
	 */
	public void setSize(int size) {
	    this.size = size;
	}
    
    // Methods
	
    /**
     * Retrieves a specified content at the specified index in this Bottle.
     *
     * @param i The index of the content to retrieve.
     * @requires i > 0 && i <= this.getContent().length.
     * @return The content at the specified index.
     * @throws ArrayIndexOutOfBoundsException if the index is out of range.
     */
	public Filling get(int i) {
		return this.getContent()[i];
	}
	
    /**
     * Sets a specified content at the specified index in this Bottle.
     *
     * @param i The index of the content to be set.
     * @requires i > 0 && i <= this.getContent().length.
     * @throws ArrayIndexOutOfBoundsException if the index is out of range.
     */
	public void set(int i, Filling content) {
		this.getContent()[i] = content;
	}

	/**
	 * Checks if the bottle is full.
	 *
	 * @return true if the bottle is full (no null elements), false otherwise.
	 */
	public boolean isFull() {
		if (this.size() == 1) {
			// If size is 1, then it is a cup, return false
			return false;
		}
		for (Filling filling : this.getContent()) {
	        if (filling == null) {
	        	return false;
	        }
	    }
		return true;
	}

	/**
	 * Checks if the bottle is empty.
	 *
	 * @return true if the bottle is empty (contains only null elements), false otherwise.
	 */
	public boolean isEmpty() {
	    try {
	        this.top();
	        return false; // If top() doesn't throw an exception, the bottle is not empty
	    } catch (IndexOutOfBoundsException e) {
	        return true; // If top() throws an exception, the bottle is empty
	    }
	}

	/**
	 * Retrieves the topmost (first non-null) filling from this Bottle instance.
	 *
	 * @return The topmost filling if available.
	 * @throws IndexOutOfBoundsException if the bottle is empty.
	 */
	public Filling top() {
	    for (Filling filling : this.getContent()) {
	        if (filling != null) {
	            return filling;
	        }
	    }
	    throw new ArrayIndexOutOfBoundsException(
	    		"The bottle is empty. Cannot retrieve top filling.");
	}
	
	/**
	 * Pours in filling to the first null position in a reversed bottleFillings array.
	 *
	 * @param fillingToAdd - The filling to add to the bottle.
	 */
	public boolean receive(Filling fillingToAdd) {
		if (this.size() == 1) {
			this.set(0, fillingToAdd);
			return true;
		}
	    // Iterate in reverse order to find the first null element
	    for (int i = this.size() - 1; i >= 0; i--) {
	        if (this.get(i) == null) {
	            // Check if the element before is null or equal to the filling to add
	            if (i == this.size() - 1 || 
	            (this.get(i + 1).equals(fillingToAdd))) {
	                // Update the array element
	                this.set(i, fillingToAdd);
	                return true;
	            } else {
	                return false;
	            }
	        }
	    }
	    return false;
	}
	
    /**
     * Pours out the topmost filling from the bottle.
     */
    public void pourOut() {
        boolean topFound = false;
        int i = 0;
        Iterator<Filling> it = this.iterator();
        while (it.hasNext() && !topFound) {
            if (it.next() == this.top()) {
                this.set(i, null);
                topFound = true;
            }
            i++;
        }
    }

    /**
    * @return the number of null Fillings inside a bottle.
    */
    public int spaceAvailable() {
      int count = 0;
      for  (Filling bottle : this.getContent()) {
          if (bottle == null) {
              count++;
          }
      }
      return count;
    }

    /**
     * Checks if the non-null contents present in this Bottle instance are equal.
     *
     * @return true if all non-null contents of this Bottle are equal, false otherwise.
     */
    public boolean isSingleFilling() {
        // If the bottle is empty return false
        if (this.isEmpty()) {
            return true;
        }
        // Check if all non-null fillings are equal
        for (Filling filling : this.getContent()) {
            if (filling != null && !filling.equals(this.top())) {
                return false;
            }
        }
        return true;
    }
	
	/**
	* Pours out a specific filling from the bottle.
	* 
	* @param i - the index of the bottle to pour out from.
	* @requires i > 0 && i <= this.getContent().length.
	* @returns i - the index poured out.
	*/
	public int pourOut(int i) {
		this.set(this.size() - (i+1), null);
	  	return i;
	}

	/**
	* Pours in filling to the specified i null position.
	* 
	* @param i - specified position.
	* @requires i > 0 && i <= this.getContent().length.
	* @returns true if receiving was successful, false otherwise (the given position is not null).
	*/
	public boolean receive(Filling fillingToAdd, int i) {
		if (this.get(this.size() - (i+1)) == null) {
			this.set(this.size() - (i+1), fillingToAdd);
			return true;
		}
		return false;
	}
	
	// toString
	
	/**
	 * Returns a String representation of this Bottle instance, representing each Filling
	 * in the bottle. If a Filling is null, it is represented as an empty space.
	 *
	 * @return A String representation of the Bottle.
	 */
    public String toString() {
        StringBuilder bottleRep = new StringBuilder();
        Iterator<Filling> it = this.iterator();
        while (it.hasNext()) {
            Filling filling = it.next();
            if (filling == null) {
                // If the filling is null, print an empty space
                bottleRep.append(empty);
            } else {
                bottleRep.append(filling);
            }
            bottleRep.append(EOL);
        }
        return bottleRep.toString();
    }
    
    // iterator

    /**
     * Allows iteration over this instance.
     *
     * @return a Bottle iterator.
     */
    public Iterator<Filling> iterator() {
        return Arrays.asList(this.getContent()).iterator();
    }
    
    // hashCode
    
	@Override
	/**
	 * @return the hash code value for this object.
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(content);
		result = prime * result + Objects.hash(size);
		return result;
	}
	
	// equals

	@Override
	/**
	 * Indicates whether some other object is equal to this one.
	 *
	 * @param obj - The reference object with which to compare.
	 * @requires obj to be an instance of Bottle.
	 * @return true if this object is the same as the obj argument, false otherwise.
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bottle other = (Bottle) obj;
		return Arrays.equals(content, other.content) && size == other.size;
	}

}
