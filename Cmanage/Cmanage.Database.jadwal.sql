
CREATE TABLE jadwal (
    id_jadwal VARCHAR(15) PRIMARY KEY,
    jam VARCHAR(20) NOT NULL,
    hari VARCHAR(15) NOT NULL,
    id_ruangan VARCHAR(20) NOT NULL,
    idMatkul VARCHAR(10) NOT NULL
);

INSERT INTO jadwal (id_jadwal, jam, hari, id_ruangan, idMatkul) VALUES
('J.11.01', '09:30 - 13:00', 'Senin',  '23.4D.01.011', 'AI306'),
('J.11.02', '13:50 - 17:10', 'Selasa', '23.4D.02.001', 'AI300'),
('J.11.03', '13:00 - 16:20', 'Kamis',  '23.4D.02.001', 'AI302'),
('J.11.04', '09:30 - 13:00', 'Rabu',   '23.4D.02.001', 'AI304'),
('J.11.05', '08:40 - 12:00', 'Kamis',  '23.4D.03.002', 'AI301'),
('J.11.06', '07:50 - 11:10', 'Selasa', '23.4F.02.003', 'AI305'),
('J.11.07', '07:00 - 10:20', 'Senin',  '23.4G.01.001', 'AI204'),
('J.11.08', '13:00 - 16:20', 'Senin',  '23.4F.02.003', 'AI200'),
('J.11.09', '07:00 - 10:20', 'Rabu',   '23.4G.01.001', 'AI202'),
('J.11.10', '10:20 - 12:00', 'Rabu',   '23.4D.01.011', 'KU100'),
('J.11.11', '08:40 - 10:20', 'Kamis',  '23.4G.01.001', 'KU110'),
('J.11.12', '07:50 - 11:10', 'Jumat',  '23.4D.02.001', 'AI201');

SELECT * FROM jadwal;
SHOW TABLES;
DESCRIBE jadwal;
