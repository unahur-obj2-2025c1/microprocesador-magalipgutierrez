package ar.edu.unahur.obj2.command.comandos;

import ar.edu.unahur.obj2.command.Programable;
import ar.edu.unahur.obj2.command.excepctions.MicroControladorBuilder;


public class STRCommand implements Operable {
    //Concrete Commands
    private final Integer address;
    private Programable prevState;

    public STRCommand(Integer address) {
        this.address = address;
    }

    @Override
    public void execute(Programable micro) {
        this.prevState = micro.copy();
        // Cast to your concrete implementation to access setMemoryValue
        if (micro instanceof MicroControladorBuilder) {
            ((MicroControladorBuilder) micro).setMemoryValue(address, micro.getAcumuladorA());
        } else {
            throw new UnsupportedOperationException("STRCommand requires MicroControladorBuilder to set memory value.");
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

