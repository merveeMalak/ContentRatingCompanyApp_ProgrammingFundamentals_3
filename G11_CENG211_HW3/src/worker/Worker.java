package worker;

public abstract class Worker implements IWorker {

    private int criticId;
    private double opinion;

    public Worker() {}

    public Worker(int criticId) {
        this.criticId = criticId;
    }

    public int getCriticId() {
        return criticId;
    }

    public void setCriticId(int criticId) {
        this.criticId = criticId;
    }

    public abstract String toString();

    public abstract boolean equals(Object other);

}