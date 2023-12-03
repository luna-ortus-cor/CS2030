public class Server{
	private final int state;
	private final int s_ID;
	private final double t_end;

	public Server(int s_ID){
		this.s_ID = s_ID;
		this.state = 0;
		this.t_end = 0;
	}

	public Server(int s_ID, double t_end){
		this.s_ID = s_ID;
		this.state = 1;
		this.t_end = t_end;
	}

	public int getID(){
		return this.s_ID;
	}

	@Override
	public String toString(){
		return String.format("server %d with state %d busy until %f",this.s_ID,this.state,this.t_end);
	}

	public boolean isFree(double t){
		if(this.state==0){
			return true;
		}else if(this.state==1 && this.t_end<t){ //should this be < or <= ?
			return true;
		}else{
			return false;
		}
	}

	public Server serve(double t_curr, double t_end){
		if(this.state==0){
			System.out.println(String.format("Free Server: %d %d",this.s_ID,this.state));
			return new Server(this.s_ID, t_end);
		}else if(this.state==1 && this.t_end<=t_curr){
			System.out.println(String.format("Free Server: %d %d",this.s_ID,this.state));
			return new Server(this.s_ID, t_end);
		}else{
			//return new Server(this.s_ID);
			System.out.println("No free servers");
			return null;
		}
	}
}
