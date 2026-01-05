package Model;

public class Jadwal {
    private String id_jadwal;
    private String jam;
    private String tanggal;
    private String id_ruangan;
    private String idMatkul;
    private String nama_matkul;
    private String kelas;

    public Jadwal(String id_jadwal, String jam, String tanggal, String id_ruangan, String idMatkul, String nama_matkul, String kelas) {
        this.id_jadwal = id_jadwal;
        this.jam = jam;
        this.tanggal = tanggal;
        this.id_ruangan = id_ruangan;
        this.idMatkul = idMatkul;
        this.nama_matkul = nama_matkul;
        this.kelas = kelas;
    }

    public Jadwal(String jam, String tanggal, String id_ruangan, String idMatkul, String kelas) {
        this.jam = jam;
        this.tanggal = tanggal;
        this.id_ruangan = id_ruangan;
        this.idMatkul = idMatkul;
        this.kelas = kelas;
    }

    

    public String getNama_matkul() {
        return nama_matkul;
    }

    public void setNama_matkul(String nama_matkul) {
        this.nama_matkul = nama_matkul;
    }
    
    public String getIdJadwal() {
        return id_jadwal;
    }

    public void setIdJadwal(String idJadwal) {
        this.id_jadwal = idJadwal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getIdRuangan() {
        return id_ruangan;
    }

    public void setIdRuangan(String idRuangan) {
        this.id_ruangan = idRuangan;
    }

    public String getIdMatkul() {
        return idMatkul;
    }

    public void setIdMatkul(String idMatkul) {
        this.idMatkul = idMatkul;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

}