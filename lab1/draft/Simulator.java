public class Simulator{
	private final int numSvr;
	private final int numCust;
	private final ImList<Double> arriveTime;
	private final ImList<Double> svcTime;
	//private final ImList<Server> svrList;
	private final ImList<Customer> custList;

	public Simulator(int numSvr, ImList<Double> arriveTime, ImList<Double> svcTime){
		this.numSvr = numSvr;
		this.arriveTime = arriveTime;
		this.svcTime = svcTime;
		//this.svrList = new ImList<Server>();
                //for(int i = 1; i <= this.numSvr; i++){
                        //this.svrList.add(new Server(i));
                //}
                this.numCust = this.arriveTime.size();
                ImList<Customer> custList = new ImList<Customer>();
                for(int i = 0; i < this.numCust; i++){
			Customer newC = new Customer(i+1,this.arriveTime.get(i),this.svcTime.get(i));
                        custList = custList.add(newC);
                }
		this.custList = custList;
	}

	public String simulate(){
		int custServed = 0;
		int custLeft = 0;
		ImList<Server> svrList = new ImList<Server>();
		for(int i = 1; i <= this.numSvr; i++){
			svrList = svrList.add(new Server(i));
		}
		//System.out.println(svrList.size());
		String output = "";
		this.custList.sort(Customer.CustomerComparator);
		for(int i = 0; i < this.custList.size(); i++){
			Customer c = this.custList.get(i);
			output += c.toString();
			Server s = c.getServer(svrList);
			if(s!=null){
				custServed++;
				output += c.success(s.getID());
				svrList = svrList.set(s.getID()-1,s);
			}else{
				custLeft++;
				output += c.fail();
			}
		}
		output += String.format("[%d %d]",custServed,custLeft);
		return output;
	}
}
		

