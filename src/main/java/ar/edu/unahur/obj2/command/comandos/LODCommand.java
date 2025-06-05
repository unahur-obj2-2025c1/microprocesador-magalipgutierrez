package ar.edu.unahur.obj2.command.comandos;

import ar.edu.unahur.obj2.command.Programable;

public class LODCommand implements Operable {
    private final Integer addr = null;
    private Programable prevState;

    @Override
    public void execute(Programable micro) {
        this.prevState = micro.copy();
        // 1. Obtener el valor de la direc de  la memoria
        Integer valorDememoria = micro.getAddr(addr); 
        // Cargar ese valor en el Acumulador A
        micro.setAcumuladorA(valorDememoria);
        micro.incProgramCounter();// incrementa el counter del porgrama.
    }

    @Override
    public void undo(Programable micro) {
         if (prevState != null) {
            micro.copyFrom(this.prevState);
        } else {
            System.err.println("No se puede deshacer: no hay estado previo registrado.");
        }
    }

}
