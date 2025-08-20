package br.com.alura.screenmatch.principal;

import java.io.*;

public class teste {
    public static void main(String[] args) throws IOException {
        File file = new File("filme.txt");

        FileWriter escrever = new FileWriter(file);
        escrever.write("O filme acima");
        escrever.close();

        FileReader reader = new FileReader(file);
        int data = reader.read();
        while (data != -1) {
            System.out.print((char) data);
            data = reader.read();
        }
        reader.close();
    }
}
