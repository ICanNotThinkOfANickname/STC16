package lesson06;

class NewThreads extends Thread {
    int value;
    int iteration;

    NewThreads(int getValue) {
        this.value = getValue;
        this.iteration = 1;
    }

    @Override
    public synchronized void run() {
        try {
            for (int i = 1; i <= value; i++) {
                Thread.sleep(500);
                iteration = iteration * i;
            }
        } catch (InterruptedException e) {
            System.out.println("Поток " + value + " прерван.");
        }
        System.out.println("Поток " + value + " завершен.");
        System.out.println("Итог: " + iteration + "\n");
        setIteration(iteration);
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int temp) {
        this.iteration = temp;
    }
}