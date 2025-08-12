ALTER TABLE activities
    DROP COLUMN user_id;
ALTER TABLE activities
    ADD COLUMN assigned_user_id BIGINT NOT NULL;