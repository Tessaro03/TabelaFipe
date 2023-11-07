package tabelaFipe.tabela.fipe.Principal;

import java.util.Comparator;
import java.util.Scanner;

import tabelaFipe.tabela.fipe.model.DadosMarca;
import tabelaFipe.tabela.fipe.model.DadosModelo;
import tabelaFipe.tabela.fipe.model.DadosAno;
import tabelaFipe.tabela.fipe.model.DadosModelos;
import tabelaFipe.tabela.fipe.model.DadosVeiculo;
import tabelaFipe.tabela.fipe.service.ConsumidorAPI;
import tabelaFipe.tabela.fipe.service.ConveterDados;

public class Principal {
    
    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";

    private Scanner teclado = new Scanner(System.in);
    private ConsumidorAPI consumidorAPI = new ConsumidorAPI(); 
    private ConveterDados conveterDados = new ConveterDados();
    
    public void exibirMenu(){
        System.out.println("--- MENU TABELA FIPE ---");
        System.out.println("- Motos\n- Carros\n- Caminhoes");
        System.out.print("Tipo de Veiculo: ");
        String tipoVeiculo = teclado.nextLine().toLowerCase();
        if (tipoVeiculo.contains("car")) {
            tipoVeiculo = "carros";
        } else if (tipoVeiculo.contains("mot")){
            tipoVeiculo = "motos";
        } else if (tipoVeiculo.contains("cam")){
            tipoVeiculo = "caminhoes";
        }

        var json = consumidorAPI.obterDados(ENDERECO + tipoVeiculo +"/marcas");
        var dadosMarcas = conveterDados.obterLista(json, DadosMarca.class);
        dadosMarcas.stream()
                    .sorted(Comparator.comparing(DadosMarca::marca))
                    .forEach(marca -> System.out.println("Cod:" + marca.codigo() + " - " +  marca.marca()));     
                    
        System.out.println("Codigo da Marca de Escolha: ");
        String codigoMarca = teclado.nextLine();
        var jsonMarca = consumidorAPI.obterDados(ENDERECO + tipoVeiculo + "/marcas/" + codigoMarca + "/modelos");
        var dadosModelos = conveterDados.obterDados(jsonMarca, DadosModelos.class);
        dadosModelos.modelos()
                    .forEach(veiculo -> System.out.println("Cod:" + veiculo.codigo() + " - " +  veiculo.nome()));
        
        System.out.println("Codigo do Modelo: ");
        String codigoModelo = teclado.nextLine();
        var jsonModelo = consumidorAPI.obterDados(ENDERECO + tipoVeiculo + "/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos");
        var dadosAno = conveterDados.obterLista(jsonModelo, DadosAno.class);
        dadosAno.forEach(ano -> System.out.println("Cod:" + ano.codigo() + " Ano:" + ano.nome()));

        System.out.println("Codigo do Ano: ");
        String codigoAno = teclado.nextLine();
        var jsonVeiculo = consumidorAPI.obterDados(ENDERECO + tipoVeiculo + "/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos/" + codigoAno);
        var veiculo = conveterDados.obterDados(jsonVeiculo, DadosVeiculo.class);
        System.out.println(
            "Valor: " + veiculo.valor() + 
            "\nMarca: " + veiculo.marca() +
            "\nModelo: " + veiculo.modelo() + 
            "\nAno Modelo: " + veiculo.anoModelo() +
            "\nCombustivel: " + veiculo.combustivel() +
            "\nMês Referencia: " + veiculo.mesReferencia() +
            "\nCodigo Fipe: " + veiculo.codigoFipe()
            );
    }
}
