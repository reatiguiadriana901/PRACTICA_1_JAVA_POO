package CajeroAutomatico;

import java.util.Scanner;

public class GestionCajero {
    private Scanner scanner = new Scanner(System.in);
    private CuentaBancaria cuenta;
    private Banco banco;

    public GestionCajero(Banco banco) {
        this.banco = banco;
    }

    private void crearCuenta() {
        System.out.print("Titular: ");
        String titular = scanner.nextLine();

        if (banco.buscarCuenta(titular) != null) {
            System.out.println("Ya existe una cuenta con ese titular");
            return;
        }

        System.out.print("Saldo inicial: ");
        double saldo = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Cree su PIN: ");
        String pin = scanner.nextLine();

        banco.agregarCuenta(new CuentaBancaria(titular, saldo, pin));
        System.out.println("Cuenta creada con éxito");
    }

    public boolean validandoAcceso() {
        System.out.print("Titular: ");
        String titular = scanner.nextLine();

        if (banco.buscarCuenta(titular) == null) {
            System.out.println("La cuenta no existe");
            return false;
        }

        for (int i = 1; i <= 3; i++) {
            System.out.print("Ingrese su PIN: ");
            String pin = scanner.nextLine();

            if (banco.validarPin(titular, pin)) {
                cuenta = banco.buscarCuenta(titular);
                return true;
            } else {
                System.out.println("PIN incorrecto");
            }
        }
        System.out.println("Cuenta bloqueada");
        return false;
    }

    public void consultarSaldo(){
        System.out.println("Su saldo actual es: " + cuenta.consultarSaldo());
    }

    public void retirarDinero(){
        System.out.println("Cuanto desea retirar");
        double cantidadRetirar = scanner.nextDouble();
        if (cuenta.retirar(cantidadRetirar)){
            System.out.println("Retiro realizado correctamente");
        }else{
            System.out.println("Ha ocurrido un error");
        }
    }

    public void depositarDinero(){
        System.out.println("Cuanto desea depositar");
        double cantidadDepositar = scanner.nextDouble();
        if (cantidadDepositar > 0) {
            cuenta.depositar(cantidadDepositar);
            System.out.println("Depósito exitoso.");
        }
    }

    public void tranferirDinero(){
        System.out.print("Ingrese el titular de la cuenta de destino: ");
        String nombreTitularReceptor = scanner.nextLine();

        CuentaBancaria destino = banco.buscarCuenta(nombreTitularReceptor);

        if (destino == null) {
            System.out.println("La cuenta no ha sido encontrada");
            return;
        }

        System.out.print("Ingrese la cantidad a transferir: ");
        double cantidad = scanner.nextDouble();
        scanner.nextLine();

        if (cuenta.transferir(destino, cantidad)) {
            System.out.println("Transferencia completada con éxito");
        } else {
            System.out.println("Fondos insuficientes");
        }
    }

    public void historialMovimientos(){
        int i=0;
        if (cuenta.getMovimientos().size() == 0) {
            System.out.println("No hay movimientos todavía.");
            return;
        }
        while(i < cuenta.getMovimientos().size()){
            System.out.println(cuenta.getMovimientos().get(i));
            i++;
        }
    }

    public void accesoPermitido() {
        int opciones =0;
        while (opciones!=6){
            System.out.println("\n===== CAJERO AUTOMÁTICO =====");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Retirar dinero");
            System.out.println("3. Depositar dinero");
            System.out.println("4. Transferir");
            System.out.println("5. Movimientos");
            System.out.println("6. Salir");
            System.out.print("Elija una opción: ");
            opciones = scanner.nextInt();
            scanner.nextLine();
            switch (opciones){
                case 1: consultarSaldo();
                    break;

                case 2: retirarDinero();
                    break;

                case 3: depositarDinero();
                    break;

                case 4: tranferirDinero();
                    break;

                case 5: historialMovimientos();
                    break;

                case 6:
                    System.out.println("Gracias por usar nuestros servicios,vuelve pronto");
                    break;

                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }

    public void iniciar() {
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("\n===== BIENVENIDO =====");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Crear cuenta");
            System.out.println("3. Salir");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    if (validandoAcceso()) {
                        accesoPermitido();
                    }
                    break;
                case 2:
                    crearCuenta();
                    break;
                case 3:
                    System.out.println("Hasta pronto");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
        scanner.close();
    }
}

