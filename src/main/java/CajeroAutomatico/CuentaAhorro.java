package CajeroAutomatico;

public class CuentaAhorro extends CuentaBancaria{

    private double tasaInteres;

    public CuentaAhorro(String titular, double saldo, String pin) {
        super(titular, saldo, pin);
        tasaInteres = 0.02;
    }

    @Override
    public void depositar(double monto) {
        if (monto > 0) {
            super.depositar(monto);
            double interes = monto * tasaInteres;
            saldo += interes;
        }
    }

}
