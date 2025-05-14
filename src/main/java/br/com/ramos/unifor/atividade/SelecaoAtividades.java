package br.com.ramos.unifor.atividade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SelecaoAtividades {
    public static List<Atividade> selecionarAtividades(Atividade[] atividades) {
        // Ordena as atividades por tempo de término
        Arrays.sort(atividades, Comparator.comparingInt(a -> a.fim));

        List<Atividade> selecionadas = new ArrayList<>();
        selecionadas.add(atividades[0]);
        int ultimoFim = atividades[0].fim;

        for (int i = 1; i < atividades.length; i++) {
            if (atividades[i].inicio >= ultimoFim) {
                selecionadas.add(atividades[i]);
                ultimoFim = atividades[i].fim;
            }
        }

        return selecionadas;
    }

    public static void main(String[] args) {
        // Exemplo do slide
        Atividade[] atividades = {
                new Atividade(1, 1, 3),
                new Atividade(2, 2, 5),
                new Atividade(3, 4, 7),
                new Atividade(4, 1, 8),
                new Atividade(5, 5, 9),
                new Atividade(6, 8, 10),
                new Atividade(7, 9, 11),
                new Atividade(8, 11, 14),
                new Atividade(9, 13, 16)
        };

        List<Atividade> selecionadas = selecionarAtividades(atividades);

        System.out.println("Atividades selecionadas:");
        for (Atividade a : selecionadas) {
            System.out.println(a);
        }
        System.out.println("Total: " + selecionadas.size() + " atividades");
    }
}
