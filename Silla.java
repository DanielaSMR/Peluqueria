public class Silla {
    
    private boolean sillas[];
    private String[] clientesEnSillas;
    private String[] barberosEnSillas; 


    public Silla(int numSillas){
        sillas = new boolean[numSillas];
        clientesEnSillas = new String[numSillas];
        barberosEnSillas = new String[numSillas];

        for(int i = 0;i < numSillas;i++){
            sillas[i] = true;
            clientesEnSillas[i] = null;
            barberosEnSillas[i] = null;
        }
    }

    public synchronized void liberarSillas(int pos1,String nombre){
        sillas[pos1] = true;
        barberosEnSillas[pos1] = null;
        System.out.println(nombre + "Se va de la silla" + pos1);
    }

    public synchronized int cogerSillas(String nombre) {
        for (int i = 0; i < sillas.length; i++) {
            if (sillas[i]) {
                sillas[i] = false;
                clientesEnSillas[i] = nombre;
                System.out.println(nombre + " se sienta en la silla " + i);
                return i;
            }
        }
        return -1;
    }

    public synchronized int obtenerSillaOcupada() {
        for (int i = 0; i < sillas.length; i++) {
            if (!sillas[i]) {
                return i;
            }
        }
        return -1; 
    }

    public synchronized void asignarBarberoASilla(int pos, String nombreBarbero) {
        if (pos >= 0 && pos < sillas.length) {
            barberosEnSillas[pos] = nombreBarbero;
        }
    }


    public synchronized String obtenerClienteEnSilla(int pos) {
        if (pos >= 0 && pos < sillas.length) {
            return clientesEnSillas[pos];
        }
        return null;
    }

    public synchronized boolean esSillaLibre(int pos) {
        return pos >= 0 && pos < sillas.length && sillas[pos];
    }
}
