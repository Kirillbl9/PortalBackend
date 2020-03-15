INSERT INTO roles(id, name) VALUES (1, 'ROLE_USER');
INSERT INTO roles(id, name) VALUES (2, 'ROLE_ADMIN');
INSERT INTO users(id, email, name, password, provider) VALUES (1, 'test@gmail.com', 'test', 'test', 'local');
INSERT INTO subjects(id, name) VALUES (1, 'C');
INSERT INTO subjects(id, name) VALUES (2, 'Java');
INSERT INTO subjects(id, name) VALUES (3, 'HTML');
INSERT INTO subjects(id, name) VALUES (4, 'CSS');
INSERT INTO courses(id, name, description, subject_id, AUTHOR_OF_COURSE_ID) VALUES (1, 'C for beginner', 'Introduction in C', 1, 1);
INSERT INTO courses(id, name, description, subject_id, AUTHOR_OF_COURSE_ID) VALUES (2, 'Java in action', 'Course about java', 2, 1);
INSERT INTO courses(id, name, description, subject_id, AUTHOR_OF_COURSE_ID) VALUES (3, 'Java in use', 'Course about java', 2, 1);
INSERT INTO courses(id, name, description, subject_id, AUTHOR_OF_COURSE_ID) VALUES (4, 'Clean code', 'Course about everything', 2, 1);
INSERT INTO courses(id, name, description, subject_id, AUTHOR_OF_COURSE_ID) VALUES (5, 'HTML basics', 'For beginner', 3, 1);
INSERT INTO courses(id, name, description, subject_id, AUTHOR_OF_COURSE_ID) VALUES (6, 'CSS in examples', 'Foundation of all', 3, 1);
INSERT INTO users_courses(user_id, course_id) VALUES (1, 2);
INSERT INTO users_courses(user_id, course_id) VALUES (1, 4);
INSERT INTO users_courses(user_id, course_id) VALUES (1, 6);
INSERT INTO users_roles(user_id, role_id) VALUES (1, 1);


-- INSERT INTO roles( name) VALUES ( 'ROLE_USER');
-- INSERT INTO roles( name) VALUES ( 'ROLE_ADMIN');
-- INSERT INTO users( email, name, password, provider) VALUES ( 'test@gmail.com', 'test', 'test', 'local');
-- INSERT INTO subjects( name) VALUES ( 'C');
-- INSERT INTO subjects( name) VALUES ( 'Java');
-- INSERT INTO subjects( name) VALUES ( 'HTML');
-- INSERT INTO subjects( name) VALUES ( 'CSS');
-- INSERT INTO courses( name, description) VALUES ( 'C for beginner', 'Introduction in C');
-- INSERT INTO courses( name, description) VALUES ( 'Java in action', 'Course about java');
-- INSERT INTO courses( name, description) VALUES ( 'Java in use', 'Course about java');
-- INSERT INTO courses( name, description) VALUES ( 'Clean code', 'Course about everything');
-- INSERT INTO courses( name, description) VALUES ( 'HTML basics', 'For beginner');
-- INSERT INTO courses( name, description) VALUES ( 'CSS in examples', 'Foundation of all');
-- -- INSERT INTO users_courses(user_id, course_id) VALUES (1, 2);
-- -- INSERT INTO users_courses(user_id, course_id) VALUES (1, 4);
-- -- INSERT INTO users_courses(user_id, course_id) VALUES (1, 6);
-- -- INSERT INTO users_roles(user_id, role_id) VALUES (1, 1);








