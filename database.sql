CREATE DATABASE IF NOT EXISTS eventdb;
USE eventdb;

-- students
CREATE TABLE IF NOT EXISTS students (
  student_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  password VARCHAR(100),
  branch VARCHAR(50),
  year INT
);

-- coordinators
CREATE TABLE IF NOT EXISTS coordinators (
  coord_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  password VARCHAR(100)
);

-- events
CREATE TABLE IF NOT EXISTS events (
  event_id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(100),
  description TEXT,
  date DATE,
  venue VARCHAR(100),
  capacity INT,
  coordinator_id INT,
  CONSTRAINT fk_coord FOREIGN KEY (coordinator_id) REFERENCES coordinators(coord_id) ON DELETE SET NULL
);

-- registrations
CREATE TABLE IF NOT EXISTS registrations (
  reg_id INT AUTO_INCREMENT PRIMARY KEY,
  event_id INT,
  student_id INT,
  status VARCHAR(20),
  CONSTRAINT fk_event FOREIGN KEY (event_id) REFERENCES events(event_id) ON DELETE CASCADE,
  CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE
);

-- sample coordinator
INSERT INTO coordinators (name, email, password) VALUES ('Admin One', 'admin@college.com', 'admin123');

-- sample student
INSERT INTO students (name, email, password, branch, year) VALUES ('Divya Dhule', 'divya@example.com', 'divya123', 'Software Engineering', 2);

-- sample event
INSERT INTO events (title, description, date, venue, capacity, coordinator_id)
VALUES ('Tech Fest', 'Annual tech fest', '2025-12-20', 'Auditorium', 200, 1);
