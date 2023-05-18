ALTER TABLE videos ADD COLUMN category_id BIGINT,
    ADD FOREIGN KEY (category_id) REFERENCES videos (id);
ALTER TABLE videos ALTER COLUMN category_id SET DEFAULT 1;