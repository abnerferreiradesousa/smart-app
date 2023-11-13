CREATE TABLE IF NOT EXISTS tasks  (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    status VARCHAR(20) NOT NULL,
    user_id BIGINT NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT fk_user_tasks_id FOREIGN KEY(user_id) REFERENCES users(id)
);