import java.sql; 
import java.util;


public class ECommerceWebsite {
private static Connection conn = null;
private static Statement stmt = null;
private static ResultSet rs = null;


public static void main(String[] args) {

    
    try {
        // Load the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");
        
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "password");

        stmt = conn.createStatement();

        createTables();
        insertData();
        queryData();

        conn.close();
        stmt.close();
        rs.close();

    } catch (Exception e) {
        
        e.printStackTrace();
    }
}


public static void createTables() throws SQLException {

  
    stmt.executeUpdate("DROP TABLE IF EXISTS rating");
    stmt.executeUpdate("DROP TABLE IF EXISTS order");
    stmt.executeUpdate("DROP TABLE IF EXISTS supplier_pricing");
    stmt.executeUpdate("DROP TABLE IF EXISTS product");
    stmt.executeUpdate("DROP TABLE IF EXISTS category");
    stmt.executeUpdate("DROP TABLE IF EXISTS customer");
    stmt.executeUpdate("DROP TABLE IF EXISTS supplier");

    
    stmt.executeUpdate("CREATE TABLE supplier (" +
            "SUPP_ID INT PRIMARY KEY," +
            "SUPP_NAME VARCHAR(50) NOT NULL," +
            "SUPP_CITY VARCHAR(50) NOT NULL," +
            "SUPP_PHONE VARCHAR(50) NOT NULL" +
            ")");

   
    stmt.executeUpdate("CREATE TABLE customer (" +
            "CUS_ID INT PRIMARY KEY," +
            "CUS_NAME VARCHAR(20) NOT NULL," +
            "CUS_PHONE VARCHAR(10) NOT NULL," +
            "CUS_CITY VARCHAR(30) NOT NULL," +
            "CUS_GENDER CHAR" +
            ")");

    
    stmt.executeUpdate("CREATE TABLE category (" +
            "CAT_ID INT PRIMARY KEY," +
            "CAT_NAME VARCHAR(20) NOT NULL" +
            ")");

    
    stmt.executeUpdate("CREATE TABLE product (" +
            "PRO_ID INT PRIMARY KEY," +
            "PRO_NAME VARCHAR(20) NOT NULL DEFAULT 'Dummy'," +
            "PRO_DESC VARCHAR(60)," +
            "CAT_ID INT," +
            "FOREIGN KEY (CAT_ID) REFERENCES category(CAT_ID)" +
            ")");

   
    stmt.executeUpdate("CREATE TABLE supplier_pricing (" +
            "PRICING_ID INT PRIMARY KEY," +
            "PRO_ID INT," +
            "SUPP_ID INT," +
            "SUPP_PRICE INT DEFAULT 0," +
            "FOREIGN KEY (PRO_ID) REFERENCES product(PRO_ID)," +
            "FOREIGN KEY (SUPP_ID) REFERENCES supplier(SUPP_ID)" +
            ")");

    
    stmt.executeUpdate("CREATE TABLE order (" +
            "ORD_ID INT PRIMARY KEY," +
            "ORD_AMOUNT INT NOT NULL," +
            "ORD_DATE DATE NOT NULL," +
            "CUS_ID INT," +
            "PRICING_ID INT," +
            "FOREIGN KEY (CUS_ID) REFERENCES customer(CUS_ID)," +
            "FOREIGN KEY (PRICING_ID) REFERENCES supplier_pricing(PRICING_ID)" +
            ")");

    
    stmt.executeUpdate("CREATE TABLE rating (" +
            "RAT_ID INT PRIMARY KEY," +
            "ORD_ID INT," +
            "RAT_RATSTARS INT NOT NULL," +
            "FOREIGN KEY (ORD_ID) REFERENCES order(ORD_ID)" +
            ")");
}


public static void insertData() throws SQLException {

    
    stmt.executeUpdate("INSERT INTO supplier VALUES" +
            "(1, 'Rajesh Retails', 'Delhi', '1234567890')," +
            "(2, 'Appario Ltd.', 'Mumbai', '2589631470')," +
            "(3, 'Knome products', 'Banglore', '9785462315')," +
            "(4, 'Bansal Retails', 'Kochi', '8975463285')," +
            "(5, 'Mittal Ltd.', 'Lucknow', '7898456532')");

    
    stmt.executeUpdate("INSERT INTO customer VALUES" +
            "(1, 'AAKASH', '9999999999', 'DELHI', 'M')," +
            "(2, 'AMAN', '9785463215', 'NOIDA', 'M')," +
            "(3, 'NEHA', '9999999999', 'MUMBAI', 'F')," +
            "(4, 'MEGHA', '9994562399', 'KOLKATA', 'F')," +
            "(5, 'PULKIT', '7895999999', 'LUCKNOW', 'M')");

    
    stmt.executeUpdate("INSERT INTO category VALUES" +
            "(1, 'BOOKS')," +
            "(2, 'GAMES')," +
            "(3, 'GROCERIES')," +
            "(4, 'ELECTRONICS')," +
            "(5, 'CLOTHES')");

    
    stmt.executeUpdate("INSERT INTO product VALUES" +
            "(1, 'GTA V', 'Windows 7 and above with i5 processor and 8GB RAM', 2)," +
            "(2, 'TSHIRT', 'SIZE-L with Black, Blue and White variations', 5)," +
            "(3, 'ROG LAPTOP', 'Windows 10 with 15inch screen, i7 processor, 1TB SSD', 4)," +
            "(4, 'OATS', 'Highly Nutritious from Nestle', 3)," +
            "(5, 'HARRY POTTER', 'Best Collection of all time by J.K Rowling', 1)," +
            "(6, 'MILK', '1L Toned MIlk', 3)," +
            "(7, 'Boat Earphones', '1.5Meter long Dolby Atmos', 4)," +
            "(8, 'Jeans', 'Stretchable Denim Jeans with various sizes and color', 5)," +
            "(9, 'Project IGI', 'compatible with windows 7 and above', 2)," +
            "(10, 'Hoodie', 'Black GUCCI for 13 yrs and above', 5)," +
            "(11, 'Rich Dad Poor Dad', 'Written by RObert Kiyosaki', 1)," +
            "(12, 'Train Your Brain', 'By Shireen Stephen', 1)");

   
    stmt.executeUpdate("INSERT INTO supplier_pricing VALUES" +
            "(1, 1, 2, 1500)," +
            "(2, 3, 5, 30000)," +
            "(3, 5, 1, 3000)," +
            "(4, 2, 3, 2500)," +
            "(5, 4, 1, 1000)," +
            "(6, 12, 2, 780)," +
            "(7, 12, 4, 789)," +
            "(8, 3, 1, 31000)," +
            "(9, 1, 5, 1450)," +
            "(10, 4, 2, 999)," +
            "(11, 7, 3, 549)," +
            "(12, 7, 4, 529)," +
            "(13, 6, 2, 105)," +
            "(14, 6, 1, 99)," +
            "(15, 2, 5, 2999)," +
            "(16, 5, 2, 2999)");

    
    stmt.executeUpdate("INSERT INTO order VALUES" +
            "(101, 1500, '2021-10-06', 2, 1)," +
            "(102, 1000, '2021-10-12', 3, 5)," +
            "(103, 30000, '2021-09-16', 5, 2)," +
            "(104, 1500, '2021-10-05', 1, 1)," +
            "(105, 3000, '2021-08-16', 4, 3)," +
            "(106, 1450, '2021-08-18', 1, 9)," +
            "(107, 789, '2021-09-01', 3, 7)," +
            "(108, 780, '2021-09-07', 5, 6)," +
            "(109, 3000, '2021-09-10', 5, 3)," +
            "(110, 2500, '2021-09-10', 2, 4)," +





           


public static void queryData() throws SQLException {

    
    String query = null;
    String result = null;

   
    query = "SELECT CUS_GENDER, COUNT(DISTINCT CUS_ID) AS TOTAL_CUSTOMERS " +
            "FROM customer c JOIN order o ON c.CUS_ID = o.CUS_ID " +
            "WHERE ORD_AMOUNT >= 3000 " +
            "GROUP BY CUS_GENDER";
    rs = stmt.executeQuery(query);
    result = "The total number of customers based on gender who have placed individual orders of worth at least Rs.3000 are:\n";
    result += "| CUS_GENDER | TOTAL_CUSTOMERS |\n";
    result += "|------------|-----------------|\n";
    while (rs.next()) {
        result += "| " + rs.getString("CUS_GENDER") + " | " + rs.getInt("TOTAL_CUSTOMERS") + " |\n";
    }
    System.out.println(result);


    query = "SELECT ORD_ID, ORD_AMOUNT, ORD_DATE, PRO_NAME " +
            "FROM order o JOIN supplier_pricing sp ON o.PRICING_ID = sp.PRICING_ID " +
            "JOIN product p ON sp.PRO_ID = p.PRO_ID " +
            "WHERE CUS_ID = 2";
    rs = stmt.executeQuery(query);
    result = "The orders along with product name ordered by a customer having Customer_Id=2 are:\n";
    result += "| ORD_ID | ORD_AMOUNT | ORD_DATE | PRO_NAME |\n";
    result += "|--------|------------|----------|----------|\n";
    while (rs.next()) {
        result += "| " + rs.getInt("ORD_ID") + " | " + rs.getInt("ORD_AMOUNT") + " | " + rs.getDate("ORD_DATE") + " | " + rs.getString("PRO_NAME") + " |\n";
    }
    System.out.println(result);

    
    query = "SELECT s.SUPP_ID, SUPP_NAME, SUPP_CITY, SUPP_PHONE, COUNT(DISTINCT PRO_ID) AS TOTAL_PRODUCTS " +
            "FROM supplier s JOIN supplier_pricing sp ON s.SUPP_ID = sp.SUPP_ID " +
            "GROUP BY s.SUPP_ID " +
            "HAVING TOTAL_PRODUCTS > 1";
    rs = stmt.executeQuery(query);
    result = "The Supplier details who can supply more than one product are:\n";
    result += "| SUPP_ID | SUPP_NAME | SUPP_CITY | SUPP_PHONE | TOTAL_PRODUCTS |\n";
    result += "|---------|-----------|-----------|------------|----------------|\n";
    while (rs.next()) {
        result += "| " + rs.getInt("SUPP_ID") + " | " + rs.getString("SUPP_NAME") + " | " + rs.getString("SUPP_CITY") + " | " + rs.getString("SUPP_PHONE") + " | " + rs.getInt("TOTAL_PRODUCTS") + " |\n";
    }
    System.out.println(result);

    
    query = "SELECT c.CAT_ID, CAT_NAME, PRO_NAME, MIN(SUPP_PRICE) AS LEAST_PRICE " +
            "FROM category c JOIN product p ON c.CAT_ID = p.CAT_ID " +
            "JOIN supplier_pricing sp ON p.PRO_ID = sp.PRO_ID " +
            "GROUP BY c.CAT_ID";
    rs = stmt.executeQuery(query);
    result = "The least expensive product from each category are:\n";
    result += "| CAT_ID | CAT_NAME | PRO_NAME | LEAST_PRICE |\n";
    result += "|--------|----------|----------|-------------|\n";
    while (rs.next()) {
        result += "| " + rs.getInt("CAT_ID") + " | " + rs.getString("CAT_NAME") + " | " + rs.getString("PRO_NAME") + " | " + rs.getInt("LEAST_PRICE") + " |\n";
    }
    System.out.println(result);


    query = "SELECT DISTINCT p.PRO_ID, PRO_NAME " +
            "FROM product p JOIN supplier_pricing sp ON p.PRO_ID = sp.PRO_ID " +
            "JOIN order o ON sp.PRICING_ID = o.PRICING_ID " +
            "WHERE ORD_DATE > '2021-10-05'";
    rs = stmt.executeQuery(query);
    result = "The Id and Name of the Product ordered after “2021-10-05” are:\n";
    result += "| PRO_ID | PRO_NAME |\n";
    result += "|--------|----------|\n";
    while (rs.next()) {
        result += "| " + rs.getInt("PRO_ID") + " | " + rs.getString("PRO_NAME") + " |\n";
    }
    System.out.println(result);
}