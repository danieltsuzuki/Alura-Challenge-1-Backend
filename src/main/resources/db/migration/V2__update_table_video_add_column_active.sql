ALTER TABLE videos ADD active  TINYINT;
UPDATE videos SET active = 1;
