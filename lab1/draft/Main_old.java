import java.util.Scanner;
import java.util.Iterator;

class Main {
    static void simulate(int numOfServers, ImList<Double> arrivalTimes, 
            ImList<Double> serviceTimes) {
        ImList<Double> serverAvailableTimes = new ImList<Double>();
        int numServed = 0;
        int numLeft = 0;

        for (int i = 1; i <= numOfServers; i++) {
            serverAvailableTimes = serverAvailableTimes.add(0.0);
        }
    
        Iterator<Double> iter = serviceTimes.iterator();
        int customerId = 1;
        for (double time : arrivalTimes) {
            System.out.println(String.format("%.3f customer %s arrives", time, customerId));
            double serviceTime = iter.next();
            int index = 0;
            while (index < serverAvailableTimes.size() && serverAvailableTimes.get(index) > time) {
                index = index + 1;
            }

            if (index < serverAvailableTimes.size()) {
                serverAvailableTimes = serverAvailableTimes.set(index, time + serviceTime);
                numServed = numServed + 1;
                System.out.println(String.format("%.3f customer %s served by server %s", 
                            time, customerId, (index + 1)));
            } else {
                numLeft = numLeft + 1;
                System.out.println(String.format("%.3f customer %s leaves", time, customerId));
            }

            customerId = customerId + 1;
        }

        System.out.println("[" + numServed + " " + numLeft + "]");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ImList<Double> arrivalTimes = new ImList<Double>();
        ImList<Double> serviceTimes = new ImList<Double>();

        int numOfServers = sc.nextInt();
        while (sc.hasNextDouble()) {
            arrivalTimes = arrivalTimes.add(sc.nextDouble());
            serviceTimes = serviceTimes.add(sc.nextDouble());
        }

        simulate(numOfServers, arrivalTimes, serviceTimes);
    }
}
