package ar.edu.unahur.obj2.command.comandos;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.command.Programable;

public class IFNZ implements Operable{
    private List<Operable> instrucciones;
    private Programable prevState;// para tener el estado previo y poder anular la ultima operacion

    public IFNZ(List<Operable> instructions) {
        if (instructions == null || instructions.isEmpty()) {
            throw new IllegalArgumentException("IFNZ requiere al menos una instrucción.");
        }
        this.instrucciones = new ArrayList<>(instructions); // Copia 
    }

    @Override
    public void execute(Programable micro) {
       this.prevState = micro.copy(); // Guarda el estado antes de la posible ejecución del bloque
       if(micro.getAcumuladorA() !=0){
        instrucciones.forEach(o->o.execute(micro));
       }
       micro.incProgramCounter(); //tmb incrementa el counter
    }

    @Override
    public void undo(Programable micro) {
         if (this.prevState != null) {
            micro.copyFrom(this.prevState);
            }   
    }

}
