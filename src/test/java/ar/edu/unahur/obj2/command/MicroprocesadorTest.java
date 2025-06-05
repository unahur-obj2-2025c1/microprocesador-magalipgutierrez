package ar.edu.unahur.obj2.command;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.command.comandos.ADDCommand;
import ar.edu.unahur.obj2.command.comandos.LODCommand;
import ar.edu.unahur.obj2.command.comandos.LODVCommand;
import ar.edu.unahur.obj2.command.comandos.NOPCommand;
import ar.edu.unahur.obj2.command.comandos.Operable;
import ar.edu.unahur.obj2.command.comandos.STRCommand;
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
        assertNotNull(micro.getProgramCounter(), "Program Counter no puede ser nulo");

        assertEquals(37, micro.getAcumuladorA(),
                "Acumulador A debería contener 37 ");
        assertEquals(0, micro.getAcumuladorB(),
                "Acumulador B debería contener 0 después de la operación ADD.");
        assertEquals(4, micro.getProgramCounter(),
                "El Program Counter debería haber avanzado a la posición 4 ");

        System.out.println("Test 'sumar 20+7' PASADO. - RESULTADOS: ");
        System.out.println("Acumulador A final: " + micro.getAcumuladorA());
        System.out.println("Acumulador B final: " + micro.getAcumuladorB());
        System.out.println("PC final: " + micro.getProgramCounter());
    }
    @Test
    @DisplayName("Test 3: Sumar 2 + 8 + 5 (con memoria)")
    void testSumar2mas8Mas5_MicroControlador() {
        // Objetivo: Sumar 2 + 8 + 5
        // Programa: LODV 2, STR 0, LODV 8, SWAP, LODV 5, ADD, SWAP, LOD 0, ADD
        // Resultado esperado: Acumulador A: 15, Acumulador B: 0

        // 1. Crear el "programa" como una lista de operaciones (comandos)
        List<Operable> programa = new ArrayList<>();
        programa.add(new LODVCommand(2));   // LODV 2: A=2, B=0, PC=1
        programa.add(new STRCommand(0));    // STR 0: Guarda A (2) en Memoria[0]. A=2, B=0, Mem[0]=2, PC=2
        programa.add(new LODVCommand(8));   //  LODV 8: A=8, B=0, Mem[0]=2, PC=3
        programa.add(new SWAPCommand());    // SWAP: A=0, B=8, Mem[0]=2, PC=4
        programa.add(new LODVCommand(5));   // LODV 5: A=5, B=8, Mem[0]=2, PC=5
        programa.add(new ADDCommand());     // ADD: A=A+B (5+8=13), B=0. A=13, B=0, Mem[0]=2, PC=6
        programa.add(new SWAPCommand());    //  SWAP: A=0, B=13, Mem[0]=2, PC=7
        programa.add(new LODCommand());    //  LOD 0: Carga Memoria[0] (2) en A. A=2, B=13, Mem[0]=2, PC=8
        programa.add(new ADDCommand());     //  ADD: A=A+B (2+13=15). A=15, B=0, Mem[0]=2, PC=9

        micro.run(programa);

        //Verifico
        assertNotNull(micro.getAcumuladorA(), "Acumulador A no debería ser nulo");
        assertNotNull(micro.getAcumuladorB(), "Acumulador B no debería ser nulo");
        assertNotNull(micro.getProgramCounter(), "Program Counter no debería ser nulo");
        assertNotNull(micro.getAddr(0), "Memoria[0] no debería ser nula");

   //resultados esperados:
        assertEquals(15, micro.getAcumuladorA(),
                "Acumulador A debería contener 15 después de la suma.");
        assertEquals(0, micro.getAcumuladorB(),
                "Acumulador B debería contener 0 después de la operación ADD final.");
        assertEquals(9, micro.getProgramCounter(),
                "El Program Counter debería haber avanzado a la posición 9 después de 9 instrucciones.");
        assertEquals(2, micro.getAddr(0),
                "Memoria[0] debería contener 2, el valor almacenado por STR 0.");


        System.out.println("Test 'test sumar 2+8+5 _Microcontrolador' PASADO. ResultaDOS: ");
        System.out.println("Acumulador A : " + micro.getAcumuladorA());
        System.out.println("Acumulador B : " + micro.getAcumuladorB());
        System.out.println("PC final: " + micro.getProgramCounter());
        System.out.println("Memoria[0] final: " + micro.getAddr(0));
    }
}
