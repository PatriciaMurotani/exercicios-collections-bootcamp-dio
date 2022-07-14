package br.com.dio.collections.set;

import java.util.*;

/*Dadas as seguintes informações sobre minhas séries favoritas, crie um conjunto e ordene este conjunto exibindo:
(nome - genero - tempo de episódio)

Serie 1 = nome: Stranger Things, genero: ficção, tempo: 50
Serie 2 = nome: Greys Anatomy, genero: drama, tempo: 45
Serie 3 = nome: La Casa De Papel, genero: suspense, tempo: 60
 */
public class ExOrdenacaoSet {
    public static void main(String[] args) {

        System.out.println("Ordem aleatória: ");
        Set<Serie> minhasSeries = new HashSet<>() {{
            add(new Serie("Stranger Things", "ficcao", 50));
            add(new Serie("Greys Anatomy", "drama", 45));
            add(new Serie("La Casa De Papel", "suspense", 50));
        }};
        for (Serie serie : minhasSeries) System.out.println(serie.getNome() + " - " + serie.getGenero() + " - " + serie.getTempoEpisodio());

        System.out.println("Ordem de inserção: ");
        Set<Serie> minhasSeries1 = new LinkedHashSet<>() {{
            add(new Serie("Stranger Things", "ficcao", 50));
            add(new Serie("Greys Anatomy", "drama", 45));
            add(new Serie("La Casa De Papel", "suspense", 50));
        }};
        for (Serie serie : minhasSeries1) System.out.println(serie.getNome() + " - " + serie.getGenero() + " - " + serie.getTempoEpisodio());

        System.out.println("Ordem natural(Tempo de Episódio): ");
        Set<Serie> minhasSeries2 = new TreeSet<>(minhasSeries1);
        for (Serie serie : minhasSeries2) System.out.println(serie.getNome() + " - " + serie.getGenero() + " - " + serie.getTempoEpisodio());

        System.out.println("Ordem de Nome/Gênero/TempoEpisodio: ");
        Set<Serie> minhasSeries3 = new TreeSet<>(new ComparatorNomeGeneroTempoEpisodio());
        minhasSeries3.addAll(minhasSeries);
        for (Serie serie : minhasSeries3) System.out.println(serie.getNome() + " - " + serie.getGenero() + " - " + serie.getTempoEpisodio());
    }
}

class Serie implements Comparable<Serie> {
    private String nome;
    private String genero;
    private Integer tempoEpisodio;

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public Integer getTempoEpisodio() {
        return tempoEpisodio;
    }

    public Serie(String nome, String genero, Integer tempoEpisodio) {
        this.nome = nome;
        this.genero = genero;
        this.tempoEpisodio = tempoEpisodio;
    }

    @Override
    public String toString() {
        return "{" +
                "nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", tempoEpisodio=" + tempoEpisodio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Serie serie = (Serie) o;
        return Objects.equals(nome, serie.nome) && Objects.equals(genero, serie.genero) && Objects.equals(tempoEpisodio, serie.tempoEpisodio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, genero, tempoEpisodio);
    }


    @Override
    public int compareTo(Serie serie) {
        int tempoEpisodio = Integer.compare(this.getTempoEpisodio(), serie.getTempoEpisodio());
        if(tempoEpisodio != 0) return tempoEpisodio;

        return this.getGenero().compareTo(serie.getGenero());
    }
}

class ComparatorNomeGeneroTempoEpisodio implements Comparator<Serie>{

    @Override
    public int compare(Serie s1, Serie s2) {
        int nome = s1.getNome().compareTo(s2.getNome());
        if(nome != 0) return nome;

        int genero = s1.getGenero().compareTo(s2.getGenero());
        if(genero != 0) return genero;

        return Integer.compare(s1.getTempoEpisodio(), s2.getTempoEpisodio());
    }
}
