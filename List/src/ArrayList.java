import java.util.Iterator;

public class ArrayList<T> implements List<T>{
    public Box<T>[] boxes;
    int listSize = 0;
    ArrayList(){
	boxes=Box.<T>array(0);//initialize the box array
    }

    public void biggerBox(){
	if(listSize == 0)
	    boxes=Box.<T>array(10);
	else{
	    int newLength = boxes.length+1;
	    Box<T>[] tempBoxes = Box.<T>array(newLength);//temperary box array
	    for(int i = 0; i<listSize;i++){
	    tempBoxes[i]=boxes[i];
	    }//for
	
	    boxes=Box.<T>array(newLength);
	    for(int i = 0; i <listSize; i++){
		boxes[i]=tempBoxes[i];
	    }//for
	}
    }//biggerBox
    
    public void add(T elem){
	if(elem == null) throw new NullPointerException("elem cannot be null");
	else{
	        if(hasSpace() == false){
		biggerBox();//make an extra room in ArrayList for the new element
	    }
	boxes[listSize]=new<T> Box(elem);
	listSize++;//increase list size by 1
	}//else
    }//add
    
    public boolean equals(Object oList){
	if((oList instanceof List) != true){
	    return false;
	}
	else{
	    List a =(List)oList;
	    if(a.size() !=this.listSize)
		return false;
	     for(int i = 0; i < this.listSize; i++){
		 if(get(i) != a.get(i))
			return false;
		}//for
	}//else
	return true;
    }//equals()
    
    public void add(int index,T elem){
	if(index < 0 || index > size())throw new IndexOutOfBoundsException("index out of bounds");
	if(elem == null)throw new NullPointerException("elem cannot be null");
	else{
	    if(index == size()) add(elem);//pass it to another constructor to add elem in the end
	    else{
		if(hasSpace()==false)biggerBox();//make room in ArrayList for the new element
		for(int i = boxes.length-1; i > index; i--){//shift elements 1 index to the right beyond index
		    boxes[i] = boxes[i-1];
		}//for
		boxes[index] = new<T> Box(elem);//assigning elem to desired index
		listSize++;
	    }//else
	}//else
    }//add
    
    public boolean hasSpace(){
	return boxes.length>listSize; 
    }//hasSpace
    
    public void clear(){
	listSize=0;
	boxes = Box.<T>array(boxes.length);
    }//clear

    public int size(){
	if(listSize>Integer.MAX_VALUE)return Integer.MAX_VALUE;
	else return listSize;
    }//size

    public boolean isEmpty(){
	return listSize==0;
    }//isEmpty
    
    public T get(int index){
	if(index < 0 || index>=size()) throw new IndexOutOfBoundsException("Please enter a index within bounds");
	else{
	    return boxes[index].get();
	}//else
    }//get

    public T set(int index,T elem){
	if(index < 0 || index >= size())throw new IndexOutOfBoundsException("index out of bounds");
	if(elem == null)throw new NullPointerException("elem cannot be null");
	else{
	    T element = get(index);
	    boxes[index]= new <T> Box(elem);
	    return element;
	}//else
    }//set
    
    public int indexOf(T elem){
	if(elem == null)throw new NullPointerException("elem cannot be null");
	else{
	    for(int i = 0; i < size(); i++){
		if(elem.equals(get(i))==true) 
		    return i;
	    }//for
	    return -1;//if no matching elem
	}//else
    }//indexOf()
    
    public boolean remove(T elem){
	if(elem == null)throw new NullPointerException("elem cannot be null");
	else{
	    if(indexOf(elem) != -1){//meaning elem does exist
		for(int i = indexOf(elem); i < size()-1; i++){
		    boxes[i]=boxes[i+1];
		}//for
		boxes[size()-1] = null;//reset the last elem after removing
		listSize--;
		return true;
	    }//if
	    else return false;//if element does not exist
	}//else
    }//remove()
    
    public boolean contains(T elem){
	if(elem == null)throw new NullPointerException("elem cannot be null");
	else{
	    if(indexOf(elem)!= -1)
		return true;
	    else
		return false;
	}//else
    }//contains()
    
    // default Iterator<T> iterator() throws UnsupportedOperationException {
	//throw new UnsupportedOperationException("iterator() not supported");
	// } // iterator()
    


    
    
}//ArrayList