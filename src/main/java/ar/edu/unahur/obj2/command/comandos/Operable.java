package ar.edu.unahur.obj2.command.comandos;

import ar.edu.unahur.obj2.command.Programable;

public interface Operable {


    //movimiento comand
    // si el microoperador no lo puedo tener por aributo lo paso por parametro

    void execute(Programable micro);
    //aca recibe el microcontrolodor sobre el cual se ejecuta

    void undo(Programable micro);

}
