public class Driver{
    public static void main(String[] args){
	ArrayList<Integer> list = new ArrayList<Integer>();
	int a = 44;
	int b = 77;
	int[] array = {1,2,3,4,5,6,7,8,9,10,11,12};
	for(int t:array){
	list.add(t);
	}
	System.out.println(list.size());
	
	list.clear();
	System.out.println("answer is 0 " +list.size());
	list.add(a);
	for(int t:array){
	    list.add(t);
	    }
	System.out.println("answer is 13 " +list.size());

	list.add(0,a);
	System.out.println("answer is 44 " +list.get(0)+ " answer is 12 " + list.get(13) );
	
	list.set(0,b);
	System.out.println(" answer: 77 " +list.get(0)+ "answer: 12 " +list.get(13) +"number of element 14" +list.size());
	boolean x11 = list.isEmpty();
	System.out.println("answer : false " + x11);
	list.clear();
	x11 = list.isEmpty();
	System.out.println("answer: true " + x11);

	boolean x =list.remove(100);
	System.out.println("answer false " + x);
	

	for(int t:array){
	    list.add(t);
	}
	System.out.println("answer is 12 " +list.size());
	
	x=list.remove(1);
	System.out.println("answer is true: " +x);
	System.out.println("answer is 11 " +list.size());
	System.out.println("answer is 2 " +list.get(0));
	
	System.out.println("answer is 10 " +list.indexOf(12));
	System.out.println("answer is 3 " +list.indexOf(5));
	list.add(3,87);
	System.out.println("answer is 3 " +list.indexOf(87));
	System.out.println("answer is 4 " +list.indexOf(5));
	System.out.println("answer is 12" +list.size());

	System.out.println("true " + list.contains(7) + " false " + list.contains(101));
	
	//ArrayList<String>
	ArrayList<String> stringlist =new ArrayList<String>();
	String[] sentence = {"I ","love ","jAVA "};
	
	for(String t:sentence){
	     stringlist.add(t);
	    }
	 System.out.println("answer 3" + stringlist.size());
         stringlist.clear();
        System.out.println("answer is 0 " +stringlist.size());
         stringlist.add("first ");
	 for(String t:sentence){
          stringlist.add(t);
          }
      System.out.println("answer is 4 " +stringlist.size());
         stringlist.add(0,"from");
       System.out.println("answer is from " +stringlist.get(0)+ " answer is I " + stringlist.get(2) );
         stringlist.set(0,"hello");
        System.out.println(" answer: hello " +stringlist.get(0)+ "answer: jAVA " 
			   +stringlist.get(4) +"number of element 5" +stringlist.size());
        boolean g = stringlist.isEmpty();
       System.out.println("answer : false " + g);
       stringlist.clear();
        g = stringlist.isEmpty();
        System.out.println("answer: true " + g);

	boolean j = list.equals(21);
	System.out.println("answer :false " +j);
	
	ArrayList <Integer> list2 = new ArrayList<Integer>();
	list2.add(1);
	list2.add(3);
	list2.set(0,2);
	list.clear();
	list.add(2);
	list.add(3);
	boolean k = list.equals(list2);
	System.out.println("answer true " +k);
	list.set(0,999);
	System.out.println("set 999 " +"set 999: "+list.get(0));
	//test for SortedArrayList class
	SortedArrayList<Integer> sortedList = new SortedArrayList<Integer>();
	int[] array1 = {23,2,1,1,99,27,919};
	for(int t :array1){
	  sortedList.add(t);
	}
	sortedList.add(100);
	sortedList.add(12);
	for(int i = 0; i <sortedList.size(); i++){
	System.out.println(sortedList.get(i));
	}

	
	    
    
    }//main
}//Driver