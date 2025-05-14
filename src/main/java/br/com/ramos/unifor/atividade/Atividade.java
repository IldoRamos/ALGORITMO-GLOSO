package br.com.ramos.unifor.atividade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Atividade {
    int inicio;
    int fim;
    int id;

    public Atividade(int id, int inicio, int fim) {
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
    }

    @Override
    public String toString() {
        return "Atividade " + id + " (" + inicio + "-" + fim + ")";
    }
}
