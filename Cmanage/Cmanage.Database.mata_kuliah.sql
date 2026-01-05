
CREATE TABLE mata_kuliah (
    id_matkul VARCHAR(10) PRIMARY KEY,
    nama VARCHAR(150) NOT NULL,
    sks INT NOT NULL,
    id_user VARCHAR(20) NOT NULL,
    id_ruangan VARCHAR(20) NOT NULL
);

INSERT INTO mata_kuliah (id_matkul, nama, sks, id_user, id_ruangan) VALUES
('AI300', 'Gambar Teknik dan Perencanaan', 4, 'U.D.02', '23.4D.02.001'),
('AI301', 'Manajemen Informasi dan Basis Data', 4, 'U.D.01', '23.4D.03.002'),
('AI302', 'AI dan Dan Analitik', 4, 'U.D.04', '23.4D.02.001'),
('AI304', 'Sensor dan Sistem Tertanam', 4, 'U.D.04', '23.4D.02.001'),
('AI305', 'Pemrograman Lanjut', 4, 'U.D.01', '23.4F.02.003'),
('AI306', 'Technopreneurship', 4, 'U.D.03', '23.4D.01.011'),

('AI200', 'ALGORITMA DAN PEMROGRAMAN', 4, 'U.D.01', '23.4F.02.003'),
('AI201', 'ELEKTRONIKA', 4, 'U.D.03', '23.4D.02.001'),
('AI202', 'RANGKAIAN LISTRIK', 4, 'U.D.03', '23.4G.01.001'),
('AI204', 'MATEMATIKA TEKNIK', 4, 'U.D.05', '23.4G.01.001'),
('KU100', 'PENDIDIKAN AGAMA ISLAM', 2, 'U.D.06', '23.4D.01.011'),
('KU110', 'PENDIDIKAN PANCASILA', 2, 'U.D.07', '23.4G.01.001');

SELECT * FROM mata_kuliah;
SHOW TABLES;
DESCRIBE mata_kuliah;
