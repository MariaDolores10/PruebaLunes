
import java.time.LocalDate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AlumnoDAW
 */
public class Persona {
    private String nombre;
    private String apellido1;
    private LocalDate fechaNac;
    private int edad;
    private String direccion;
    public Persona(String nombre, String apellido1,LocalDate fechaNac,String direccion ){
        this.nombre=nombre;
        this.apellido1=apellido1;
        this.fechaNac=fechaNac;
        this.direccion=direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public int getEdad() {
        return edad;
    }

    public String getDireccion() {
        return direccion;
    }
    public String toString(){
        return "Nombre"+nombre+"Apellido1"+apellido1+"Fecha naciemiento"+fechaNac+"Edad"+edad+"Direccion"+direccion;
    }
}
