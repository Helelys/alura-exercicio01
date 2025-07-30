package br.com.alura.screenmatch.livros.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class LivroService {

    public void mensagemSaudacao() throws IOException, InterruptedException {
        System.out.println("Menu:\n" +
                "1- Pesquisar livro\n" +
                "2- Listar livros\n" +
                "3- Listar autores registrados\n" +
                "4- Listar autores vivos no ano inserido\n" +
                "5- Insira um idioma\n" +
                "6- Sair\n");

        executaMetodos();
    }

    public void executaMetodos() throws IOException, InterruptedException {
        Scanner teclado = new Scanner(System.in);
        int teste = 0;
        do {
            System.out.println("Digite o número da opção desejada: ");
            teste = teclado.nextInt();
            teclado.nextLine(); // Limpa o buffer
            if (teste == 1) {
                System.out.println("Digite o nome do livro que deseja pesquisar: ");
                String endereco = teclado.nextLine();
                String resultado = consumoDaApi(endereco);
                System.out.println(resultado);
            } else if (teste == 2) {
                System.out.println("Não tá pronto.");
            } else if (teste == 3) {
                System.out.println("Não tá pronto.");
            } else if (teste == 4) {
                System.out.println("Não tá pronto.");
            } else if (teste == 5) {
                System.out.println("Não tá pronto.");
            } else if (teste == 6) {
                System.out.println("Saindo...");
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        } while (teste != 6);
        teclado.close();
    }



    public String consumoDaApi(String endereco) throws IOException, InterruptedException {

        var url = "https://gutendex.com/books/?search=" + endereco.replaceAll(" ", "+");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

}
