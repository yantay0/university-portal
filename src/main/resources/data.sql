INSERT INTO department (name) VALUES
                                  ('Computer Science'),
                                  ('Electrical Engineering'),
                                  ('Mechanical Engineering'),
                                  ('Biology'),
                                  ('BASE'),
                                  ('Chemistry');

INSERT INTO course (code, name, department_id) VALUES
                                                   ('CSE101', 'Introduction to Computer Science', 1),
                                                   ('EEE201', 'Electronics I', 2),
                                                   ('MEC301', 'Thermodynamics', 3),
                                                   ('CHE401', 'Organic Chemistry', 6);

INSERT INTO teacher(department_id, id, user_id) VALUES
                                                    (1, 1, 1);