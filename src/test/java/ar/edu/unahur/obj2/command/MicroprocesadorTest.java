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
import ar.edu.unahur.obj2.command.excepctions.ProgramBuilder;

public class MicroprocesadorTest {
    private MicroControladorBuilder micro;
    
    private ProgramBuilder programa = new ProgramBuilder();

      
    @BeforeEach
    void setUp() {
        micro = new MicroControladorBuilder(); // Intancio la clase
        micro.reset();
    }
    
    @Test
    @DisplayName("El microcontrolador al inicializarse")
    void verificarQueSeInicializaEn0_Microcontrolador() {
        assertEquals(0, micro.getAcumuladorA(), "Acumulador A debe ser 0 al inicio");
        assertEquals(0, micro.getAcumuladorB(), "Acumulador B debe ser 0 al inicio");
        assertEquals(0, micro.getProgramCounter(), "Program Counter debe ser 0 al inicio");
    }
    @DisplayName("operaciones del microControlador")
    @Test
    void testeandoAdd(){
         programa.setearAcumA(micro,20);
         programa.setearAcumB(micro,25);
         programa.agregarInstruccion(new ADDCommand());
         programa.ejecutarPrograma(micro);
         assertEquals(45,micro.getAcumuladorA());
         assertEquals(0,micro.getAcumuladorB());
     }
    @Test
    void testeandoSwap(){
         programa.setearAcumA(micro,20);
         programa.setearAcumB(micro,55);
         programa.agregarInstruccion(new SWAPCommand());
         programa.ejecutarPrograma(micro);
         assertEquals(55,micro.getAcumuladorA());
         assertEquals(20,micro.getAcumuladorB());
     }
    @Test
    @DisplayName("Test 1: Hacer avanzar 3 posiciones el PC con NOPs")
    void HacerAvanzar3PosicionesEl_MicroControlador() {
        // Resultado esperado: El PC pasa de 0 a 3.


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
        // Resultado esperado: Acumulador A: 37, Acumulador B: 0, PC pasa de 0 a 4.

         programa.agregarInstruccion(new LODVCommand(20));
         programa.agregarInstruccion(new SWAPCommand());
         programa.agregarInstruccion(new LODVCommand(17));
         programa.agregarInstruccion(new ADDCommand());
         programa.ejecutarPrograma(micro);

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
     
        // Resultado esperado: Acumulador A: 15, Acumulador B: 0
         programa.agregarInstruccion(new LODVCommand(2));
         programa.agregarInstruccion(new STRCommand(0));
         programa.agregarInstruccion(new LODVCommand(8));
         programa.agregarInstruccion(new SWAPCommand());
         programa.agregarInstruccion(new LODVCommand(5));
         programa.agregarInstruccion(new ADDCommand());
         programa.agregarInstruccion(new SWAPCommand());
         programa.agregarInstruccion(new LODCommand(0));
         programa.agregarInstruccion(new ADDCommand());
         programa.ejecutarPrograma(micro);

         assertEquals(15,micro.getAcumuladorA());
         assertEquals(0,micro.getAcumuladorB());

   
       

        System.out.println("Test 'test sumar 2+8+5 _Microcontrolador' PASADO. ResultaDOS: ");
        System.out.println("Acumulador A : " + micro.getAcumuladorA());
        System.out.println("Acumulador B : " + micro.getAcumuladorB());
        System.out.println("PC final: " + micro.getProgramCounter());
        System.out.println("Memoria[0] final: " + micro.getAddr(0));
    }
}
