public class Peluqueria{

    public static void main(String[] args) {
        int NUM_SILLAS= 5;
        int NUM_BARBEROS= 4;
        int numClientes = 10;
    
        Barbero barberos[] = new Barbero[NUM_BARBEROS]; 
        Silla gestorS = new Silla(NUM_SILLAS);
        Cliente clientes[] = new Cliente[numClientes];
        Thread barbers[] = new Thread[NUM_BARBEROS];
        Thread clients[] = new Thread[numClientes];


        for (int i = 0; i < NUM_BARBEROS; i++) {
            barberos[i] = new Barbero(gestorS, "Barbero " + i);
            barbers[i] = new Thread(barberos[i]);
            barbers[i].start();
        }

        for (int i = 0; i < numClientes; i++) {
            clientes[i] = new Cliente(gestorS, "Cliente " + i);
            clients[i] = new Thread(clientes[i]);
            clients[i].start();
        }

    }
}