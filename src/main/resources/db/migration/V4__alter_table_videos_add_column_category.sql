ALTER TABLE videos ADD COLUMN category_id BIGINT DEFAULT 1,
    ADD FOREIGN KEY (category_id) REFERENCES videos (id);