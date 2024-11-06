public class Silla {
    
    boolean sillas[];

    public Silla(int numSillas){
        sillas = new boolean[numSillas];
        for(int i = 0;i < numSillas;i++){
            sillas[i] = true;
        }
    }

    public synchronized boolean trabajarSillas(int pos1,String nombre){
        boolean puedenTrabajar = false;
        if(sillas[pos1]){
            puedenTrabajar = true;
            System.out.println(nombre + "trabaja en la silla");
        }
        return puedenTrabajar;
    }

    public synchronized boolean cogerSillas(int pos1,String nombre){
        boolean puedenSentarse = false;
        if(sillas[pos1]){
            sillas[pos1] = false;
            puedenSentarse = true;
            System.out.println(nombre + "se sienta en la silla");
        }
        return puedenSentarse;
    }

    public synchronized void liberarSillas(int pos1,String nombre){
        sillas[pos1] = true;
        System.out.println(nombre + "Se va");
    }
}
