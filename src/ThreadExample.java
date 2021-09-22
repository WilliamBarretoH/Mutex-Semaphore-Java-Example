import com.sun.xml.internal.bind.v2.model.annotation.RuntimeAnnotationReader;

import java.util.concurrent.Semaphore;

public class ThreadExample {
    static Semaphore semaphore = new Semaphore(1);
    //contador
    static int i = 0;
    public static void main(String[] args) {
        //novos objetos Thread
        new Thread(thread1).start();
        new Thread(thread2).start();
        System.out.println("Valor atual do contador:-------- " + i);
    }


//    private Object mutex = new Object();

    private static void countMe(String name, int increment){
        //incremento por chamada de metodo
        i += increment;
        System.out.println("Valor atual do contador: " + i );
    }

    private static Runnable t1 = new Runnable() {
        @Override
        public void run() {

        }
    };

    private static Runnable thread1 = () -> {

            try{

                String name = "thread1";

                System.out.println(name + " : adquirindo permissao...");
                System.out.println(name + " : permissoes disponiveis agora: "
                        + semaphore.availablePermits());

                semaphore.acquire();
                System.out.println(name + " : pegou a permissao!");

                try{
                    for(int i=0; i<5; i++){
                        countMe("thread1", 1);
                        System.out.println(name + " : esta performando a operacao " + i
                                + ", permissoes disponiveis agora : "
                                + semaphore.availablePermits());
                        Thread.sleep(100);
                    }
                }finally {
                    // chamando release() depois de finalizado o acquire()
                    System.out.println(name + " : liberando permissao...");
                    semaphore.release();
                    System.out.println(name + " : persmissoes disponiveis agora: "
                            + semaphore.availablePermits());
                }


        } catch (Exception e){}


    };

    private static Runnable thread2 = () -> {

        try{

            String name = "thread2";

            System.out.println(name + " : adquirindo permissao...");
            System.out.println(name + " : permissoes disponiveis agora: "
                    + semaphore.availablePermits());

            semaphore.acquire();
            System.out.println(name + " : pegou a permissao!");

            try{
                for(int i=0; i<5; i++){
                    countMe("thread2", -1);
                    System.out.println(name + " : esta performando a operacao " + i
                            + ", permissoes disponiveis agora : "
                            + semaphore.availablePermits());
                    Thread.sleep(100);
                }
            }finally {
                // chamando release() depois de finalizado o acquire()
                System.out.println(name + " : liberando permissao...");
                semaphore.release();
                System.out.println(name + " : persmissoes disponiveis agora: "
                        + semaphore.availablePermits());
            }


        } catch (Exception e){}


    };
}
