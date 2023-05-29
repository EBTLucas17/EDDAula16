
package com.mycompany.aula16;

import java.util.Deque;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

public class PostoSaudeAtendimento {
    public static void main(String[] args) {
        Deque<Pessoa> filaAtendimento = new LinkedList<>();
        List<Pessoa> naoAtendidos = new ArrayList<>();

        Random random = new Random();

        // Gerar grupos de 10 pessoas
        for (int grupo = 0; grupo < 10; grupo++) {
            List<Pessoa> pessoas = new ArrayList<>();

            // Proporção de pessoas por grupo
            int necessidadesEspeciais = 1;
            int gestantes = 1;
            int lactantes = 1;
            int acima60 = 3;
            int normais = 5;

            // Gerar pessoas aleatórias no grupo
            for (int i = 0; i < 10; i++) {
                int idade = random.nextInt(90) + 10; // Idade entre 10 e 99
                boolean gestante = random.nextBoolean();
                boolean lactante = random.nextBoolean();
                boolean necessidadeEspecial = random.nextBoolean();

                Pessoa pessoa = new Pessoa(i, "Feminino", idade, gestante, lactante, necessidadeEspecial);
                pessoas.add(pessoa);
            }

            pessoas.sort(Comparator.comparingInt(p -> {
                if (p.hasNecessidadeEspecial()) {
                    return 2;
                } else if (p.isGestante() || p.isLactante()) {
                    return 3;
                } else if (p.getIdade() >= 60) {
                    return 1;
                } else {
                    return 0;
                }
            }));

            for (Pessoa pessoa : pessoas) {
                filaAtendimento.addLast(pessoa);
            }

            if (!filaAtendimento.isEmpty()) {
                Pessoa pessoaAtendida = filaAtendimento.removeFirst();
                System.out.println("Pessoa atendida: " + pessoaAtendida.getId());
            }

            naoAtendidos.addAll(filaAtendimento);
            filaAtendimento.clear();
        }

        System.out.println("Pessoas não atendidas:");
        for (Pessoa pessoa : naoAtendidos) {
            System.out.println("ID: " + pessoa.getId());
        }
    }
}
