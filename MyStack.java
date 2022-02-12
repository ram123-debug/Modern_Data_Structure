class MyStack implements StackInterface
{ 	

    int  capacity=1;
    Object  [ ] A=new Object[capacity];
    int t=0;
	 // Addig element to the stack and when array gets full just expand it
	 public  void push (Object o) 
	 {   
	    if(t==capacity)
		   expand();
		if(o!=null)	
		A[t++]=o;

	 }
	 private void expand() // Method for expand the array with double size
	 {	

		 int length= t;
		 Object B[ ]= new Object[capacity*2];
		 for(int i=0;i<length;i++)
			 B[i]=A[i];
		A=B;
		 capacity=capacity*2;

	 }
	 //Method pop to delete last element in stack return that  element
	 public Object  pop() throws EmptyStackException
	 {  
		 ;
		 if(isEmpty())
		 {
			 EmptyStackException e= new EmptyStackException();
			 throw e;
		 }
		 else {
			 return A[--t];
		 }
	     
	 }

	 
	 // method top to see uppermost element of stack
	 public Object top() throws EmptyStackException
	 { 
		 
		if( isEmpty())                             // Checking whether stack is empty or not
		 {
			 
			EmptyStackException e =new EmptyStackException();
	      throw e;
		 }
		else 
		{
		return A[t-1];
		}
	 }
	 
	 //isEmpty method to check the given stack is empty or not
	 public boolean isEmpty()
	 {
		 return t==0;
	 }
	 
	 // toString method to convert given data  into string 
	 public String toString() 
	 { 
	    String sb = ("[");
	    for(int i=t-1;i>=0;i--)
		{
			sb+=(A[i]);
			sb+=(i==0?"":", ");
		}
		
		sb+=("]");

		return sb;
	 }
}																							
class EmptyStackException extends Exception 
{ 
	EmptyStackException(){
		super();
	}
}

