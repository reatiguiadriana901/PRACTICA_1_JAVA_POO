package AgendaContactos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Agenda {
    // Cambia este número si tu profe pide otra longitud (el PDF da 9 como ejemplo)
    public static final int LONGITUD_TELEFONO = 10;
    private static final String ARCHIVO = "agenda.txt";

    // Expansión 16: ArrayList en lugar del arreglo de 20
    private ArrayList<Contacto> contactos = new ArrayList<>();

    public int cantidadContactos() {
        return contactos.size();
    }

    // Expansión 17: validaciones
    public boolean telefonoValido(String telefono) {
        if (telefono.length() != LONGITUD_TELEFONO) {
            return false;
        }
        for (int i = 0; i < telefono.length(); i++) {
            if (!Character.isDigit(telefono.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean emailValido(String email) {
        return email.contains("@") && email.contains(".");
    }

    // No puede repetirse ni el nombre ni el teléfono
    public boolean estaRepetido(Contacto contacto) {
        for (Contacto existente : contactos) {
            if (existente.getNombre().equalsIgnoreCase(contacto.getNombre())
                    || existente.getTelefono().equals(contacto.getTelefono())) {
                return true;
            }
        }
        return false;
    }

    public boolean agregarContacto(Contacto contacto) {
        if (!telefonoValido(contacto.getTelefono())
                || !emailValido(contacto.getEmail())
                || estaRepetido(contacto)) {
            return false;
        }
        contactos.add(contacto);
        return true;
    }

    // Expansión 18: búsqueda parcial (el nombre CONTIENE el texto)
    public ArrayList<Contacto> buscarContacto(String texto) {
        ArrayList<Contacto> encontrados = new ArrayList<>();
        for (Contacto c : contactos) {
            if (c.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                encontrados.add(c);
            }
        }
        return encontrados;
    }

    public String mostrarTodos() {
        String resultado = "";
        for (Contacto c : contactos) {
            resultado += c.mostrarInfo() + "\n";
        }
        return resultado;
    }

    // Expansión 19
    public String mostrarContactosPorGrupo(String grupo) {
        String resultado = "";
        for (Contacto c : contactos) {
            if (c.getGrupo().equalsIgnoreCase(grupo)) {
                resultado += c.mostrarInfo() + "\n";
            }
        }
        return resultado;
    }

    public boolean eliminarContacto(String nombre) {
        for (int i = 0; i < contactos.size(); i++) {
            if (contactos.get(i).getNombre().equalsIgnoreCase(nombre)) {
                contactos.remove(i);
                return true;
            }
        }
        return false;
    }

    // Expansión 20: archivo de texto, una línea por contacto: nombre;telefono;email;grupo
    public boolean guardarEnArchivo() {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Contacto c : contactos) {
                escritor.println(c.getNombre().replace(";", ",") + ";"
                        + c.getTelefono() + ";"
                        + c.getEmail() + ";"
                        + c.getGrupo());
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Devuelve false si todavía no existe el archivo (primera ejecución)
    public boolean cargarDesdeArchivo() {
        try (BufferedReader lector = new BufferedReader(new FileReader(ARCHIVO))) {
            contactos.clear();
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    contactos.add(new Contacto(partes[0], partes[1], partes[2], partes[3]));
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}