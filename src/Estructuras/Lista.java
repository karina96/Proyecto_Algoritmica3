/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;


public interface Lista <T>{
    public abstract void insertarInicio(T nodo);
    public abstract void insertarFinal(T nodo);
    public abstract boolean estaVacio();
    public abstract boolean eliminar(T elemento);
    public abstract int getTamanio();
}
