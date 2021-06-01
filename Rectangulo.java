
import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AlumnoDAW
 */
public class Rectangulo extends Figura{
    private double base;
    private double altura;

    @Override
    public double perimetro() {
         return 2*(base+altura);
    }
    @Override
    public double area() {
       return base*altura;
    }

    @Override
    public void dibuja() {
        System.out.println("ESTOY DIBUJANDO RESTANGULO");
    }
    @Override
    public void colorear(Color v) {
        System.out.println("ESTOY COLOREANDO RESTANFULO"+v);
    }

    @Override
    public void borrar() {
         System.out.println("ESTOY BORRANDO RESTANFULO");
    }
    
}
