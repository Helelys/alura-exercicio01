package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import br.com.alura.screenmatch.modelos.exception.ErroDeConversaoException;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException, ErroDeConversaoException {

        Scanner teclado = new Scanner(System.in);
        String nomeDoFilme = "";
        List<Titulo> listaDeTitulos = new ArrayList<>();

        while (!nomeDoFilme.equalsIgnoreCase("sair")) {
            System.out.println("Digite o nome do filme que deseja buscar:");
            nomeDoFilme = teclado.nextLine();

            if (nomeDoFilme.equalsIgnoreCase("sair")) {
                System.out.println("Saindo do programa.");
                break;
            }

            String endereco = "https://www.omdbapi.com/?t=" + nomeDoFilme.replaceAll(" ", "+") + "&apikey=8fa83e31";

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();

                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                Gson gsonNormal = new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                        .create();
                TituloOmdb tituloOmdb = gsonNormal.fromJson(response.body(), TituloOmdb.class);

                Titulo meuTitulo = new Titulo(tituloOmdb);
                System.out.println(meuTitulo);

                listaDeTitulos.add(meuTitulo);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro ao converter JSON: " + e.getMessage());
            } catch (ErroDeConversaoException e) {
                System.out.println("Erro de conversão, pois o ano não tem 4 dígitos." + e.getMessage());
            }
        }
        System.out.println("Finalizado. Lista de filmes: " + listaDeTitulos.stream()
                .map(Titulo::getNome)
                .toList());

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        File file = new File("filmes.json");
        try (FileWriter writer = new FileWriter(file)) {
            String json = gson.toJson(listaDeTitulos);
            writer.write(json);
            System.out.println("Arquivo JSON criado com sucesso: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Erro ao escrever o arquivo JSON: " + e.getMessage());
        } finally {
            teclado.close();
        }
    }
}
