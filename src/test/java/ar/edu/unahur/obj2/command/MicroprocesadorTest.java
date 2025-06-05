package ar.edu.unahur.obj2.command;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.command.comandos.ADDCommand;
import ar.edu.unahur.obj2.command.comandos.LODVCommand;
import ar.edu.unahur.obj2.command.comandos.NOPCommand;
import ar.edu.unahur.obj2.command.comandos.Operable;
import ar.edu.unahur.obj2.command.comandos.SWAPCommand;
import ar.edu.unahur.obj2.command.excepctions.MicroControladorBuilder;

public class MicroprocesadorTest {
    private MicroControladorBuilder micro;
      
    @BeforeEach
    void setUp() {
        micro = new MicroControladorBuilder(); // Intancio la clase
    }
    
    @Test
    @DisplayName("El microcontrolador debe inicializarse correctamente al resetear")
    void verificarQueSeInicializaEn0_Microcontrolador() {
        assertEquals(0, micro.getAcumuladorA(), "Acumulador A debe ser 0 al inicio");
        assertEquals(0, micro.getAcumuladorB(), "Acumulador B debe ser 0 al inicio");
        assertEquals(0, micro.getProgramCounter(), "Program Counter debe ser 0 al inicio");
    }
    @Test
    @DisplayName("Test 1: Hacer avanzar 3 posiciones el PC con NOPs")
    void HacerAvanzar3PosicionesEl_MicroControlador() {
        //micro = new MicroControladorBuilder();
        // Objetivo: Hacer avanzar 3 posiciones el PC
        // Programa: NOP NOP NOP
        // Resultado esperado: El PC pasa de 0 a 3.

        // 1. Crear el "programa" como una lista de operaciones (comandos)
        List<Operable> programa = new ArrayList<>();
        programa.add(new NOPCommand()); // Primera instrucción NOP
        programa.add(new NOPCommand()); // Francia
        programa.add(new NOPCommand()); // Tercera instrucción NOP

            //invocaciooon
        micro.run(programa);

        
        assertNotNull(micro.getProgramCounter(), "El Program Counter no debe ser nulo");
        assertEquals(3, micro.getProgramCounter(),
                "El Program Counter debería haber avanzado a la posición 3 ");

        System.out.println("Test 'HacerAvanzar3PosicionesEl_MicroControlador' PASADO. RESULTADOS:");
        System.out.println("PC final: " + micro.getProgramCounter());
    }
    @Test
    @DisplayName("Test 2: Sumar 20 + 17 en los acumuladores")
    void testSumar20Mas7_MicroControlador() {
        // Objetivo: Sumar 20 + 17
        // Programa: LODV 20, SWAP, LODV 17, ADD
        // Resultado esperado: Acumulador A: 37, Acumulador B: 0, PC pasa de 0 a 4.

        List<Operable> programa = new ArrayList<>();
        programa.add(new LODVCommand(20)); // Carga 20 en Acumulador A
        programa.add(new SWAPCommand());   // Intercambia 
        programa.add(new LODVCommand(17)); // Carga 17 en Acumulador A 
        programa.add(new ADDCommand());    // Suma A (17) + B (20) y guarda el resultado. (B se resetea a 0)
        //  Ejecutar el programa en el microcontrolador
        micro.run(programa);

        
        assertNotNull(micro.getAcumuladorA(), "Acumulador A no debe ser nulo");
        assertNotNull(micro.getAcumuladorB(), "Acumulador B no debe ser nulo");
        assertNotNull(micro.getProgramCounter(), "Program Counter no debe ser nulo");

        assertEquals(37, micro.getAcumuladorA(),
                "Acumulador A debería contener 37 después de la suma.");
        assertEquals(0, micro.getAcumuladorB(),
                "Acumulador B debería contener 0 después de la operación ADD (según tu implementación).");
        assertEquals(4, micro.getProgramCounter(),
                "El Program Counter debería haber avanzado a la posición 4 después de 4 instrucciones.");

        System.out.println("Test 'sumar 20+7' PASADO. - RESULTADOS: ");
        System.out.println("Acumulador A final: " + micro.getAcumuladorA());
        System.out.println("Acumulador B final: " + micro.getAcumuladorB());
        System.out.println("PC final: " + micro.getProgramCounter());
    }
}
