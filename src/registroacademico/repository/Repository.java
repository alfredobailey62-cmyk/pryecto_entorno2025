package registroacademico.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Tabla que obtiene los elemento de los archivos de Base de Datos.
 *
 * @param <T> Modelo al que se le realizaran las operaciones
 */
public abstract class Repository<T> {

    /**
     * Archivo txt que ejerce de Base de Datos
     */
    protected final File file;

    /**
     *
     * @param name Nombre del archivo .txt
     */
    protected Repository(String name) {
        this.file = new File("/data/" + name + ".txt");
    }

    /**
     * Funcion que convierte el Objeto en un string que es el que se guardara el el .txt
     *
     * @param e Modelo
     * @return String formateado para su almacenamiento
     */
    abstract String parseToString(T e);

    /**
     * Funcion que mapea un string obtenido del .txt en un Objeto
     *
     * @param s String que representa un Modelo
     * @return Modelo ya mapeado
     */
    abstract T parseFromString(String s);

    /**
     * Guarda los datos de un arreglo del Modelo en el .txt
     *
     * @param list Lista de Modelos a guardar
     * @return Retorna true si se guardo y false si fallo
     */
    public boolean save(List<T> list) {
        try {
            file.getParentFile().mkdirs();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (var e : list) {
                bw.write(parseToString(e));
                bw.newLine();
            }
            bw.close();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Funcion que obtine una lista de modelos desde el .txt
     *
     * @return la lista obtenida del archivo
     */
    public List<T> read() {
        if (!file.exists()) return List.of();
        try {
            List<T> list = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String lines;
            while ((lines = br.readLine()) != null) {
                T e = parseFromString(lines);
                if (e != null) list.add(e);
            }
            br.close();
            return list;
        } catch (IOException ex) {
            ex.printStackTrace();
            return List.of();
        }
    }
}
