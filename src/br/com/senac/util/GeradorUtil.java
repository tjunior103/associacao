/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 *
 * @author silvio.junior
 */
public class GeradorUtil {
    
    public static Date converterParaData(String data){
        SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formatado.parse(data);
        } catch (ParseException ex) {
            System.out.println("Erro ao converter data " + ex.getMessage());
        }
            return null;
    }

    public static String gerarTelefoneFixo() {
        return "(48)3" + gerarNumero(3) + "-" + gerarNumero(4);
    }

    public static String gerarTelefoneCelular() {
        return "(48)9" + gerarNumero(4) + "-" + gerarNumero(4);
    }

    public static String gerarNumero(int qtde) {
        String senha = "";
        int numero;
        for (int i = 0; i < qtde; i++) {
            numero = (int) (Math.random() * 10);
            senha = senha + numero;
        }
        return senha;
    }

    public static String gerarIdade() {
        List<Integer> numeros = new ArrayList<>();
        for (int i = 15; i <= 60; i++) {
            numeros.add(i);
        }
        Collections.shuffle(numeros);
        return Integer.toString(numeros.get(0));
    }

    public static String gerarNome() {
        List<String> nomes = Arrays.asList("Mariele", "Matheus",
                "Vinicíus Alves", "Vitor", "Alisson", "Jonatas",
                "Jeremias", "Bruno", "Karen", "Brayan",
                "Vinícius da Silva", "Jeferson", "Prego");
        Collections.shuffle(nomes);
        return nomes.get(0);
    }

    public static int ultimaPosicaoList(List pregos) {
        return pregos.size() - 1;
    }

    public static String gerarSenha() {
        String[] letras = {"a", "b",
            "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
            "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        int tamanho = letras.length;
        String senha = "";
        int indice;
        for (int i = 0; i < 4; i++) {
            indice = (int) (Math.random() * tamanho);
            senha = senha + letras[indice];
        }
        return senha;
    }

    public static String gerarDataHoraAtual() {
        LocalDateTime dataAtual = LocalDateTime.now();
        DateTimeFormatter conversorData = DateTimeFormatter
                .ofPattern("dd/MM/yyyy HH:mm");
        return conversorData.format(dataAtual);
    }
}
