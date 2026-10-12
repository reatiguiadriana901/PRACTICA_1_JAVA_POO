package CajeroAutomatico;

import java.util.ArrayList;

public class CuentaBancaria extends Cuenta {

    public CuentaBancaria(String titular, double saldo, String pin) {
        super(titular, saldo, pin);
    }



    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getPin() {
        return pin;
    }

    public ArrayList<String> getMovimientos() {
        return movimientos;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }


    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
            String texto = "Deposito: "+ "$"+monto;
            registrarMovimiento(texto);
        }
    }

    public boolean retirar(double monto){
        if (monto>0 && monto<=saldo){
            saldo -= monto;
            String texto = "Retiro: " + "-$"+monto;
            registrarMovimiento(texto);
            return true;
        }
        return false;
    }

    public boolean transferir(CuentaBancaria cuentaDestino, double monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
            cuentaDestino.depositar(monto);
            String texto = "Transferencia: " + "$"+monto;
            registrarMovimiento(texto);
            return true;
        }
        return false;
    }

    protected void registrarMovimiento(String texto) {
        movimientos.add(texto);
        if (movimientos.size() > 5) {
            movimientos.remove(0);
        }
    }

    public double consultarSaldo(){
        return saldo;
    }

    public boolean validarPin(String pinIngresado) {
        return this.pin.equals(pinIngresado);

    }}