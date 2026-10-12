package CajeroAutomatico;

public class CuentaCorriente extends CuentaBancaria{

    protected double limiteSobregiro;

    public CuentaCorriente(String titular, double saldo, String pin) {
        super(titular, saldo, pin);
        limiteSobregiro = 100000;
    }

    @Override
    public boolean retirar(double monto) {
        if (monto > 0 && monto <= saldo + limiteSobregiro) {
            saldo -= monto;
            String texto = "Retiro: -$" + monto;
            registrarMovimiento(texto);
            return true;
        }
        return false;
    }
}
