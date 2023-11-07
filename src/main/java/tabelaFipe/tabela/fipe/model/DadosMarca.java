package tabelaFipe.tabela.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosMarca(
    @JsonAlias("codigo") String codigo,
    @JsonAlias("nome") String marca
) {
    
}
