package com.trybe.conversorcsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Descrição da classe.
 **/
public class Conversor {

  /**
   * Função utilizada apenas para validação da solução do desafio.
   *
   * @param args Não utilizado.
   * @throws IOException Caso ocorra algum problema ao ler os arquivos de entrada ou gravar os
   *         arquivos de saída.
   */
  public static void main(String[] args) throws IOException {
    File pastaDeEntradas = new File("./entradas/");
    File pastaDeSaidas = new File("./saidas/");
    new Conversor().converterPasta(pastaDeEntradas, pastaDeSaidas);
  }

  /**
   * Converte todos os arquivos CSV da pasta de entradas. Os resultados são gerados na pasta de
   * saídas, deixando os arquivos originais inalterados.
   *
   * @param pastaDeEntradas Pasta contendo os arquivos CSV gerados pela página web.
   * @param pastaDeSaidas Pasta em que serão colocados os arquivos gerados no formato requerido pelo
   *        subsistema.
   *
   * @throws IOException Caso ocorra algum problema ao ler os arquivos de entrada ou gravar os
   *         arquivos de saída.
   */
  public void converterPasta(File pastaDeEntradas, File pastaDeSaidas) throws IOException {
    // TODO: Implementar.
    File[] arquivosEntrada = pastaDeEntradas.listFiles();

    for (File arquivo : arquivosEntrada) {
      FileReader leitorArquivo = new FileReader(arquivo);
      BufferedReader bufferedLeitor = new BufferedReader(leitorArquivo);

      String linhas = bufferedLeitor.readLine();

      while (linhas != null) {
        linhas = bufferedLeitor.readLine();
        if (linhas != null) {
          String[] linha = linhas.split(",");
          System.out.println(converteCpf(linha[3]));
        }
      }

      fecharArquivo(leitorArquivo, bufferedLeitor);
    }

  }

  public String nomeUpperCase(String nome) {
    return nome.toUpperCase();
  }

  /**
   * Metódo para converter a data no formato ISO-8601: aaaa-mm-dd.
   **/
  public String converteData(String data) {
    String[] arrayData = data.split("/");
    String ano = arrayData[2];
    String mes = arrayData[1];
    String dia = arrayData[0];
    return ano + "-" + mes + "-" + dia;
  }

  /**
   * Metódo para converter o CPF.
   **/
  public String converteCpf(String cpf) {
    String part1 = cpf.substring(0, 3);
    String part2 = cpf.substring(3, 6);
    String part3 = cpf.substring(6, 9);
    String part4 = cpf.substring(9, 11);
    return part1 + "." + part2 + "." + part3 + "-" + part4;
  }

  private void fecharArquivo(FileReader fileReader, BufferedReader bufferedReader) {
    try {
      fileReader.close();
      bufferedReader.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
