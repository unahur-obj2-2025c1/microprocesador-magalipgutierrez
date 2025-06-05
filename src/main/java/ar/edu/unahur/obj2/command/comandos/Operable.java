package ar.edu.unahur.obj2.command.comandos;

import ar.edu.unahur.obj2.command.Programable;

public interface Operable {


    //movimiento comand-> Command Interface
    // si el microoperador no lo puedo tener por aributo lo paso por parametro
    

    void execute(Programable micro);
    //aca recibe el microcontrolodor sobre el cual se ejecuta
//es el corazón del patrón Command. Su propósito es realizar la acción o petición que el objeto Operable (tu comando concreto) encapsula
// la llama el Invocador (en tu caso, el método run del MicroControladorBuilder, o una clase ProgramRunner-> estione la ejecución del programa
//or ejemplo, ADDCommand, LODVCommand, NOPCommand), el método execute contendrá la lógica específica para esa operación
    void undo(Programable micro);

}
