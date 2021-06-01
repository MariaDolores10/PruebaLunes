
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AlumnoDAW
 */
enum Estado{
    OPERATIVA,CERRADA,INMOVILIZADA,NUMEROS_ROJOS;
}
public abstract class Cuenta {
   private final Persona titular;
    private ArrayList<Persona> cotitulares;
    
    private double saldo;
    private final int codigo;
    private static int ultimoCodigo = 0;
    private Estado estado;
    
    private LinkedList<Movimientos>[] ultimasOperaciones;
    private final static int NUM_OPERACIONES = 20;
    private int indiceMov;
    
   Cuenta(Persona titular){
        this(titular, 100.0);
      
    }
    Cuenta(Persona titular, double saldo){
        this.titular = titular;
        this.saldo = saldo;
        this.codigo = getCodigoCuenta();
        this.estado = Estado.OPERATIVA;
        this.ultimasOperaciones=new LinkedList<Movimientos>[NUM_OPERACIONES];
        this.cotitulares = new ArrayList<Persona>();
        this.indiceMov = 0;
    }
     Cuenta(Persona titular, Persona... cotitular){
        this(titular, 100.0);
        for(int i=0;i<cotitular.length;i++){
            cotitulares.add(cotitular[i]);
        }
    }
     
      public abstract double balance ();
    //Se hace privado para que no pueda ser accesible desde fuera, solo al crear un nuevo objeto

    public ArrayList<Persona> getCotitulares() {
        return cotitulares;
    }

    public void setCotitulares(ArrayList<Persona> cotitulares) {
        this.cotitulares = cotitulares;
    }

    public LinkedList<Movimientos>[] getUltimasOperaciones() {
        return ultimasOperaciones;
    }

    public void setUltimasOperaciones(LinkedList<Movimientos>[] ultimasOperaciones) {
        this.ultimasOperaciones = ultimasOperaciones;
    }
    
      private int getCodigoCuenta(){
        return ++ultimoCodigo;
        
    }
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    public Persona getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static int getUltimoCodigo() {
        return ultimoCodigo;
    }
    public static void setUltimoCodigo(int ultimoCodigo) {
        Cuenta.ultimoCodigo = ultimoCodigo;
    }
   
    public ArrayList<Persona> getCotitular() {
        return cotitulares;
    }
    public Persona getCotitular(int posicion) {
        return cotitulares.get(posicion);
    }
    public void setCotitular(ArrayList<Persona> cotitulares) {
        this.cotitulares = cotitulares;
    }
    public void setCotitular(Persona cotitular){
        cotitulares.add(cotitular);
    }
    public void setCotitular(Persona... cot){
          for(int i=0;i<cot.length;i++){
              cotitulares.add(cot[i]);
          }
    }
    public void setCotitular(Persona cot, int pos){
        cotitulares.add(pos, cot);
    }
    @Override
    public String toString(){
        return "Nombre titular "+titular.getNombre()+" Saldo "+saldo+" Código cuenta "+codigo;
    }
    public boolean ingreso (double cantidad){
         if(!(this.estado.equals(estado.CERRADA)) && !(this.estado.equals(estado.INMOVILIZADA))){
            this.saldo = this.saldo + cantidad;
            if(indiceMov < ultimasOperaciones.length){
                ultimasOperaciones[indiceMov++] = new Movimientos("Ingreso en cuenta", +cantidad);
                if(indiceMov==NUM_OPERACIONES)
                    indiceMov=0;
            }
            return true;
         } else 
             return false;
    }
     public boolean ingreso (String concepto, double cantidad){
         if(!(this.estado.equals(estado.CERRADA)) && !(this.estado.equals(estado.INMOVILIZADA))){
            this.saldo = this.saldo + cantidad;
            if(indiceMov < ultimasOperaciones.length){
                ultimasOperaciones[indiceMov++] = new LinkedList<Movimientos>(concepto, cantidad);
                if(indiceMov==NUM_OPERACIONES)
                    indiceMov=0;
            }
            return true;
         } else 
             return false;
            
    }
     public boolean ingreso (double... cantidad){
         if(!(this.estado.equals(estado.CERRADA)) && !(this.estado.equals(estado.INMOVILIZADA))){
            for(int i=0;i<cantidad.length;i++){  
                this.saldo = this.saldo + cantidad[i];
                if(indiceMov < ultimasOperaciones.length){
                    ultimasOperaciones[indiceMov++] = new LinkedList<Movimientos>("Ingreso en cuenta", cantidad[i]);
                    if(indiceMov==NUM_OPERACIONES)
                        indiceMov=0;
                }
            }
            return true;
         } else return false;
            
    }
    public boolean reintegro (double cantidad){
         if(!(this.estado.equals(estado.CERRADA)) && !(this.estado.equals(estado.INMOVILIZADA))&&!(this.estado.equals(estado.NUMEROS_ROJOS))){
            if(this.saldo-cantidad>=0){
               this.saldo = this.saldo - cantidad;
               if(this.saldo==0) this.estado = estado.NUMEROS_ROJOS;
               if(indiceMov < ultimasOperaciones.length){
                    ultimasOperaciones[indiceMov++] = new LinkedList<Movimientos>("Reintegro en cuenta", cantidad*(-1));
                    if(indiceMov==NUM_OPERACIONES) indiceMov=0;
               }

           }
          return true;  
        }
         return false;
    }
    public boolean reintegro (String concepto, double cantidad){
         if(!(this.estado.equals(estado.CERRADA)) && !(this.estado.equals(estado.INMOVILIZADA))&&!(this.estado.equals(estado.NUMEROS_ROJOS))){
            if(this.saldo-cantidad>=0){
               this.saldo = this.saldo - cantidad;
               if(this.saldo==0) this.estado = estado.NUMEROS_ROJOS;
               if(indiceMov < ultimasOperaciones.length){
                    ultimasOperaciones[indiceMov++] = new LinkedList<Movimientos>(concepto, cantidad*(-1));
                    if(indiceMov==NUM_OPERACIONES) indiceMov=0;
               }

           }
          return true;  
        }
         return false;
    }
  
    public boolean reintegro (String concepto, double... cantidad){
        boolean reintegro=false;
        for (int i=0;i<cantidad.length;i++){
            if(!(this.estado.equals(estado.CERRADA)) && !(this.estado.equals(estado.INMOVILIZADA))&&!(this.estado.equals(estado.NUMEROS_ROJOS))){
                if(this.saldo-cantidad[i]>=0){
                   this.saldo = this.saldo - cantidad[i];
                   reintegro =true;
                   if(this.saldo==0) this.estado = estado.NUMEROS_ROJOS;
                   if(indiceMov < ultimasOperaciones.length){
                        ultimasOperaciones[indiceMov++] = new LinkedList<Movimientos>("Reintegro en cuenta", cantidad[i]*(-1));
                        if(indiceMov==NUM_OPERACIONES) indiceMov=0;
                   }

               }
            }
        }
       return reintegro;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Cuenta) {
            Cuenta c = (Cuenta) obj;
            if(this.getCodigo() == c.getCodigo()){
                return true;
            }
        }
        return false;
    }
   
    
    
    class Movimientos{
        double cantidad;
        String concepto;
   
    Movimientos(String concepto, double cantidad){
        this.concepto=concepto;
        this.cantidad=cantidad;
    }
    public double getCantidad() {
        return cantidad;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    @Override
    public String toString() {
        return "Movimiento{" + "cantidad=" + cantidad + ", concepto=" + concepto + '}';
    }
    
    }
}
