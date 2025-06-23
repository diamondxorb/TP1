package main;

import controller.Conexao;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e){
            e.printStackTrace();
        }
        Conexao.inicializarBanco();



        Conexao.inicializarTabelaAgendamento();
        Conexao.atualizarBanco();
        new view.TelaPrincipal();

    }
}
