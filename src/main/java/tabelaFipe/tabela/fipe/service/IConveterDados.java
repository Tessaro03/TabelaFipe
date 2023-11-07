package tabelaFipe.tabela.fipe.service;

import java.util.List;

public interface IConveterDados {

    <T> T obterDados(String json, Class<T> classe);

    <T> List<T> obterLista(String json, Class<T> classe);
}
