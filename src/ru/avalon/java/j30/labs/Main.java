package ru.avalon.java.j30.labs;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;

/**
 * Лабораторная работа №3
 * <p>
 * Курс: "DEV-OCPJP. Подготовка к сдаче сертификационных экзаменов серии Oracle Certified Professional Java Programmer"
 * <p>
 * Тема: "JDBC - Java Database Connectivity" 
 *
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class Main {

    /**
     * Точка входа в приложение
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        /*
         * TODO #01 Подключите к проекту все библиотеки, необходимые для соединения с СУБД.
         */
        if(true) {
        try (Connection connection = getConnection()) {
            ProductCode code = new ProductCode("MO", 'N', "Movies");
            code.save(connection);
            printAllCodes(connection);
            
            System.out.println("---------------------");

            code.setCode("MV");
            code.save(connection);
            printAllCodes(connection);
        }
        }
        
//        Connection connection = getConnection();
//        PreparedStatement stmt = ProductCode.getSelectQuery(getConnection());
//        ResultSet result = stmt.executeQuery();
//        while(result.next()){
//            System.out.println(result.getString("PROD_CODE"));
//        }
        
        /*
         * TODO #14 Средствами отладчика проверьте корректность работы программы
         */
    }
    /**
     * Выводит в кодсоль все коды товаров
     * 
     * @param connection действительное соединение с базой данных
     * @throws SQLException 
     */    
    private static void printAllCodes(Connection connection) throws SQLException {
        Collection<ProductCode> codes = ProductCode.all(connection);
        for (ProductCode code : codes) {
            System.out.println(code);
        }
    }
    /**
     * Возвращает URL, описывающий месторасположение базы данных
     * 
     * @return URL в виде объекта класса {@link String}
     */
    private static String getUrl() {

        return "jdbc:derby://localhost:1527/sample";
    }
    /**
     * Возвращает параметры соединения
     * 
     * @return Объект класса {@link Properties}, содержащий параметры user и 
     * password
     */
    
    
    /*
     *  WRONG PROPERTIES EXCEPTIONS  ???
     */
    private static Properties getProperties() {

        Properties properties = new Properties();
        try (InputStream stream = ClassLoader.
                getSystemResourceAsStream("resources/db.properties")) {
            properties.load(stream);
        } catch (IOException e) {
            System.out.println("Can't load properties " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Can't load properties " + e.getMessage());
        }
        return properties;
    }
    /**
     * Возвращает соединение с базой данных Sample
     * 
     * @return объект типа {@link Connection}
     * @throws SQLException 
     */
    private static Connection getConnection() throws SQLException {
        
        Properties properties = getProperties();
        return DriverManager.getConnection(getUrl(), getProperties());
    }
    
}
