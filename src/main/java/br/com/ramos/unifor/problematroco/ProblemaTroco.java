package br.com.ramos.unifor.problematroco;
import java.util.ArrayList;
import java.util.List;

public class ProblemaTroco {
    public static List<Integer> calcularTroco(int valor, int[] moedas) {
        List<Integer> resultado = new ArrayList<>();

        // Ordenamos as moedas em ordem decrescente para abordagem gulosa
        // Assumindo que as moedas já estão ordenadas como no exemplo (25, 10, 5, 1)

        int i = 0;
        while (valor > 0 && i < moedas.length) {
            if (moedas[i] <= valor) {
                resultado.add(moedas[i]);
                valor -= moedas[i];
            } else {
                i++;
            }
        }

        return resultado;
    }

    public static void main(String[] args) {
        int[] moedas = {25, 10, 5, 1};
        int valor = 92;

        List<Integer> troco = calcularTroco(valor, moedas);

        System.out.println("Troco para " + valor + " centavos:");
        for (int moeda : troco) {
            System.out.print(moeda + " ");
        }

        System.out.println("\nTotal de moedas: " + troco.size());
    }
}
