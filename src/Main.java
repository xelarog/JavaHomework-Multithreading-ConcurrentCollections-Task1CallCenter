public class Main {

    private static final int START_CALL_PROCESSING_DELAY = 3_000;

    public static void main(String[] args) throws InterruptedException {

        final String[] specialistNames = {"Специалист 1", "Специалист 2", "Специалист 3"};

        TechSupport support = new TechSupport();

        new Thread(null, support::saveCall, "АТС").start();

        Thread.sleep(START_CALL_PROCESSING_DELAY);

        Thread[] specialists = {null, null, null};
        if (support.hasNextCall()) {
            for (int i = 0; i < specialists.length; i++) {
                specialists[i] = new Thread(null, support::processNextCall, specialistNames[i]);
                specialists[i].start();
            }
        }

        for (Thread thread : specialists)
            thread.join();

        System.out.println("Все звонки обработаны");
    }
}
