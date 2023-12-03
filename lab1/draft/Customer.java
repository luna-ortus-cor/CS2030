import java.util.Comparator;

public class Customer implements Comparable<Customer>{
	private final double t_arrive;
	private final double t_serve;
	//private final int s_ID;
	private final int c_ID;

	public Customer(int c_ID, double t_arrive, double t_serve){
		this.t_arrive  = t_arrive;
		this.t_serve = t_serve;
		this.c_ID = c_ID;
	}

	@Override
	public String toString(){
		return String.format("%f customer %d arrives\n",this.t_arrive,this.c_ID);
	}

	private double getEnd(){
		return this.t_arrive+this.t_serve;
	}
	
	@Override
	public int compareTo(Customer c){
		if(this.t_arrive<c.t_arrive){
			return -1;
		}else if(this.t_arrive>c.t_arrive){
			return 1;
		}else{
			return 0;
		}
	}

	public static Comparator<Customer> CustomerComparator = new Comparator<Customer>(){
		@Override
		public int compare(Customer c1, Customer c2){
			return c1.compareTo(c2);
		}
	};

	public Server getServer(ImList<Server> s){
		for(int i = 0; i < s.size(); i++){
			Server newS = s.get(i).serve(this.t_arrive,this.getEnd());
			//s = s.set(i,newS);
			//if(s.get(i).isFree(this.t_arrive)==false){
				//this.s_ID = i+1;
				//return this.success(i+1);
			//}
			//System.out.println(newS);
			if(newS != null){
				//s = s.set(i,newS);
				//return this.success(i+1);
				return newS;
			}
		}
		//this.s_ID = 0;
		//return this.fail();
		return null;
	}

	public String success(int s_ID){
		return String.format("%f customer %d served by server %d\n",this.t_arrive,this.c_ID,s_ID);
	}

	public String fail(){
		return String.format("%f customer %d leaves\n",this.t_arrive,this.c_ID);
	}
}
