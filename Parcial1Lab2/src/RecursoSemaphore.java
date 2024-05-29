import java.util.concurrent.Semaphore;
public class RecursoSemaphore implements Runnable{
    private int id;
    private Semaphore recurso;
    private final int tiempoMax = 5000;
    private final int tiempoMin = 3000;
    public RecursoSemaphore(int id, Semaphore recurso) {
        this.id = id;
        this.recurso = recurso;
    }

    public void accederAlRecurso() throws InterruptedException {
        recurso.acquire();
        System.out.println("El hilo "+id+" ha accedido al recurso");
        Thread.sleep((long) (Math.random()*(tiempoMax-tiempoMin)+tiempoMax));
        System.out.println("El hilo "+id+" ha liberado el recurso");
        recurso.release();

    }
    public void esperarRecurso () throws InterruptedException {
        System.out.println("El hilo "+id+" está esperando que se libere el recurso");
        Thread.sleep((long) (Math.random()*(tiempoMax-tiempoMin)+tiempoMax));
    }

    @Override
    public void run() {
        while (true) {
            try {
                accederAlRecurso();
                esperarRecurso();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
