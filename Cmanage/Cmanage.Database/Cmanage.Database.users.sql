
CREATE TABLE users (
    id VARCHAR(50) PRIMARY KEY,
    nomor_induk VARCHAR(50) NOT NULL UNIQUE,
    nama VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    peran VARCHAR(50) NOT NULL
);

INSERT INTO users (id, nama, nomor_induk, password, peran) VALUES
('U.A.01','Admin Cmanage','2424242','admincmng1','admin'),
('U.M.01','Rizqi Abdan','2405122','abdan242','mahasiswa'),
('U.M.02','Fauzan Rizki Pratama','2404389','rizki123','pj_matkul'),
('U.M.03','Luthfi Bunga Nurmalasari','2400903','bunga234','mahasiswa'),
('U.M.04','Teresia Denia Primisani','2404107','denia234','mahasiswa'),
('U.M.05','Ghaisan Malik Al Jabbar','2400333','malik345','pj_matkul'),
('U.D.01','Liptia Venica, S.T., M.T','920210919941203000','liptia242','dosen'),
('U.D.02','Dewi Indriati Hadi Putri, S.Pd., M.T.','920190219900126000','dewi202','dosen'),
('U.D.03','Muhammad Rizalul Wahid, S.Si., M.T.','920210919940401000','rizalul232','dosen'),
('U.D.04','Mahmudah Salwa Gianti, S.Si., M.Eng.','920210919960408000','salwa232','dosen'),
('U.D.05','Dr. H. Suprih Widodo, S.Si, M.T.','198012172005021000','suprih252','dosen'),
('U.D.06','Jennyta Caturiasari, M.Pd.','920200119910729000','Jennyta302','dosen');

SELECT * FROM users;
SHOW TABLES;
DESCRIBE users;
SELECT * FROM users;
