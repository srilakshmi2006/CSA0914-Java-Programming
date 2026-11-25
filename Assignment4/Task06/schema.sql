CREATE DATABASE IF NOT EXISTS campus_events;
USE campus_events;
CREATE TABLE IF NOT EXISTS students (
  student_id VARCHAR(20) PRIMARY KEY,
  student_name VARCHAR(100) NOT NULL,
  email VARCHAR(150) NOT NULL
);
CREATE TABLE IF NOT EXISTS events (
  event_id INT PRIMARY KEY AUTO_INCREMENT,
  event_name VARCHAR(120) NOT NULL,
  capacity INT NOT NULL CHECK (capacity > 0)
);
CREATE TABLE IF NOT EXISTS registrations (
  registration_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  student_id VARCHAR(20) NOT NULL,
  event_id INT NOT NULL,
  registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_reg_student FOREIGN KEY(student_id) REFERENCES students(student_id),
  CONSTRAINT fk_reg_event FOREIGN KEY(event_id) REFERENCES events(event_id),
  CONSTRAINT uq_student_event UNIQUE(student_id,event_id)
);
