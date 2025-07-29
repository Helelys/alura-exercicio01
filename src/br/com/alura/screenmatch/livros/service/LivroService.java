package br.com.alura.screenmatch.livros.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LivroService {

    public void mensagemSaudacao() {
        System.out.println("Menu:\n" +
                "1- Pesquisar livro\n" +
                "2- Listar livros\n" +
                "3- Listar autores registrados\n" +
                "4- Listar autores vivos no ano inserido\n" +
                "5- Insira um idioma\n" +
                "6- Sair\n");
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
