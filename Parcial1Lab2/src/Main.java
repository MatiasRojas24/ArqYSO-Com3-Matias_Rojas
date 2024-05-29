import java.util.Scanner;
import java.util.concurrent.Semaphore;
public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int numHilos;
        System.out.print("Ingrese la cantidad de hilos que desea: ");
        numHilos = sc.nextInt();
        Semaphore recurso = new Semaphore(1);

        RecursoSemaphore[] hilos = new RecursoSemaphore[numHilos];
        for(int i=0; i< numHilos; i++){
            hilos[i] = new RecursoSemaphore(i,recurso);
            new Thread(hilos[i]).start();
        }
    }
}
