package CajeroAutomatico;

import java.util.ArrayList;

public abstract class Cuenta {
    protected String titular;
    protected double saldo;
    protected String pin;
    protected ArrayList<String> movimientos = new ArrayList<String>();



    public Cuenta (String titular, double saldo, String pin) {
        this.titular = titular;
        this.saldo = saldo;
        this.pin = pin;
    }
}
