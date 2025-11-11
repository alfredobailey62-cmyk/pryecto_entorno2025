package registroacademico.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Repository<T> {
    protected final File file;

    protected Repository(File file) {
        this.file = file;
    }

    abstract String parseToString(T e);

    abstract T parseFromString(String s);

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
