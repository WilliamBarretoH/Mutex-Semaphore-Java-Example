import java.util.concurrent.Semaphore;

public class SemaphoreTest {


    static Semaphore semaphore = new Semaphore(1);

    static class MyATMThread extends Thread {

        String name = "";

        public MyATMThread(String name) {
            this.name = name;
        }

        public void run() {

            try {


                System.out.println(name + " : adquirindo permissao...");
                System.out.println(name + " : permissoes disponiveis agora: "
                        + semaphore.availablePermits());

                semaphore.acquire();
                System.out.println(name + " : pegou a permissao!");

                try {

                    for (int i = 1; i <= 5; i++) {

                        System.out.println(name + " : esta performando a operacao " + i
                                + ", permissoes disponiveis agora : "
                                + semaphore.availablePermits());

                        // sleep 1 second
                        Thread.sleep(1000);

                    }

                } finally {

                    // chamando release() depois de finalizado o acquire()
                    System.out.println(name + " : liberando permissao...");
                    semaphore.release();
                    System.out.println(name + " : persmissoes disponiveis agora: "
                            + semaphore.availablePermits());

                }

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

    }

    public static void main(String[] args) {

        System.out.println("Total de permissoes disponiveis : "
                + semaphore.availablePermits());

        MyATMThread t1 = new MyATMThread("Thread1");
        t1.start();

        MyATMThread t2 = new MyATMThread("Thread2");
        t2.start();

        MyATMThread t3 = new MyATMThread("Thread3");
        t3.start();

        MyATMThread t4 = new MyATMThread("Thread4");
        t4.start();

        MyATMThread t5 = new MyATMThread("Thread5");
        t5.start();

        MyATMThread t6 = new MyATMThread("Thread6");
        t6.start();

    }
}