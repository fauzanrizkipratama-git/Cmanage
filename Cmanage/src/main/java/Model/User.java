package Model;

import java.util.UUID;

public class User {
    private String id;
    private String nama;
    private String nomor_induk;
    private String password;
    private String peran;

    public User(String id, String peran, String nomor_induk, String password) {
        this.id = id;
        this.peran = peran;
        this.nomor_induk = nomor_induk;
        this.password = password;
    }

    public User(String id, String nama, String nomor_induk, String password, String peran) {
        this.id = id;
        this.nama = nama;
        this.nomor_induk = nomor_induk;
        this.password = password;
        this.peran = peran;
    }

    public User(String id, String nama, String peran) {
        this.id = id;
        this.nama = nama;
        this.peran = peran;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomor_induk() {
        return nomor_induk;
    }

    public void setNomor_induk(String nomor_induk) {
        this.nomor_induk = nomor_induk;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPeran() {
        return peran;
    }

    public void setPeran(String peran) {
        this.peran = peran;
    }


}
