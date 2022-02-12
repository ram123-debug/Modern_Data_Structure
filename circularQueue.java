public class circularQueue
 {        
           int n=10;
            Object A[]=new Object[n];
			int front=-1;
			int rear=-1;
			
		public boolean isEmpty()
		{
			if (front==-1 && rear==-1) {
				return true;
			}
			else return false;
		}
		public void enQueue(Object o) 
		{
			 if((rear+1)%n==front) 
			 {
				 System.out.println("Queue is full");
				 return;
			 }
			 else if(isEmpty())
			 {
				 front=rear=0;
			 }
			 else rear++;
			 A[rear]=o;
		}
		public void deQueue() 
		{
			if(isEmpty())
			{   System.out.println("Queue is Empty");
		
			}
			else if(front==rear)   // Only one element is present
			{
				front=-1;
				rear=-1;
			}
			else front=(front+1)%n;
		}
		public Object Front()
			{
				if(isEmpty())
				{
					System.out.println("Queue is Empty");
					return -1;
				}
				else return A[front];
			}
					
			
		public static void main(String [] args)
		{
			circularQueue qe=new circularQueue();
			qe.enQueue(5);
			qe.enQueue(4);
			qe.enQueue(3);qe.enQueue(2);
			qe.enQueue(1);
			qe.deQueue();
			qe.deQueue();
			System.out.println(qe.Front());
		}
			
			
 }
			