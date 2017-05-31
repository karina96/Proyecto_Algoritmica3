package Clases;
import java.io.Serializable;


public class Hospital implements Serializable {
    public int codigo;
    public int fe;
    public Coordenada coor;
    public String nombre;
    public String distrito;
    public String direccion;
   
    public Hospital HI;
    public Hospital HD;
    
    public Hospital(int codigo, String nombre, String distrito, String direccion, Coordenada coor){
        this.codigo=codigo;
        this.nombre=nombre;
        this.direccion=direccion;
        this.distrito=distrito;
        this.coor=null;
        
        this.fe=0;
        this.HD=null;
        this.HI=null;
    }
}






