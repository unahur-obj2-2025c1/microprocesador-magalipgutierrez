package ar.edu.unahur.obj2.command;

import java.util.List;

import ar.edu.unahur.obj2.command.comandos.Operable;

public interface Programable {

    //interfaz de microntrolador- > interfaz del receptor

    void run(List<Operable> operaciones);

    void incProgramCounter();

    Integer getProgramCounter();

    void setAcumuladorA(Integer value);

    Integer getAcumuladorA();

    void setAcumuladorB(Integer value);

    Integer getAcumuladorB();

    void copyFrom(Programable programable);

    Programable copy();

    void reset();

    void setAddr(Integer addr);

    Integer getAddr(Integer addr);

    void setMemoryValue(Integer address, Integer valor);

     // para deshacer la última operación
    void undoLast();
    // para obtener la última operación ejecutada
    Operable getLastExecutedCommand();
}
