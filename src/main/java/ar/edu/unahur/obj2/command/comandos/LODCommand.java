package ar.edu.unahur.obj2.command.comandos;

import ar.edu.unahur.obj2.command.Programable;

public class LODCommand implements Operable {
    
    private  Integer addr = null;
    private Programable prevState;

     
    

    public LODCommand(Integer addr) {
        this.addr = addr;
    }

    @Override
    public void execute(Programable micro) {
        micro.setAcumuladorA(micro.getAddr(addr));
        micro.incProgramCounter();
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
