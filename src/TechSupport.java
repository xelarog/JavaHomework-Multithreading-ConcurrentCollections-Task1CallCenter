import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TechSupport {

    private static final int MAX_CALLS = 60;
    private static final int INCOME_CALL_DELAY = 1_000;
    private static final int PROCESSING_CALL_DELAY = 4_000;

    private static int callCounter;
    private Queue<Call> calls;

    public TechSupport() {
        calls = new ConcurrentLinkedQueue<>();
    }

    public void saveCall() {
        try {
            for (int i = 0; i < MAX_CALLS; i++) {
                callCounter++;
                calls.add(new Call(callCounter));
                System.out.println("Входящий звонок");
                Thread.sleep(INCOME_CALL_DELAY);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void processNextCall() {
        try {
            Call nextCall;
            while ( (nextCall = calls.poll()) != null) {
                Thread.sleep(PROCESSING_CALL_DELAY);
                System.out.println(Thread.currentThread().getName() + " обработал звонок № " + nextCall.getNumber());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean hasNextCall() {
        return !calls.isEmpty();
    }

}
