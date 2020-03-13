package br.com.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static final String SEPARADOR_VALOR = ".";
    public static final String SEPARADOR_ORIGEM_VALOR = ",";
    private static final String SEPARADOR = "\t";
    public static final String INVALID_STRING = "INVALID";

    public static void main(String[] args) {
        Database.getCategoryFromFrase("uber");
        List<String> list = new ArrayList<>();

        try (Stream<String> stream = Files.lines(
                Paths.get(getPathFromProject("/src/main/resources/fatura_santander.csv")))) {
            stream
                    .forEach(Main::validaDados);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void validaDados(String dados) {
        String aux = dados.trim();
        String[] listAux = aux.split(" ");

        System.out.println(validaData(listAux, "/2020")
                + validaValor(listAux)
                + validaDescricao(listAux)
                + validaCategoria(dados));
    }

    private static String validaCategoria(String dados) {
        String aux = Database.getCategoryFromFrase(dados);
        aux = (aux == null ? INVALID_STRING : aux);
        return SEPARADOR + aux;
    }

    private static String validaData(String[] listAux, String ano) {
        return listAux[0] + ano;
    }

    private static String validaValor(String[] listAux) {
        return SEPARADOR + listAux[listAux.length - 1].replaceAll(SEPARADOR_ORIGEM_VALOR, SEPARADOR_VALOR);
    }

    private static String validaDescricao(String[] listAux) {
        StringBuilder stringBuilder = new StringBuilder(SEPARADOR);
        IntStream.range(1, listAux.length - 1)
                .forEach(i -> stringBuilder.append(" ").append(listAux[i]));
        return stringBuilder.toString();
    }

    private static String getPathFromProject(String fileProjectPath) {
        return new File("").getAbsolutePath() + fileProjectPath;
    }
}
