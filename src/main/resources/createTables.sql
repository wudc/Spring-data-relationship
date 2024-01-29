CREATE TABLE Manager (
    id INT PRIMARY KEY AUTO_INCREMENT,
    -- Other manager fields
);

CREATE TABLE Project (
    id INT PRIMARY KEY AUTO_INCREMENT,
    manager_id INT UNIQUE,
    FOREIGN KEY (manager_id) REFERENCES Manager(id),
    -- Other project fields
);

CREATE TABLE Staff (
    id INT PRIMARY KEY AUTO_INCREMENT,
    project_id INT,
    FOREIGN KEY (project_id) REFERENCES Project(id),
    -- Other staff fields
);
