package ru.itis.reflex.util;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GraphsTestDataGenerator {

    private static final String COMPANY_INSERT_SQL = "INSERT INTO company (id,name)"
            + "VALUES (?,?);";
    private static final String DEPARTMENT_INSERT_SQL = "INSERT INTO department (id,name,company_id)"
            + "VALUES (?,?,?);";
    private static final String USER_INSERT_SQL = "INSERT INTO \"user\" (id,email,password,role,name,department_id,company_id) "
            + "VALUES (?,?,\'password\',\'USER\',\'Some Name\',?,?);";
    private static final String MOOD_INSERT_SQL = "INSERT INTO mood_data (morning_val,evening_val,user_id,date) "
            + "VALUES (?,?,?,?);";
    private static final String TIREDNESS_INSERT_SQL = "INSERT INTO tiredness_data (morning_val,evening_val,user_id,date) "
            + "VALUES (?,?,?,?);";
    private static final String POSTURE_INSERT_SQL = "INSERT INTO posture_data (smooth_num,flex_num,user_id,date) "
            + "VALUES (?,?,?,?);";

    private static Connection c = null;
    private static PreparedStatement stmt = null;

    static Random random = new Random();

    public static void main(String args[]) {
        try {

            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/reflex",
                            "postgres", "postgres");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

//           addCompany();

            List<Date> dates = createDateRange(50);

            //for example user
            int id = 3;
            for (Date date: dates){
                addMoodData(id, date);
                addTirednessData(id, date);
                addPostureData(id, date);
            }

            //for departments

            for (int d=1; d<4; d++) {
                for (int i = 100; i < 150; i++) {
                    Integer uid = Integer.valueOf(i + "" + d);
                    addUser(uid, "email" + i + d + "@gmail.com", d, 1);

                    for (Date date : dates) {
                        addMoodData(uid, date);
                        addTirednessData(uid, date);
                        addPostureData(uid, date);
                    }

                }
            }

            stmt.close();
            c.commit();
            c.close();

        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        System.out.println("Test data created successfully");
    }

    static void addCompany() throws SQLException {
        stmt = c.prepareStatement(COMPANY_INSERT_SQL);

        stmt.setInt(1, 100);
        stmt.setString(2, "SomeCompany");

        stmt.executeUpdate();

        stmt = c.prepareStatement(DEPARTMENT_INSERT_SQL);

        stmt.setInt(1, 1);
        stmt.setString(2, "IT");
        stmt.setInt(3, 100);

        stmt.executeUpdate();
    }

    static void addUser(int id, String email, int departmentId, int companyId) throws SQLException {
        stmt = c.prepareStatement(USER_INSERT_SQL);

        stmt.setInt(1, id);
        stmt.setString(2, email);
        stmt.setInt(3, departmentId);
        stmt.setInt(4, companyId);

        stmt.executeUpdate();
    }

    private static void addMoodData(int userId, Date date) throws SQLException {
        stmt = c.prepareStatement(MOOD_INSERT_SQL);

        stmt.setInt(1, randomValue(10));
        stmt.setInt(2, randomValue(10));
        stmt.setInt(3, userId);
        stmt.setDate(4, date);

        stmt.executeUpdate();
    }

    private static void addTirednessData(int userId, Date date) throws SQLException {
        stmt = c.prepareStatement(TIREDNESS_INSERT_SQL);

        stmt.setInt(1, randomValue(10));
        stmt.setInt(2, randomValue(10));
        stmt.setInt(3, userId);
        stmt.setDate(4, date);

        stmt.executeUpdate();
    }

    private static void addPostureData(int userId, Date date) throws SQLException {
        stmt = c.prepareStatement(POSTURE_INSERT_SQL);

        stmt.setInt(1, randomValue(2000));
        stmt.setInt(2, randomValue(500));
        stmt.setInt(3, userId);
        stmt.setDate(4, date);

        stmt.executeUpdate();
    }

    private static List<Date> createDateRange(int days_num){

        LocalDate start = LocalDate.now().minusDays(days_num);
        LocalDate stop = LocalDate.now();

        List<Date> dates = new ArrayList<>();

        LocalDate localDate = start;
        while ( localDate.isBefore( stop ) ) {
            dates.add( Date.valueOf(localDate));
            localDate = localDate.plusDays( 1 );
        }
        return  dates;
    }


    private static int randomValue(int max){
        int randValue = (int) (random.nextGaussian() * (max/4) + (max/2));
        if (randValue > max) {
            randValue = max;
        }
        else if (randValue < 0) {
            randValue = 0;
        }
        return randValue;
    }

}
