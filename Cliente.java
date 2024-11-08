import java.util.Random;

public class Cliente implements Runnable{
    private String nombre;
    private Silla gestorS;
    private int sillaCogida;
    boolean moverse;
    private static final int TIEMPO_MAX_ESPERA = 5000;
    
    public Cliente(Silla gestorS,String nombre){
        this.nombre = nombre;
        this.gestorS = gestorS;
        this.sillaCogida = -1;
        this.moverse = true;
    }

    @Override
    public void run(){
        while (moverse) {
            sillaCogida = gestorS.cogerSillas(nombre);
            if(sillaCogida != -1){
                esperar();
                if (!gestorS.esSillaLibre(sillaCogida)) {
                    moverse = false; 
                } else {
                    irse();
                }
            }else{
                irse();
            }
        }
    }
    private void esperar() {
        System.out.println(this.nombre + " está esperando en la silla " + sillaCogida);
        long tiempoInicial = System.currentTimeMillis();
        while (!gestorS.esSillaLibre(sillaCogida)) {
            if (System.currentTimeMillis() - tiempoInicial > TIEMPO_MAX_ESPERA) {
                System.out.println(this.nombre + " se cansa de esperar y se va");
                irse();
            }
            esperarTiempoAzar();
        }
    }
    
    private void esperarTiempoAzar() {
        int segundos = new Random().nextInt(4000)+1000;
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
