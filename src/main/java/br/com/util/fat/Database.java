package br.com.util.fat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;

public class Database {
    public static final int TAMANHO_MINIMO_PALAVRA = 2;
    private static Map<String, List<String>> data = Map.ofEntries(
            entry("Investments", Arrays.asList("TESTE")),
            entry("Health", Arrays.asList("FARMACIAS", "FARMACIA", "FARMA")),
            entry("Lunch", Arrays.asList("LINDOIA", "BEABA", "MAHL")),
            entry("Restaurant", Arrays.asList("PANORAMA", "MAMGIARE", "SILVA", "PILOTA", "LUG", "INTERVALO")),
            entry("Food", Arrays.asList("ZAFFARI", "SUPERMERCADO", "MERCADO", "BIG", "DISUL", "OASIS")),
            entry("Transport", Arrays.asList("UBER", "POST", "POP", "CABIFY", "PASSAGEM", "ATP", "ABASTECEDORA")),
            entry("Clothes", Arrays.asList("RIACHUELO", "RENNER", "NETSHOES", "CALCADOS")),
            entry("Brenda", Arrays.asList("ESPACOE")),
            entry("Telephone ", Arrays.asList("CLARO", "RECARGAPAY")),
            entry("Cinema", Arrays.asList("INGRESSO", "GNC", "CINEMARK", "CINEMA")),
            entry("Junky food", Arrays.asList("AMERICANAS", "GELB", "PEPPERONI", "PIZZA", "PIZ", "PRADO", "IFOOD", "NICOLASCECCHIN", "CHURR")),
            entry("Work", Arrays.asList()),
            entry("Credit card", Arrays.asList()),
            entry("Gift", Arrays.asList("BUSER")),
            entry("Home", Arrays.asList()),
            entry("Extras", Arrays.asList("AME", "NETFLIX", "AMAZONPRIMEBR", "SPOTIFY", "AMAZON")),
            entry("Games", Arrays.asList("SONYPLAYSTATN", "SONY")),
            entry("Brenda party", Arrays.asList("PAGBRUNONUNESCICULAN")),
            entry("Appartment", Arrays.asList("CASSOL", "NOVO", "WEBCONTINENTAL"))

    );

    private static List<String> getMainWords(String category) {
        return data.get(category);
    }

    private static String getCategoryFromWord(String word) {
        Optional<Map.Entry<String, List<String>>> category =
                data.entrySet()
                        .stream()
                        .filter((key) ->
                                key.getValue().contains(word.toUpperCase()))
                        .findFirst();
        return category.isEmpty() ? null : category.get().getKey();
    }

    public static String getCategoryFromFrase(String frase) {
        if (frase == null) {
            return null;
        }
        String category = frase.replaceAll("[^a-zA-Z ]", " ");
        Optional<String> optional =
                Arrays.stream(category.split(" "))
                        .map(s -> {
                            String categoryAux = null;
                            if (s.length() > TAMANHO_MINIMO_PALAVRA) {
                                categoryAux = getCategoryFromWord(s);
                            }
                            return categoryAux;
                        })
                        .filter(s -> s != null)
                        .findFirst();
        return optional.isEmpty() ? null : optional.get();
    }
}
