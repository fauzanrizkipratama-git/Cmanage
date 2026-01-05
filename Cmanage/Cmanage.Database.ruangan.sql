
CREATE TABLE ruangan (
    id VARCHAR(20) PRIMARY KEY,
    nama VARCHAR(150) NOT NULL,
    lokasi VARCHAR(200) NOT NULL
);

INSERT INTO ruangan (id, nama, lokasi) VALUES
('23.4D.02.001', 'PWK LAB MKB MEKATRONIKA & KECERDASAN BUATAN', 'Gedung Perkuliahan 4 Purwakarta Lt. 2'),
('23.4D.03.002', 'PWK RUANG KULIAH 02', 'Gedung Perkuliahan 4 Purwakarta Lt. 3'),
('23.4F.02.003', 'PWK LAB KOMPUTER B', 'Gedung Perkuliahan 3 Purwakarta Lt. 2'),
('23.4D.01.011', 'PWK RUANG KULIAH 30', 'Gedung Perkuliahan 4 Purwakarta Lt. 1'),
('23.4G.01.001', 'PWK RUANG KULIAH 25', 'Ruang Kuliah 8 Kelas Purwakarta Lt.1');

SELECT * FROM ruangan;
SHOW TABLES;
DESCRIBE ruangan;
