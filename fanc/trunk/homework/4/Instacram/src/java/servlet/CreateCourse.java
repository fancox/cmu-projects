/*
 * @author fanc
 * CreateCourse.java
 */
package servlet;

import dao.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateCourse extends HttpServlet {

    static UserDAO userDAO;
    static CourseDAO courseDAO;
    static NoteDAO noteDAO;
    static EnrollmentDAO enrollmentDAO;
    static SearchDAO searchDAO;

    @Override
    public void init() {
        //initMemoryImpl();
        //initSQLImpl();
        initFactoryImpl();
    }

//    public void initMemoryImpl() {
//        userDAO = UserDAOMemoryImpl.INSTANCE;
//        courseDAO = CourseDAOMemoryImpl.INSTANCE;
//
//    }
    public void initSQLImpl() {
        String jdbcDriverName = getInitParameter("jdbcDriverName");
        String jdbcURL = getInitParameter("jdbcURL");

        userDAO = new UserDAOSQLImpl(jdbcDriverName, jdbcURL);
        courseDAO = new CourseDAOSQLImpl(jdbcDriverName, jdbcURL);
        noteDAO = new NoteDAOSQLImpl(jdbcDriverName, jdbcURL);
        enrollmentDAO = new EnrollmentDAOSQLImpl(jdbcDriverName, jdbcURL);
        searchDAO = new SearchDAOSQLImpl(jdbcDriverName, jdbcURL);

    }

    public void initFactoryImpl() {
        String jdbcDriverName = getInitParameter("jdbcDriverName");
        String jdbcURL = getInitParameter("jdbcURL");
        try {
            userDAO = new UserDAOFactoryImpl(jdbcDriverName, jdbcURL);
            courseDAO = new CourseDAOFactoryImpl(jdbcDriverName, jdbcURL);
            noteDAO = new NoteDAOFactoryImpl(jdbcDriverName, jdbcURL);
            enrollmentDAO = new EnrollmentDAOFactoryImpl(jdbcDriverName, jdbcURL);
            searchDAO = new SearchDAOSQLImpl(jdbcDriverName, jdbcURL);
        } catch (MyDAOException e) {
        }

    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher view = null;
        HttpSession session = request.getSession();
        if (!(Boolean) session.getAttribute("login")) {
            request.setAttribute("message", "please log in");
            view = request.getRequestDispatcher("welcome.jsp");
        } else {
            String courseName = request.getParameter("courseName");
            if (courseName != null) {
                courseName = courseName.replace("<", "");
                courseName = courseName.replace(">", "");
            }
            // String email = request.getParameter("email");

            String email = (String) session.getAttribute("email");
            String message = "";

            if (courseName != null) {
                try {
                    courseDAO.createCourse(courseName, email);
                } catch (MyDAOException e) {
                }
                message = "Course has been created successfully!";
                request.setAttribute("message", message);
                request.setAttribute("email", email);
                session.setAttribute("login", true);
                view = request.getRequestDispatcher("create-a-course.jsp");
            } else {
                request.setAttribute("email", email);
                view = request.getRequestDispatcher("create-a-course.jsp");
            }
        }
        view.forward(request, response);
    }
}
