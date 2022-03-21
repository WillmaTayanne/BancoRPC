package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    //private Map<String, Double> saldoContas;

    private List<Conta> contas = new ArrayList<>();


    public BancoServiceServer() throws RemoteException {
        contas.add(new Conta("1",100.0));
        contas.add(new Conta("2",156.0));
        contas.add(new Conta("3",950.0));

        //saldoContas = new HashMap<>();

    }

    @Override
    public double saldo(String conta) throws RemoteException {
        List<Conta> contas = this.contas.stream().filter(x -> x.numero.equals(conta)).toList();
        if(contas.size() < 1 )
            throw new RemoteException("Conta não encontrada ou Conta Invalida");

        return contas.get(0).saldo;

    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return  this.contas.size();
    }

    @Override
    public void adicionarConta(String numero, double saldo) throws RemoteException  {
        List<Conta> contas = this.contas.stream().filter(x -> x.numero.equals(numero)).toList();
        if(contas.size() > 0)
            throw new RemoteException("Conta já existente!");
       this.contas.add(new Conta(numero,saldo));
    }

}
