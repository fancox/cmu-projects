package dao;

import databeans.*;

/**
 * Product Version: NetBeans IDE 7.1 (Build 201112071828)
 *
 * @author Fan Chen
 */
public interface NoteDAO {

    public Note createNote(String content, String courseName, String createdBy, String timestamp) throws MyDAOException;
}
