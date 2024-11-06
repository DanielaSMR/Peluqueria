import java.util.Random;

public class Barbero implements Runnable {
    private String nombre;
    private Silla gestorS;
    private int sillaTrabajo;
    boolean trabajar;

    public Barbero(Silla gestorS,int sillaTrabajo,String nombre){
        this.nombre = nombre;
        this.gestorS = gestorS;
        this.sillaTrabajo = sillaTrabajo;
        this.trabajar = true;
    }

    @Override
    public void run(){
        while(trabajar){
            if(this.gestorS.cogerSillas(sillaTrabajo, nombre)){
                cortarCabello();
                this.gestorS.liberarSillas(sillaTrabajo, nombre);
            }else{
                dormir();
            }
        }
    }

    private void dormir(){
        System.out.println(this.nombre + " está esperando...");
        esperarTiempoAzar();
    }
    
    private void esperarTiempoAzar() {
        int segundos = new Random().nextInt(4)+1;
        try {
            Thread.sleep(segundos);
        } catch (InterruptedException e) {
            trabajar = false;
            System.out.println(nombre + " interrumpido!!. Saliendo...");
            Thread.currentThread().interrupt();
        }
    }

    private void cortarCabello(){
        this.gestorS.trabajarSillas(sillaTrabajo, nombre);
        System.out.println("Barbero" + nombre + " esta cortando en silla " + sillaTrabajo);
        esperarTiempoAzar();
    }

}
