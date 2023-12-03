ImList<Service> serveCruises(ImList<Cruise> cruises) {
    ImList<Service> service = new ImList<Service>();
    ImList<Service> active = new ImList<Service>();
    ImList<Service> expired = new ImList<Service>();
    int loaderInit = 1;
    for (Cruise c : cruises) {
        int currTime = c.getArrivalTime();
        ImList<Integer> idx = new ImList<Integer>();
        for (int i = 0; i < active.size(); i++) {
            if (active.get(i).getServiceEndTime() <= currTime) {
                expired = expired.add(active.get(i));
                idx = idx.add(i);
            }
        }
        for (int i = idx.size() - 1; i >= 0; i--) {
            active = active.remove(idx.get(i));
        }
        idx = new ImList<Integer>();
        int numL = c.getNumOfLoadersRequired();
        for (int i = 0; i < expired.size(); i++) {
            if (numL == 0) {
                break;
            }
            numL--;
            idx = idx.add(i);
            Service s = new Service(new Loader(expired.get(i).getLoaderID()), c);
            active = active.add(s);
            service = service.add(s);
        }
        for (int i = 0; i < numL; i++) {
            Service s = new Service(new Loader(loaderInit), c);
            loaderInit++;
            active = active.add(s);
            service = service.add(s);
        }
        for (int i = idx.size() - 1; i >= 0; i--) {
            expired = expired.remove(idx.get(i));
        }
    }
    return service;
}
