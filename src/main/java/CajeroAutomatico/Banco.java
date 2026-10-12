package CajeroAutomatico;

import java.util.ArrayList;

public class Banco {
    private ArrayList<CuentaBancaria> cuentas = new ArrayList<>();

    public Banco() {
        cargarCuentas();
    }

    public boolean validarPin(String titular, String pin) {
        CuentaBancaria c = buscarCuenta(titular);
        if (c == null) {
            return false;
        }
        return c.validarPin(pin);    }


    protected void cargarCuentas() {
            cuentas.add(new CuentaCorriente("Carlos", 200000, "9876"));
            cuentas.add(new CuentaAhorro("María", 150000, "4321"));
            cuentas.add(new CuentaCorriente("Laura", 50000, "1111"));
            cuentas.add(new CuentaAhorro("Pedro", 0, "2222"));
        }


    public void agregarCuenta(CuentaBancaria cuenta) {
        cuentas.add(cuenta);
    }

    public CuentaBancaria buscarCuenta(String titular) {
        for (CuentaBancaria c : cuentas) {
            if (c.getTitular().equalsIgnoreCase(titular)) {
                return c;
            }
        }
        return null;
    }
}