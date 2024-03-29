/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import databeans.Note;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author f9chen
 */
public class SearchDAOSQLImpl implements SearchDAO {

    private List<Connection> connectionPool = new ArrayList<Connection>();
    private String jdbcDriver;
    private String jdbcURL;

    public SearchDAOSQLImpl(String jdbcDriver, String jdbcURL) {
        this.jdbcDriver = jdbcDriver;
        this.jdbcURL = jdbcURL;
    }

    @Override
    public synchronized String searchInAllCourses(String queryString) throws MyDAOException {
        try {
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement("select * from fanc_note where content like ? order by timestamp desc");
            pstmt.setString(1, "%" + queryString + "%");
            ResultSet rs = pstmt.executeQuery();
            List<Note> list = new ArrayList<Note>();
            while (rs.next()) {
                list.add(new Note(rs.getString("content"), rs.getString("courseName"), rs.getString("createdBy"), rs.getString("timestamp")));
            }
            rs.close();
            pstmt.close();
            releaseConnection(con);

            String result = "";

            for (Note note : list) {
                result += "\"" + note.getContent() + "\"<br>course <a href=\"#\">" + note.getCourseName() + "</a><br>by <a href=\"#\">" + note.getCreatedBy() + "</a>@ " + note.getTimestamp() + "<br><br>";
            }
            return result;
        } catch (SQLException e) {
            throw new AssertionError(e);
        }

    }

    @Override
    public synchronized String searchInYourCourses(String queryString, String email) throws MyDAOException {
        try {
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement("select * from fanc_note join fanc_enrollment where content like ? and fanc_enrollment.email=? and fanc_enrollment.coursename=fanc_note.coursename order by timestamp desc");
            pstmt.setString(1, "%" + queryString + "%");
            pstmt.setString(2, email);
            ResultSet rs = pstmt.executeQuery();
            List<Note> list = new ArrayList<Note>();
            while (rs.next()) {
                list.add(new Note(rs.getString("content"), rs.getString("courseName"), rs.getString("createdBy"), rs.getString("timestamp")));
            }
            rs.close();
            pstmt.close();
            releaseConnection(con);

            String result = "";

            for (Note note : list) {
                result += "\"" + note.getContent() + "\"<br>course <a href=\"#\">" + note.getCourseName() + "</a><br>by <a href=\"#\">" + note.getCreatedBy() + "</a>@ " + note.getTimestamp() + "<br><br>";
            }
            return result;
        } catch (SQLException e) {
            throw new AssertionError(e);
        }
    }

    private synchronized Connection getConnection() throws MyDAOException {
        if (connectionPool.size() > 0) {
            return connectionPool.remove(connectionPool.size() - 1);
        }

        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            throw new MyDAOException(e);
        }

        try {
            return DriverManager.getConnection(jdbcURL);
        } catch (SQLException e) {
            throw new MyDAOException(e);
        }
    }

    private synchronized void releaseConnection(Connection con) {
        connectionPool.add(con);
    }
}
