//package dao;
//
//import instacram.Course;
//import instacram.Note;
//
///**
// * Product Version: NetBeans IDE 7.1 (Build 201112071828)
// * @author Fan Chen
// */
//public class NoteDAOMemoryImpl implements NoteDAO {
//
//  public static final NoteDAOMemoryImpl INSTANCE = new NoteDAOMemoryImpl();
//
//  CourseDAO courseDAO = CourseDAOMemoryImpl.INSTANCE;
//  public Note createNote (String content, String courseName, String createdBy, String timestamp) throws MyDAOException{
//    Note newNote = new Note(content, courseName, createdBy, timestamp);
//    courseDAO.addNote(courseName, newNote, this);
//    return copy(newNote);
//  }
//
//
//  private Note copy(Note original) {
//    if (original == null) {
//      return null;
//    }
//
//    Note duplicate = new Note(original.getContent(), original.getCourseName(),original.getCreatedBy(),original.getTimestamp());
//    return duplicate;
//  }
//
//
//}
