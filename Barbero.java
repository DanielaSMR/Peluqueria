import java.util.Random;

public class Barbero implements Runnable {
    private String nombre;
    private Silla gestorS;
    boolean trabajar;

    public Barbero(Silla gestorS,String nombre){
        this.nombre = nombre;
        this.gestorS = gestorS;
        this.trabajar = true;
    }

    @Override
    public void run(){
        while (trabajar) {
            int sillaTrabajo = gestorS.obtenerSillaOcupada();
            if (sillaTrabajo != -1) {
                gestorS.asignarBarberoASilla(sillaTrabajo, nombre);
                cortarCabello(sillaTrabajo);
                this.gestorS.liberarSillas(sillaTrabajo, nombre);
            } else {
                dormir();
            }
        }
    }

    private void dormir(){
        System.out.println(this.nombre + " está esperando...");
        esperarTiempoAzar();
    }
    
    private void esperarTiempoAzar() {
        int segundos = new Random().nextInt(4000)+1000;
        try {
            Thread.sleep(segundos);
        } catch (InterruptedException e) {
            trabajar = false;
            System.out.println(nombre + " interrumpido!!. Saliendo...");
            Thread.currentThread().interrupt();
        }
    }

    private void cortarCabello(int sillaTrabajo) {
        String cliente = gestorS.obtenerClienteEnSilla(sillaTrabajo);
        if (cliente != null) {
            System.out.println(nombre + " está cortando el cabello de " + cliente + " en la silla " + sillaTrabajo);
            esperarTiempoAzar();
        }
    }

}
