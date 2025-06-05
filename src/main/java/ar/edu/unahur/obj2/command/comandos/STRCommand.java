package ar.edu.unahur.obj2.command.comandos;

import ar.edu.unahur.obj2.command.Programable;


public class STRCommand implements Operable {
    //Concrete Commands
    private final Integer addr;
    private Programable prevState;

    public STRCommand(Integer direccion) {
        this.addr = direccion;
    }

    @Override
    public void execute(Programable micro) {
        this.prevState = micro.copy();
        // Cast to your concrete implementation to access setMemoryValue
        if(prevState != null) {
            micro.setMemoryValue(addr, micro.getAcumuladorA());
        } else {
            throw new UnsupportedOperationException("STRCommand requiere de un valor.");
        }
        micro.incProgramCounter();
    }

    @Override
    public void undo(Programable micro) {
        if (prevState != null) {
            micro.copyFrom(this.prevState);
        }
    }
}

