# CMPS451 Project Phase 2

## Project Description
This project aims to develop a cost calculator for SQL queries using Oracle and Java Swing for the frontend. The system allows users to calculate query costs for **selection**, **join** queries, and **metadata** retrieval from an Oracle database.

### Features:
1. **Select Queries**: 
   - Equality on key and non-key.
   - Range selection on key and non-key.
   - Conjunctive and disjunctive selection.
   
2. **Join Queries**: 
   - Equi-join cost calculation.

3. **Metadata Display**: 
   - Retrieve and display metadata from the database.

### Project Architecture
- **Frontend**: Built with Java Swing, providing an intuitive GUI for user interaction with the database.
  - The main scene contains buttons for selecting query types, performing joins, and viewing metadata.
  - The select window allows users to choose and calculate the cost of different query types.
  - The join window facilitates join query cost calculations.
  - The metadata window displays detailed metadata stored in the Oracle database.

- **Backend**: Powered by Oracle Database, with the connection handled via a `Dbase` class in Java. This class includes methods for executing query cost plans such as Binary Search, Linear Search, etc.

---

## Key Functionalities
1. **Query Cost Calculation**: Users can input specific query types and the program calculates the cost based on the underlying table and index structure.
   
2. **Metadata Management**: Retrieve and present the table's metadata, including details about column types, key types, index levels, distinct values, and ranges.

3. **Execution Plan**: Choose an appropriate execution plan based on the query input, using methods like Binary Search or Linear Search.

---

## Queries Implemented
- **Select equality on a key**
- **Select range on a key**
- **Select equality on non-key**
- **Select range on non-key**
- **Conjunctive selection**
- **Disjunctive selection**
- **Equi-join**

---

## How to Run the Program
1. Ensure you have **Java** installed and an **Oracle** database running.
2. Clone the repository or download the project files.
3. Open the project in an IDE like **Eclipse** or **IntelliJ**.
4. Compile and run the `Main` class to launch the GUI.
5. Use the GUI buttons to select, join, or view metadata.
