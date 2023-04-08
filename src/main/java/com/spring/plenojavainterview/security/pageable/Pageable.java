package com.spring.plenojavainterview.security.pageable;

import com.spring.plenojavainterview.util.ListaUtils;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pageable<T> {

    private String filtro = "";

    private int pageSize = 0;

    private int pageNumber = 0;

    private List<T> lista;


    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<T> getLista() {
        if(Objects.isNull(lista)){
            return new ArrayList<>();
        }
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }

    public Pageable(String filtro, int pageSize, int pageNumber, List<T> lista) {
        this.filtro = filtro;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.lista = lista;
    }

    public Pageable(){

    }
}
