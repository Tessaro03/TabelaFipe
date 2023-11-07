package tabelaFipe.tabela.fipe.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosModelos(
    @JsonAlias("modelos") List<DadosModelo> modelos,
    @JsonAlias("anos") List<DadosAno> anos
) {

    
    
}
