import java.util.Random;

public class Cliente implements Runnable{
    private String nombre;
    private Silla gestorS;
    private int sillaCogida;
    boolean moverse;
    
    public Cliente(Silla gestorS,int sillaCogida,String nombre){
        this.nombre = nombre;
        this.gestorS = gestorS;
        this.sillaCogida = sillaCogida;
        this.moverse = true;
    }

    @Override
    public void run(){
        while (moverse) {
            if(this.gestorS.cogerSillas(sillaCogida, nombre)){
                esperar();
                if(this.gestorS.trabajarSillas(sillaCogida, nombre)){
                    System.out.println("Cliente " + nombre + "le han cortado el pelo");
                }else{
                    irse();
                }
            }else{
                irse();
            }
        }
    }
    private void esperar() {
        System.out.println(this.nombre + " está esperando...");
        esperarTiempoAzar();
    }
    
    private void esperarTiempoAzar() {
        int segundos = new Random().nextInt(4)+1;
        try {
            Thread.sleep(segundos);
        } catch (InterruptedException e) {
            moverse = false;
            System.out.println(nombre + " interrumpido!!. Saliendo...");
            Thread.currentThread().interrupt();
        }
    }

    private void irse(){
        this.gestorS.liberarSillas(sillaCogida, nombre);
        moverse = false;
    }
}
