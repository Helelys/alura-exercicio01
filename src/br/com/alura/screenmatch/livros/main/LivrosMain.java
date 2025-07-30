package br.com.alura.screenmatch.livros.main;

import br.com.alura.screenmatch.livros.service.LivroService;

import java.io.IOException;

public class LivrosMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        LivroService livroService = new LivroService();
        livroService.mensagemSaudacao();
    }
}
