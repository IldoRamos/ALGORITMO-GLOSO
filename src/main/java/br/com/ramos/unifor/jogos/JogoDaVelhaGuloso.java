package br.com.ramos.unifor.jogos;

public class JogoDaVelhaGuloso {
    private char[][] tabuleiro = new char[3][3];

    public JogoDaVelhaGuloso() {
        // Inicializa tabuleiro vazio
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = '-';
            }
        }
    }

    // Função gulosa: escolhe a jogada que completa uma linha, coluna ou diagonal
    // ou bloqueia o oponente de completar
    public int[] fazerJogadaGulosa(char jogador) {
        char oponente = (jogador == 'X') ? 'O' : 'X';

        // 1. Verifica se pode ganhar na próxima jogada
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == '-') {
                    tabuleiro[i][j] = jogador;
                    if (verificarVitoria(jogador)) {
                        tabuleiro[i][j] = '-'; // Desfaz a jogada de teste
                        return new int[]{i, j};
                    }
                    tabuleiro[i][j] = '-';
                }
            }
        }

        // 2. Verifica se precisa bloquear o oponente
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == '-') {
                    tabuleiro[i][j] = oponente;
                    if (verificarVitoria(oponente)) {
                        tabuleiro[i][j] = '-'; // Desfaz a jogada de teste
                        return new int[]{i, j};
                    }
                    tabuleiro[i][j] = '-';
                }
            }
        }

        // 3. Estratégia gulosa: escolhe o centro ou cantos primeiro
        if (tabuleiro[1][1] == '-') return new int[]{1, 1};

        int[][] cantos = {{0,0}, {0,2}, {2,0}, {2,2}};
        for (int[] canto : cantos) {
            if (tabuleiro[canto[0]][canto[1]] == '-') {
                return canto;
            }
        }

        // 4. Escolhe qualquer posição disponível
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == '-') {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{-1, -1}; // Jogo acabou
    }

    private boolean verificarVitoria(char jogador) {
        // Verifica linhas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador) {
                return true;
            }
        }

        // Verifica colunas
        for (int j = 0; j < 3; j++) {
            if (tabuleiro[0][j] == jogador && tabuleiro[1][j] == jogador && tabuleiro[2][j] == jogador) {
                return true;
            }
        }

        // Verifica diagonais
        if (tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) {
            return true;
        }
        if (tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador) {
            return true;
        }

        return false;
    }
}