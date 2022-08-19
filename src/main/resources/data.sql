INSERT INTO hr_user (first_name, last_name, email, username, password)
VALUES ('User', 'User', 'hrUser@mail.com', 'user', '$2a$12$QBp.LkjkefOTmr74qNxtleAIujyGc7zPEZLv2dM.lg9pSTjS4EYEy');
INSERT INTO hr_user (first_name, last_name, email, username, password)
VALUES ('User2', 'User2', 'hrUser2@mail.com', 'user2', '$2a$12$QBp.LkjkefOTmr74qNxtleAIujyGc7zPEZLv2dM.lg9pSTjS4EYEy');
INSERT INTO hr_user (first_name, last_name, email, username, password)
VALUES ('Administrator', 'Admin', 'admin@mail.com', 'admin', '$2y$12$YQs3yNmEIbBQCyrRG2CgBeZ3B2PYekj680etsbYenhNtSrmXZJg0S');

INSERT INTO user_role (username, role) VALUES ('user', 'ROLE_USER');
INSERT INTO user_role (username, role) VALUES ('user2', 'ROLE_USER');
INSERT INTO user_role (username, role) VALUES ('admin', 'ROLE_USER');
INSERT INTO user_role (username, role) VALUES ('admin', 'ROLE_ADMIN');

INSERT INTO todo (user_id, description, creation_date) VALUES (1, 'First TODO', '2022-07-23 00:00:00.000');
INSERT INTO todo (user_id, description, creation_date) VALUES (1, 'Second TODO', '2022-07-23 12:00:00.000');
INSERT INTO todo (user_id, description, creation_date) VALUES (1, 'Third TODO', '2022-07-23 12:02:00.000');
INSERT INTO todo (user_id, description, creation_date) VALUES (2, 'First user2 TODO', '2022-07-23 00:00:00.000');
INSERT INTO todo (user_id, description, creation_date) VALUES (2, 'Second user2 TODO', '2022-07-23 12:00:00.000');
