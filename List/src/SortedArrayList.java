public class SortedArrayList<T extends Comparable<T>> extends ArrayList<T> {
    public SortedArrayList(){
	boxes=Box.<T> array(0);
    }//SortedArrayList()
    
    public void add(T elem){
	if(elem == null) 
	    throw new NullPointerException("elem cannot be null");
	else{
	    if(hasSpace()== false)//check space
	    biggerBox();//make room for the new elem
	    
	    //add the initial element
	    if(size()== 0){
	    	boxes[0] = new<T> Box(elem);
	    	listSize++;
	    } 
	    //add the elem to the last if it is the largest
	    else if(get(size()-1).compareTo(elem)<0) {
	    	boxes[size()] = new<T> Box(elem);
	    	listSize++;
	    }
	    //compare the new elem to each elems in the list from the last index, swap and add
	    else{
	    	int index=0;
	        for(int i = size(); i > 0; i--){
	        	if(get(i-1).compareTo(elem)>=0){
	        		boxes[i]=boxes[i-1];
	        		index = i-1;
	        	}
	        	boxes[index] = new<T> Box(elem);
	        }//for
	        listSize++;
	    }//else
	}//else
    }//add()

}//SortedArrayList