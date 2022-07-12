INSERT INTO hr_user (firstName, lastName, email, username, password)
VALUES ('Pero', 'Peric', 'pero@mail.com', 'pero', '$2y$12$tvFvkRFN0C//3MLED9YmQeGyiWoGJA55ytZax.85Kg1BEmO4R7MI2');
INSERT INTO hr_user (firstName, lastName, email, username, password)
VALUES ('Administrator', 'Admin', 'admin@mail.com', 'admin', '$2y$12$YQs3yNmEIbBQCyrRG2CgBeZ3B2PYekj680etsbYenhNtSrmXZJg0S');

-- TEST VALUES
INSERT INTO hr_user (firstName, lastName, email, username, password)
VALUES ('User', 'User', 'hrUser@mail.com', 'hrUser', '$2a$12$QBp.LkjkefOTmr74qNxtleAIujyGc7zPEZLv2dM.lg9pSTjS4EYEy');

INSERT INTO user_role (username, role) VALUES ('pero', 'ROLE_USER');
INSERT INTO user_role (username, role) VALUES ('admin', 'ROLE_USER');
INSERT INTO user_role (username, role) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO user_role (username, role) VALUES ('hrUser', 'ROLE_USER');
