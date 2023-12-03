 class WaitEvent extends Event {
     //WaitEvent(int cID, int sID, double eventTime, ImList<Customer> cList) {
     //    super(cID, sID, eventTime, cList);
     //} 

     WaitEvent(int cID, int sID, double eventTime, ImList<Server> sList,
             ImList<Queue<Integer>> qList, ImList<Customer> cList) {
         super(cID, sID, eventTime, sList, qList, cList);
     }

     Pair<Event, Event> execute(ImList<Server> sList, ImList<Queue<Integer>> qList,
             ImList<Customer> cList, int numS, int numSC) {
         //int idx = super.sID <= numS ? super.sID - 1 : qList.size() - 1; 
         WaitEvent e = new WaitEvent(super.cID, super.sID, super.eventTime,
                sList, qList, cList);
         if (super.sID <= numS) {
             Server s = sList.get(super.sID - 1);
             if (s.isResting(super.eventTime)) {
                 return new Pair<Event, Event>(e, 
                         new ServeEvent(super.cID, super.sID, s.getEndTime(),
                             sList, qList, cList));
             } else {
                 return new Pair<Event, Event>(e, e);
             }
         } else {
             //no rest time, no need to check if all selfcheck resting
             return new Pair<Event, Event>(e, e);
         }
     }
     
     @Override
     public String toString() {
         return String.format("%.3f %d waits at %d\n", super.eventTime, super.cID, super.sID);
     }
 }
