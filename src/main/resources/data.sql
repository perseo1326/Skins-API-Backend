
USE jump2digital_test_skins_db;

DELETE FROM user_skins WHERE TRUE;

INSERT INTO user_skins (skins_users_user_id, skins_users_skin_id, skins_users_skin_color) VALUES
(1, 'A1', 'white'),
(2, 'B2', 'black'),
(2, 'C3', 'yellow'),
(4, 'D4', 'red'),
(1, 'E5', 'green'),
(3, 'F6', 'blue'),
(2, 'A1', 'white'),
(1, 'B2', 'black'),
(4, 'C3', 'yellow'),
(1, 'D4', 'red'),
(2, 'E5', 'green'),
(5, 'F6', 'blue');
