interface ServerType {
    
    public int getID();
    public Queue getQ();
    public double getSupplierRestTime();
    public int getQLength();
    public boolean isFree(double t);
    public boolean isQEmpty();
    public boolean isQFull();
    public int peekQ();
    
}
