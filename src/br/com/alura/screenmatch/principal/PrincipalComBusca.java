package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner teclado = new Scanner(System.in);
        System.out.println("Digite o nome do filme que deseja buscar:");
        String nomeDoFilme = teclado.nextLine();
        String endereco = "https://www.omdbapi.com/?t=" + nomeDoFilme.replaceAll(" ", "+") + "&apikey=8fa83e31";

        try {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("#1 Response body");
            String json = response.body();
            System.out.println(json);

            System.out.println("#2 Gson Builder");
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();
            TituloOmdb tituloOmdb = gson.fromJson(response.body(), TituloOmdb.class);
            System.out.println(tituloOmdb);

        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao converter JSON: " + e.getMessage());
        } finally {
            System.out.println("Uhul");
        }
        /*
        System.out.println("#2.5 Gson normal");
        Gson gsonNormal = new Gson();
        TituloOmdb tituloOmdbNormal = gsonNormal.fromJson(response.body(), TituloOmdb.class);
        System.out.println(tituloOmdbNormal);

        System.out.println("#3 Convertendo");
        Titulo meuTitulo = new Titulo(tituloOmdb);
        System.out.println(meuTitulo);
        System.out.println(meuTitulo.getNome());
        System.out.println(meuTitulo.getAnoDeLancamento());
        System.out.println(meuTitulo.getDuracaoEmMinutos() + " minutos");

         */
    }
}
