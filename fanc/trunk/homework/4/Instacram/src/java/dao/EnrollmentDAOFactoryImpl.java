/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import databeans.Enrollment;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.DuplicateKeyException;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

/**
 *
 * @author f9chen
 */
public class EnrollmentDAOFactoryImpl implements EnrollmentDAO {

    private BeanFactory<Enrollment> factory;
    private String jdbcDriver;
    private String jdbcURL;

    public EnrollmentDAOFactoryImpl(String jdbcDriver, String jdbcURL) throws MyDAOException {
        this.jdbcDriver = jdbcDriver;
        this.jdbcURL = jdbcURL;

        try {
            BeanTable<Enrollment> table = BeanTable.getSQLInstance(Enrollment.class, "fanc_enrollment", jdbcDriver, jdbcURL);
            if (!table.exists()) {
                table.create("email","courseName");
            }
            factory = table.getFactory();
        } catch (BeanFactoryException e) {
            throw new MyDAOException(e);
        }
    }

    @Override
    public synchronized Enrollment createEnrollment(String email, String courseName) throws MyDAOException {
        try {
            Transaction.begin();
            Enrollment newEnrollment = factory.create(email,courseName);
            Transaction.commit();
            return newEnrollment;
        } catch (DuplicateKeyException e) {
            throw new MyDAOException("An exact same enrollment already exists.");
        } catch (RollbackException e) {
            throw new MyDAOException(e);
        }

    }
}
