package br.com.ramos.unifor.jogos;

import java.util.ArrayList;
import java.util.List;

public class DamaGulosa {
    private static final int TAMANHO = 8;
    private char[][] tabuleiro = new char[TAMANHO][TAMANHO];

    public DamaGulosa() {
        // Inicializa tabuleiro
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                if ((i + j) % 2 == 0) {
                    if (i < 3) tabuleiro[i][j] = 'P'; // Peças pretas
                    else if (i > 4) tabuleiro[i][j] = 'B'; // Peças brancas
                    else tabuleiro[i][j] = ' '; // Espaço vazio
                } else {
                    tabuleiro[i][j] = ' '; // Espaço inválido (não usado)
                }
            }
        }
    }

    // Função gulosa para escolher a melhor jogada
    public int[] melhorJogadaGulosa(char jogador) {
        char oponente = (jogador == 'B') ? 'P' : 'B';
        int[] melhorJogada = null;
        int melhorPontuacao = -1;

        // Avalia todas as peças do jogador
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                if (Character.toUpperCase(tabuleiro[i][j]) == jogador) {
                    // Verifica movimentos possíveis
                    for (int[] movimento : movimentosPossiveis(i, j)) {
                        int pontuacao = avaliarJogada(i, j, movimento[0], movimento[1], jogador);
                        if (pontuacao > melhorPontuacao) {
                            melhorPontuacao = pontuacao;
                            melhorJogada = new int[]{i, j, movimento[0], movimento[1]};
                        }
                    }
                }
            }
        }

        return melhorJogada;
    }

    private List<int[]> movimentosPossiveis(int x, int y) {
        List<int[]> movimentos = new ArrayList<>();
        char peca = tabuleiro[x][y];
        boolean ehDama = Character.isUpperCase(peca);

        // Direções possíveis (depende se é dama ou peça normal)
        int[][] direcoes;
        if (ehDama) {
            direcoes = new int[][]{{1,1}, {1,-1}, {-1,1}, {-1,-1}};
        } else {
            if (Character.toUpperCase(peca) == 'B') {
                direcoes = new int[][]{{-1,1}, {-1,-1}}; // Brancas movem para cima
            } else {
                direcoes = new int[][]{{1,1}, {1,-1}}; // Pretas movem para baixo
            }
        }

        // Verifica movimentos simples e capturas
        for (int[] dir : direcoes) {
            int novoX = x + dir[0];
            int novoY = y + dir[1];

            if (novoX >= 0 && novoX < TAMANHO && novoY >= 0 && novoY < TAMANHO) {
                if (tabuleiro[novoX][novoY] == ' ') {
                    movimentos.add(new int[]{novoX, novoY});
                } else if (Character.toUpperCase(tabuleiro[novoX][novoY]) !=
                        Character.toUpperCase(peca)) {
                    // Possível captura
                    int capturaX = novoX + dir[0];
                    int capturaY = novoY + dir[1];
                    if (capturaX >= 0 && capturaX < TAMANHO && capturaY >= 0 && capturaY < TAMANHO &&
                            tabuleiro[capturaX][capturaY] == ' ') {
                        movimentos.add(new int[]{capturaX, capturaY});
                    }
                }
            }
        }

        return movimentos;
    }

    private int avaliarJogada(int xAtual, int yAtual, int xNovo, int yNovo, char jogador) {
        int pontuacao = 0;

        // Captura tem alta prioridade
        if (Math.abs(xNovo - xAtual) > 1) {
            pontuacao += 10; // Captura vale 10 pontos
        }

        // Promoção a dama
        if ((jogador == 'B' && xNovo == 0) || (jogador == 'P' && xNovo == TAMANHO-1)) {
            pontuacao += 5;
        }

        // Controle do centro
        if ((xNovo >= 3 && xNovo <= 4) && (yNovo >= 2 && yNovo <= 5)) {
            pontuacao += 2;
        }

        // Segurança - não deixar peça vulnerável
        if (!estaVulneravel(xNovo, yNovo, jogador)) {
            pontuacao += 1;
        }

        return pontuacao;
    }

    private boolean estaVulneravel(int x, int y, char jogador) {
        // Verifica se a peça pode ser capturada na próxima jogada
        char oponente = (jogador == 'B') ? 'P' : 'B';
        int[][] direcoesOponente = (oponente == 'P') ?
                new int[][]{{1,1}, {1,-1}} : new int[][]{{-1,1}, {-1,-1}};

        for (int[] dir : direcoesOponente) {
            int adjX = x + dir[0];
            int adjY = y + dir[1];
            int atrasX = x - dir[0];
            int atrasY = y - dir[1];

            if (adjX >= 0 && adjX < TAMANHO && adjY >= 0 && adjY < TAMANHO &&
                    atrasX >= 0 && atrasX < TAMANHO && atrasY >= 0 && atrasY < TAMANHO) {
                if (Character.toUpperCase(tabuleiro[adjX][adjY]) == oponente &&
                        tabuleiro[atrasX][atrasY] == ' ') {
                    return true;
                }
            }
        }

        return false;
    }
}