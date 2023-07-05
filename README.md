# Mini Database Engine

This project is a mini database engine that supports SQL statements such as create, insert, update, delete, select, and create index using octree. It also includes a GUI with the functionality to write and execute SQL queries, save and open SQL files, and switch between light and dark mode for the code editor.

## Getting Started

To get started with the project, follow these steps:

1. Clone the repository to your local machine.
2. Open the project in your preferred Java IDE (such as Eclipse or IntelliJ IDEA).
3. Build the project using Maven.
4. Run the `MiniDatabaseEngineGUI` class to start the GUI.

## Supported SQL Statements

The mini database engine supports the following SQL statements:

- `CREATE TABLE`: creates a new table in the database.
- `INSERT INTO`: inserts new rows into a table.
- `UPDATE`: updates rows in a table.
- `DELETE FROM`: deletes rows from a table.
- `SELECT`: selects rows from one or more tables.
- `CREATE INDEX USING OCTREE`: creates an octree index on a table.

## GUI Functionality

The GUI provides the following functionality:

- A code editor for writing SQL queries.
- A console for displaying query results and error messages.
- A file menu for saving and loading SQL files.
- A theme menu for switching between light and dark mode.
- A Help menu to support user-experience.

## Octree Indexing

The mini database engine uses an octree index to speed up queries on large datasets. The octree index is a tree data structure that partitions the data into smaller and smaller cubes. Each cube represents a range of values for the indexed columns of the table. The mini database engine uses the octree index to quickly locate the rows that match a given query.

## Future Work

There are several ways in which the mini database engine could be improved in the future:

- Support for additional SQL statements, such as `ALTER TABLE` and `DROP TABLE`.
- Support for more advanced indexing techniques, such as B-trees or hash indexes.
- Support for more complex queries, such as joins and subqueries.

## Contributors
This project was developed by [Omar Ahmed](https://github.com/OMAR-AHMED-SAAD), [Abdullah El Nahas](https://github.com/AbdullahElNahas), [Youssef Samer Samir](https://github.com/YoussefSamerSamir) and [Habiba Mohamed](https://github.com/HabibaMohamedd4). If you have any questions or feedback, please feel free to contact us.

## GUI Preview

Here are some screenshots of the GUI 
![Home](https://github.com/OMAR-AHMED-SAAD/Mini-Database-Engine/assets/110069095/d3cdfa78-1d9d-4187-adee-72bcaa1ebab3)
![Help](https://github.com/OMAR-AHMED-SAAD/Mini-Database-Engine/assets/110069095/7a57fecb-56e4-4956-849c-169347001c7f)
![Select](https://github.com/OMAR-AHMED-SAAD/Mini-Database-Engine/assets/110069095/9e433604-4f68-4310-bd54-cd5fd71de89d)
![Editor](https://github.com/OMAR-AHMED-SAAD/Mini-Database-Engine/assets/110069095/0d2005ff-7dcf-452e-ad22-2fd01331bbae)
![Error Handling](https://github.com/OMAR-AHMED-SAAD/Mini-Database-Engine/assets/110069095/81854384-4492-44c5-a218-32a8ccbc9344)


