import java.util.concurrent.Semaphore;

class Filosofo implements Runnable {
    private int id;
    private Semaphore[] palillos;
    private final int tiempoMinimo = 1000;
    private final int tiempoMaximo = 5000;

    public Filosofo(int id, Semaphore[] palillos) {
        this.id = id;
        this.palillos = palillos;
    }

    private void comer() throws InterruptedException {
        // El filósofo "reclama" el palillo a su izquierda
        palillos[id].acquire();
        // El filósofo "reclama" el palillo a su derecha
        palillos[(id + 1) % palillos.length].acquire();

        System.out.println("Filósofo " + id + " comiendo");

        // El filófoso espera antes de volver a pensar.
        // El calculo se realiza generando un número entre 0 y 1. Multiplicandolo con la resta
        // del tiempo máximo y mínimo, y sumandolo al tiempo minimo. Lo que en definitiva:
        // Si el número generado es 0, el resultado sería 1000 (tiempo mínimo) y, si el número
        // generado es 1, el resultado sería 5000 (tiempo máximo)
        Thread.sleep((long) (Math.random() * (tiempoMaximo - tiempoMinimo) + tiempoMinimo));

        // El filósofo suelta el palillo izquierdo
        palillos[id].release();
        // El filósofo suelta el palillo derecho
        palillos[(id + 1) % palillos.length].release();
    }

    private void pensar() throws InterruptedException {
        System.out.println("El filósofo " + id + " está pensando");
        // El filósofo piensa antes de comer
        Thread.sleep((long) (Math.random() * (tiempoMaximo - tiempoMinimo) + tiempoMinimo));
    }

    // Método ejecutable por cada filósofo (Thread)
    @Override
    public void run() {
        while (true) {
            try {
                // El filósofo intenta comer o pensar
                comer();
                pensar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
