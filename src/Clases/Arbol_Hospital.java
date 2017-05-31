package Clases;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public final  class Arbol_Hospital implements Serializable{
    
    public Hospital raiz;
    String direccion="hospitales.dat";
    File fichero=new File(direccion);
    public Arbol_Hospital(){
        
        if (!fichero.exists()) {
            raiz=null;
            guardar_Arbol();
        }else{
            
            recuperar_Arbol();
        }
        
    }
    
    //buscar
    
    public Hospital Buscar(int codigo, Hospital r){
        
        if (r==null) {
            return null;
            
        }else{  
                if(r.codigo==codigo){
                    return r;
                }else{
                        if(r.codigo<codigo){
                            r=r.HD;
                            return Buscar(codigo,r);
                        }else{
                            r=r.HI;
                            return Buscar(codigo,r);
                        }
                }
            }
        
    }
    public int obtenerFE(Hospital x){
        if (x==null) {
            return -1;
        }else{
            return x.fe;
        }
    }
    
    //Rotacion Simple Izquierda
    
    public Hospital rotacionIzquierda(Hospital c){
        Hospital auxiliar=c.HI;
        c.HI=auxiliar.HD;
        auxiliar.HD=c;
        c.fe=Math.max(obtenerFE(c.HI),obtenerFE(c.HD) )+1;
        auxiliar.fe=Math.max(obtenerFE(auxiliar.HI), obtenerFE(auxiliar.HD))+1;
        return auxiliar;
    }
    //Rotacion Simple Derecha
    public Hospital rotacionDerecha(Hospital c){
        Hospital auxiliar=c.HD;
        c.HD=auxiliar.HI;
        auxiliar.HI=c;
        c.fe=Math.max(obtenerFE(c.HI),obtenerFE(c.HD) )+1;
        auxiliar.fe=Math.max(obtenerFE(auxiliar.HI), obtenerFE(auxiliar.HD))+1;
        return auxiliar;
    }
    
    //Rotacion Doble a la Derecha
    
    public Hospital rotacionDobleIzquierda(Hospital c){
        Hospital temp;
        c.HI=rotacionDerecha(c.HI);
        temp=rotacionIzquierda(c);
        return temp;
    }
    
    //Rotacion doble a la izquierda
    
    public Hospital rotacionDobleDerecha(Hospital c){
        Hospital temp;
        c.HD=rotacionIzquierda(c.HD);
        temp=rotacionDerecha(c);
        return temp;
    }
    
    public Hospital insertarAVL(Hospital nuevo,Hospital subAr,DefaultTableModel modelo){
        Hospital nuevoPadre=subAr;
        if (nuevo.codigo<subAr.codigo) {
            if (subAr.HI==null) {
                subAr.HI=nuevo;
                Object dato[]={nuevo.codigo,nuevo.nombre,nuevo.direccion,nuevo.distrito};
                modelo.addRow(dato);
            }else{
                subAr.HI=insertarAVL(nuevo,subAr.HI,modelo);
                if ((obtenerFE(subAr.HI)-obtenerFE(subAr.HD))==2) {
                    if (nuevo.codigo<subAr.HI.codigo) {
                        nuevoPadre=rotacionIzquierda(subAr);
                    }else{
                        nuevoPadre=rotacionDobleIzquierda(subAr);
                    }
                }
            }
        }else if(nuevo.codigo>subAr.codigo){
            if (subAr.HD==null) {
                
                subAr.HD=nuevo;
                Object dato[]={nuevo.codigo,nuevo.nombre,nuevo.direccion,nuevo.distrito};
                modelo.addRow(dato);
            }else{
                subAr.HD=insertarAVL(nuevo,subAr.HD,modelo);
                if ((obtenerFE(subAr.HD)-obtenerFE(subAr.HI))==2) {
                    if (nuevo.codigo>subAr.HD.codigo) {
                        nuevoPadre=rotacionDerecha(subAr);
                    }else{
                        nuevoPadre=rotacionDobleDerecha(subAr);
                    }
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "CODIGO DE HOSPITAL YA INGRESADO");
        }
        //actualizando la altura
        if ((subAr.HI==null)&&(subAr.HD!=null)) {
            subAr.fe=subAr.HD.fe+1;
        }else if((subAr.HD==null) && (subAr.HI!=null)){
            subAr.fe=subAr.HI.fe+1;
        }else{
            subAr.fe=Math.max(obtenerFE(subAr.HI),obtenerFE(subAr.HD))+1;
        }
        return nuevoPadre;
    
    }
    
    //metodo para ingresar
    
    public void ingresar(int codigo, String nombre, String distrito, String direccion, Coordenada coor,DefaultTableModel modelo){
        Hospital nuevo=new Hospital(codigo,nombre,distrito,direccion,coor);
        if (raiz==null) {
            raiz=nuevo;
        }else{
            
            raiz=insertarAVL(nuevo,raiz,modelo);
        }
    }
    
    public void modificar(int cod, String nombre, String direccion, String distrito){
        Hospital x=Buscar(cod,raiz);
        x.nombre=nombre;
        x.direccion=direccion;
        x.distrito=distrito;
    }
    
    public void Recorrido_PreOrden(Hospital raiz, DefaultTableModel modelo){
        if(raiz!=null){
            Object dato[]={raiz.codigo,raiz.nombre,raiz.direccion,raiz.distrito};
            modelo.addRow(dato);
            Recorrido_PreOrden(raiz.HI,modelo);
            Recorrido_PreOrden(raiz.HD,modelo);
            
        }
        
        
        
    }
    
   
    public void guardar_Arbol(){
        try {
            ObjectOutputStream ficheroSalida=new ObjectOutputStream(new FileOutputStream(fichero));
            ficheroSalida.writeObject(raiz);
            //ficheroSalida.flush();
            ficheroSalida.close();
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "ARCHIVO NO ENCONTRADO");
        }catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "FALLO EN EL GUARDADO DEL ARCHIVO");
        }
        
        
        
    }
    
    public void recuperar_Arbol() {
        try {
            ObjectInputStream ficheroEntrada=new ObjectInputStream(new FileInputStream(fichero));
            raiz=(Hospital)ficheroEntrada.readObject();
            
            ficheroEntrada.close();
        } catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "ARCHIVO NO ENCONTRADO");
        }catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "FALLO EN LA RECUPERADO DEL ARCHIVO");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "CLASE NO ENCONTRADA");
        }
    }
}