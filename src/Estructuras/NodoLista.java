/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import java.util.Objects;


public class NodoLista <T>{
    protected T valor;
    protected NodoLista <T> anterior;
    protected NodoLista <T> siguiente;

    public NodoLista(T valor) {
        this.valor = valor;
        this.anterior = null;
        this.siguiente = null;
    }

    @Override
    public String toString() {
        return valor+"";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.valor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NodoLista<?> other = (NodoLista<?>) obj;
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        return true;
    }

    
    
}
