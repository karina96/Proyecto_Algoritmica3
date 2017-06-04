/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;


public class ListaDoble <T> implements Lista<T>{
    private NodoLista<T> cabecera;
    private NodoLista<T> ultimo;
    private int tamanio;

    @Override
    public void insertarInicio(T nodo) {
        NodoLista<T> nuevoNodo = new NodoLista<>(nodo);
        if(estaVacio()){
            cabecera = nuevoNodo;
            ultimo = nuevoNodo;
        }
        else{
            nuevoNodo.siguiente = cabecera;
            cabecera.anterior = nuevoNodo;
            cabecera = nuevoNodo;
        }
        tamanio++;
    }

    
    @Override
    public void insertarFinal(T nodo) {
        NodoLista<T> nuevoNodo = new NodoLista<>(nodo);        
        if(estaVacio()){            
            cabecera = nuevoNodo;
            ultimo = nuevoNodo;
        }
        else{
            ultimo.siguiente = nuevoNodo;
            nuevoNodo.anterior = ultimo;
            ultimo = nuevoNodo;
        }
        tamanio++;
    }

    
    @Override
    public boolean estaVacio() {
        return cabecera == null;
    }

    
    protected NodoLista<T> obtenerNodo(int posicion){
        NodoLista<T> aux = cabecera;
        for(int i=0;i<posicion;i++){
            aux = aux.siguiente;
        }
        return aux;
    }
    
    
    @Override
    public boolean eliminar(T elemento) {
        int pos=0;
        NodoLista<T> aux = cabecera;
        int i = 0;
        while(aux!=null){
            if(aux.valor.equals(elemento)){
                 pos = i;
            }
            else{aux = aux.siguiente;
                i++;
            }
        }
        
        if(pos<0 || pos>=getTamanio()){
            throw new IllegalArgumentException("La posición está fuera de rango");
        }
        
        if(pos == 0){
            cabecera = cabecera.siguiente;
            if(cabecera != null)
                cabecera.anterior = null;
        }
        
        else if(pos == tamanio-1){
            NodoLista<T> nodo = obtenerNodo(pos-1);
            ultimo = nodo;
            nodo.siguiente = null;
        }
        else{
            NodoLista<T> nodoAnterior = obtenerNodo(pos-1);
            NodoLista<T> nodo = nodoAnterior.siguiente;
            NodoLista<T> nodoSiguiente = nodo.siguiente;
            nodoAnterior.siguiente = nodoSiguiente  ;
            nodoSiguiente.anterior = nodoAnterior;
        }
         tamanio--;
         return true;
    }
    
    
    @Override
    public int getTamanio() {
        return tamanio;
    }
    
    
}
