ALTER TABLE doctors ADD COLUMN active BOOLEAN;
UPDATE doctors SET  active = true;