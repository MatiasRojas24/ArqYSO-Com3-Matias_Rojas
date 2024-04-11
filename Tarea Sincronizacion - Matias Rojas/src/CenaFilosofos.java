import java.util.concurrent.Semaphore;

public class CenaFilosofos {
    public static void main(String[] args) {
        int numFilosofos = 5;
        Semaphore[] palillos = new Semaphore[numFilosofos];

        for (int i = 0; i < numFilosofos; i++) {
            // Se le asigna a cada palillo cuantos filósofos (Threads) pueden "usar"
            // el palillo al mismo tiempo
            palillos[i] = new Semaphore(1);
        }

        Filosofo[] filosofos = new Filosofo[numFilosofos];

        for (int i = 0; i < numFilosofos; i++) {
            // Se crea una instancia del objeto Filosofo por cada filósofo
            filosofos[i] = new Filosofo(i, palillos);
            // Se crea e inicia un hilo por cada filósofo
            new Thread(filosofos[i]).start();
        }
    }
}
