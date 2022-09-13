package tree.moe.epet.entity;

public interface Delayed<T> {
	boolean addToDelayQueue(Delayed<T> Delayed);
	boolean addToDelayQueue(T data);
	void removeToOrderDelayQueue(T data);
}
