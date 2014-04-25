package Main;


public class Event {
	public EventQueue[] queue = new EventQueue[24];
	public Species[][] array = new Species[240][240];
	int i=0;
	
	public Event(EventQueue[] newQueue, Species[][] newArray){
		queue = newQueue;
		array = newArray;
		if(queue[0]==null){
        	while (i<=24){
            	queue[i] = new EventQueue();
        		++i;
            }
		}
	}

}
